package com.syr.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Table(name = "SYR_VEHICLE_MODELS")
@NamedEntityGraphs({
	@NamedEntityGraph(name="vehicleModel",attributeNodes={@NamedAttributeNode("vehicleType"),@NamedAttributeNode("vehicleCompany")})
})
public class VehicleModel extends AbstractPersistentObject{
	
	@Id
	@GenericGenerator(name="vm_seq", strategy="com.syr.id_generators.MasterIdGenerator",parameters = @Parameter(name = "masterType", value = "VHMD"))
	@GeneratedValue(generator="vm_seq")
	@Column(name = "model_id",nullable = false)
	private long modelId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "business_id", nullable = false, length = 100)
	private String bId = IdGenerator.createId();
	
	@NotNull(message = "name.notnull")
	@Column(name = "model_name",nullable = false,length = 100)
	private String modelName;
	@Column(name = "status",nullable = false,length = 1)
	private String status = SyrStatus.ACTIVE.Status();
	@Column(name = "user_view",nullable = false,length = 2)
	private String userView = SyrUserType.NORMAL.Type();
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "add_date", nullable = false)
	private LocalDate addDate	= SyrDefault.CURRENT_DATE();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "mod_date", nullable = false)
	private LocalDateTime modDate = SyrDefault.CURRENT_DATE_TIME();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "v_type_id")
	private VehicleType vehicleType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private VehicleCompany vehicleCompany;
	
	@Transient @JsonProperty(access = Access.WRITE_ONLY)
	private long vTypeId = 0;
	
	@Transient @JsonProperty(access = Access.WRITE_ONLY)
	private long companyId = 0;
	
	@Override
	public String toString() {
		return "VehicleModel [modelId=" + modelId + ", bId=" + bId + ", modelName=" + modelName + ", status=" + status
				+ ", userView=" + userView + ", addDate=" + addDate + ", modDate=" + modDate + "]";
	}

	public long getModelId() {
		return modelId;
	}

	public void setModelId(long modelId) {
		this.modelId = modelId;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public VehicleCompany getVehicleCompany() {
		return vehicleCompany;
	}

	public void setVehicleCompany(VehicleCompany vehicleCompany) {
		this.vehicleCompany = vehicleCompany;
	}

	public long getvTypeId() {
		return vTypeId;
	}

	public void setvTypeId(long vTypeId) {
		this.vTypeId = vTypeId;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
}
