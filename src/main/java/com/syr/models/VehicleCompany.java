package com.syr.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.syr.app_config.AbstractPersistentObject;
import com.syr.app_config.IdGenerator;
import com.syr.defaults.SyrDefault;
import com.syr.defaults.SyrStatus;
import com.syr.defaults.SyrUserType;

/**
 * @author Ebin
 * @on 02-Oct-2018
 * @version 0.0
 */
@Entity
@Table(name = "SYR_VEHICLE_COMPANY")
public class VehicleCompany extends AbstractPersistentObject{

	@Id
	@GenericGenerator(name = "vc_seq", strategy = "com.syr.id_generators.MasterIdGenerator", parameters = @Parameter(name = "masterType", value = "VHCM"))
	@GeneratedValue(generator = "vc_seq")
	@Column(name = "company_id",nullable = false)
	private long companyId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "business_id", nullable = false, length = 100)
	private String bId = IdGenerator.createId();
	@NotNull(message = "company-name.notnull")
	@Column(name = "company_name",nullable = false,length = 100)
	private String companyName;
	@Column(name = "company_status",nullable = false,length = 1)
	private String companyStatus = SyrStatus.ACTIVE.Status();
	@Column(name = "user_view_flag",nullable = false,length = 2)
	private String userView = SyrUserType.NORMAL.Type();
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "add_date", nullable = false)
	private LocalDate addDate = SyrDefault.CURRENT_DATE();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "mod_date", nullable = false)
	private LocalDateTime modDate = SyrDefault.CURRENT_DATE_TIME();
	
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyStatus() {
		return companyStatus;
	}
	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}
	public String getUserView() {
		return userView;
	}
	public void setUserView(String userView) {
		this.userView = userView;
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
		return "VehicleCompany [companyId=" + companyId + ", bId=" + bId + ", companyName=" + companyName
				+ ", companyStatus=" + companyStatus + ", userView=" + userView + ", addDate=" + addDate + ", modDate="
				+ modDate + "]";
	}
}
