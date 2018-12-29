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
 * @on 26-Sep-2018
 * @version 0.0
 */
@Entity
@Table(name = "SYR_VEHICLE_TYPES")
public class VehicleType extends AbstractPersistentObject{

	@Id
	@GenericGenerator(name="vtype_seq", strategy="com.syr.id_generators.MasterIdGenerator",parameters = @Parameter(name = "masterType", value = "VTYP"))
	@GeneratedValue(generator="vtype_seq")
	@Column(name = "v_type_id",nullable = false)
	private long vehicleTypeId;
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "business_id", nullable = false, length = 100)
	private String bId = IdGenerator.createId();
	@NotNull(message="name.notnull")
	@Column(name = "v_type_name",nullable = false,length= 100)
	private String vehicleTypeName;
	@Column(name = "symbol_class",nullable = false,columnDefinition = "TEXT")
	private String symbolClass = SyrDefault.HASH;
	@Column(name = "user_view_flag",nullable = false,length = 2)
	private String userView = SyrUserType.NORMAL.Type();
	@Column(name = "status",nullable = false,length = 1)
	private String status = SyrStatus.ACTIVE.Status();
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "add_date", nullable = false)
	private LocalDate addDate	= SyrDefault.CURRENT_DATE();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "mod_date", nullable = false)
	private LocalDateTime modDate = SyrDefault.CURRENT_DATE_TIME();
	
	public long getVehicleTypeId() {
		return vehicleTypeId;
	}
	public void setVehicleTypeId(long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	public String getVehicleTypeName() {
		return vehicleTypeName;
	}
	public void setVehicleTypeName(String vehicleTypeName) {
		this.vehicleTypeName = vehicleTypeName;
	}
	public String getSymbolClass() {
		return symbolClass;
	}
	public void setSymbolClass(String symbolClass) {
		this.symbolClass = symbolClass;
	}
	public String getUserView() {
		return userView;
	}
	public void setUserView(String userView) {
		this.userView = userView;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		return "VehicleType [vehicleTypeId=" + vehicleTypeId + ", bId=" + bId + ", vehicleTypeName=" + vehicleTypeName
				+ ", symbolClass=" + symbolClass + ", userView=" + userView + ", status=" + status + ", addDate="
				+ addDate + ", modDate=" + modDate + "]";
	}
}
