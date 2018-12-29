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

import com.syr.defaults.SyrDefault;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
public class RideIdGenerator implements IdentifierGenerator,Configurable{

	private String reqType;
	/* (non-Javadoc)
	 * @see org.hibernate.id.Configurable#configure(org.hibernate.type.Type, java.util.Properties, org.hibernate.service.ServiceRegistry)
	 */
	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		setReqType(params.getProperty("rideReqType").trim());
	}

	/* (non-Javadoc)
	 * @see org.hibernate.id.IdentifierGenerator#generate(org.hibernate.engine.spi.SharedSessionContractImplementor, java.lang.Object)
	 */
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		long key		= 0;
		Serializable pk	= 0;	
		String numPartStr	= "";
		Connection connection = session.connection();
		
		CallableStatement ps;ResultSet rs;
		CallableStatement cs;
		
		try{
			int year	= Calendar.getInstance().get(Calendar.YEAR);
			
			ps = connection.prepareCall("SELECT key_value FROM syr_ride_keygen WHERE ride_req_type = ? AND year = ?");
			ps.setString(1, reqType);
			ps.setLong(2, year);
			rs = ps.executeQuery();
			if(rs.next()){
				key = rs.getLong("key_value")+1;
				
				cs	= connection.prepareCall("UPDATE syr_ride_keygen SET key_value = ? WHERE ride_req_type = ? AND year = ?");
				cs.setLong(1, key);
				cs.setString(2, reqType);
				cs.setLong(3, year);
				cs.executeUpdate();
				cs.close();cs= null;
			}else{
				key	= 1;
				
				cs	= connection.prepareCall("INSERT INTO syr_ride_keygen (`ride_req_type`,`year`,`key_value`) values (?,?,?)");
				cs.setString(1, reqType);
				cs.setInt(2, year);
				cs.setLong(3, key);
				cs.executeLargeUpdate();
				cs.close();cs=null;
			}
			rs.close();rs = null;
			ps.close();ps = null;
			
			if(String.valueOf(key).length() < SyrDefault.ID_NUM_PART_LENGTH){
				numPartStr	= String.format("%0"+SyrDefault.ID_NUM_PART_LENGTH+"d", key);
			}else{
				numPartStr	= String.valueOf(key);
			}
			if(reqType.trim().equals("CR"))
				pk	= Long.valueOf(String.valueOf(year)+key);
			else
				pk	= reqType.trim()+year+numPartStr;
		}catch (Exception e) {
			return null;
		}
		return pk;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

}
