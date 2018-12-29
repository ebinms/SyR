package com.syr.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.syr.app_config.AbstractPersistentObject;
import com.syr.app_config.IdGenerator;
import com.syr.defaults.SyrDefault;

/**
 * @author Ebin
 * @on 30-Sep-2018
 * @version 0.0
 */
@Entity
@Table(name = "syr_client_master")
public class ApplicationClient extends AbstractPersistentObject{
	@Id
	@GenericGenerator(name = "at_seq", strategy = "com.syr.id_generators.MasterIdGenerator", parameters = @Parameter(name = "masterType", value = "ACTK"))
	@GeneratedValue(generator = "at_seq")
	@Column(name = "client_id",nullable = false)
	private long clientId;
	
	@Column(name = "client_name",nullable = false,length = 100)
	private String clientName;
	@Column(name = "client_password",nullable = false,length = 100)
	private String clientPassword;
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "business_id", nullable = false, length = 100)
	private String bId = IdGenerator.createId();
	@Column(name = "client_email",nullable = false,length = 200)
	private String clientEmail;
	@Column(name = "country_code",nullable = false)
	private int countryCode = 0;
	@Column(name = "mobile_number",nullable = false)
	private long mobileNo = 0;
	@Column(name = "contact_other",nullable = false)
	private long contactNumberOther = 0;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "add_date", nullable = false)
	private LocalDate addDate = SyrDefault.CURRENT_DATE();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "mod_date", nullable = false)
	private LocalDateTime modDate = SyrDefault.CURRENT_DATE_TIME();
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientPassword() {
		return clientPassword;
	}
	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public int getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public long getContactNumberOther() {
		return contactNumberOther;
	}
	public void setContactNumberOther(long contactNumberOther) {
		this.contactNumberOther = contactNumberOther;
	}
	public LocalDate getAddDate() {
		return addDate;
	}
	public void setAddDate(LocalDate addDate) {
		this.addDate = addDate;
	}
	public LocalDateTime getModDate() {
		return modDate;
	}
	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}
	@Override
	public String toString() {
		return "ApplicationClient [clientId=" + clientId + ", clientName=" + clientName + ", clientPassword="
				+ clientPassword + ", bId=" + bId + ", clientEmail=" + clientEmail + ", countryCode=" + countryCode
				+ ", mobileNo=" + mobileNo + ", contactNumberOther=" + contactNumberOther + ", addDate=" + addDate
				+ ", modDate=" + modDate + "]";
	}
}
