package com.syr.id_generators;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

/**
 * @author Ebin
 * @Createdon 26/09/2018
 * @version 0.0
 */
public class MasterIdGenerator implements IdentifierGenerator,Configurable{
	
	private String masterType;

	@Override
	public void configure(Type arg0, Properties arg1, ServiceRegistry arg2) throws MappingException {
		setMasterType(arg1.getProperty("masterType").trim());
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object arg1) throws HibernateException {
		long key		= 0;
		long pk			= 0;	
		Connection connection = session.connection();
		
		CallableStatement ps;ResultSet rs;
		CallableStatement cs;
		
		try{
			int year	= Calendar.getInstance().get(Calendar.YEAR);
			
			ps = connection.prepareCall("SELECT key_value FROM syr_master_keygen WHERE master_type = ? AND year = ?");
			ps.setString(1, masterType);
			ps.setLong(2, year);
			rs = ps.executeQuery();
			if(rs.next()){
				key = rs.getLong("key_value")+1;
				
				cs	= connection.prepareCall("UPDATE syr_master_keygen SET key_value = ? WHERE master_type = ? AND year = ?");
				cs.setLong(1, key);
				cs.setString(2, masterType);
				cs.setLong(3, year);
				cs.executeUpdate();
				cs.close();cs= null;
			}else{
				key	= 1001;
				
				if(masterType.trim().equals("USER")||masterType.trim().equals("ACTK"))
					key	= 1002;
				
				cs	= connection.prepareCall("INSERT INTO syr_master_keygen (`master_type`,`year`,`key_value`) values (?,?,?)");
				cs.setString(1, masterType);
				cs.setInt(2, year);
				cs.setLong(3, key);
				cs.executeLargeUpdate();
				cs.close();cs=null;
			}
			rs.close();rs = null;
			ps.close();ps = null;
			
			pk	= Long.valueOf(key);
		}catch (Exception e) {
			return null;
		}
		return pk;
	}

	public void setMasterType(String masterType) {
		this.masterType = masterType;
	}

}
