package com.syr.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;
import javax.validation.constraints.Min;
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

/**
 * @author Ebin
 * @on 26-Sep-2018
 * @version 0.0
 */
@Entity
@Table(name = "SYR_USER_VEHICLES",indexes={@Index(columnList="registration_no",name="idx_reg_no")})
@NamedEntityGraphs({ 
		@NamedEntityGraph(
				name = "userVehicle", 
				attributeNodes = {
						@NamedAttributeNode(value = "vehicleModel", subgraph = "vehicleModelType"),
						@NamedAttributeNode(value = "user"/*,subgraph="userDetails"*/)
				}, 
				subgraphs = {
						@NamedSubgraph(name = "vehicleModelType", attributeNodes = { @NamedAttributeNode("vehicleType"),@NamedAttributeNode("vehicleCompany") }),
						/*@NamedSubgraph(name = "userDetails",attributeNodes={@NamedAttributeNode("userPhones"),@NamedAttributeNode("userEmails")})*/
				}
		) 
	})
public class UserVehicle extends AbstractPersistentObject {

	@Id
	@GenericGenerator(name = "usvh_seq", strategy = "com.syr.id_generators.MasterIdGenerator", parameters = @Parameter(name = "masterType", value = "USVH"))
	@GeneratedValue(generator = "usvh_seq")
	@Column(name = "user_vehicle_id",nullable = false)
	private long userVehicleId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "business_id", nullable = false, length = 100)
	private String bId = IdGenerator.createId();
	
	@Column(name = "registration_no",nullable = false,length = 100)
	private String registrationNo;
	@NotNull(message = "seating-capacity.notnull")
	@Min(value = 2, message = "seating-capacity.min")
	@Column(name = "seating_capacity", nullable = false)
	private int seatingCapacity;
	@Column(name = "ac_flag", length = 1, nullable = false)
	private String acFlag = SyrStatus.NO.Status(); // AC available or not
	@Column(name = "milaege_approx", nullable = false, precision = 5, scale = 2)
	private double milaegePerLtr = SyrDefault.DOUBLE;
	@Column(name = "taxi_flag", nullable = false, length = 1)
	private String taxiFlag = SyrStatus.NO.Status(); // Taxi Or Private
	@Column(name = "owner_flag", length = 1, nullable = false)
	private String ownerFlag = SyrStatus.YES.Status(); // Whether vehicle owner
														// or not
	@Column(name = "vehicle_status", length = 1, nullable = false)
	private String status = SyrStatus.ACTIVE.Status();
	@Column(name = "running_status", length = 1, nullable = false)
	private String runningStatus = SyrStatus.NO.Status(); // Current running
															// status
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "add_date", nullable = false)
	private LocalDate addDate = SyrDefault.CURRENT_DATE();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "mod_date", nullable = false)
	private LocalDateTime modDate = SyrDefault.CURRENT_DATE_TIME();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "model_id")
	private VehicleModel vehicleModel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public long getUserVehicleId() {
		return userVehicleId;
	}

	public void setUserVehicleId(long userVehicleId) {
		this.userVehicleId = userVehicleId;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public String getAcFlag() {
		return acFlag;
	}

	public void setAcFlag(String acFlag) {
		this.acFlag = acFlag;
	}

	public double getMilaegePerLtr() {
		return milaegePerLtr;
	}

	public void setMilaegePerLtr(double milaegePerLtr) {
		this.milaegePerLtr = milaegePerLtr;
	}

	public String getTaxiFlag() {
		return taxiFlag;
	}

	public void setTaxiFlag(String taxiFlag) {
		this.taxiFlag = taxiFlag;
	}

	public String getOwnerFlag() {
		return ownerFlag;
	}

	public void setOwnerFlag(String ownerFlag) {
		this.ownerFlag = ownerFlag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRunningStatus() {
		return runningStatus;
	}

	public void setRunningStatus(String runningStatus) {
		this.runningStatus = runningStatus;
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

	public VehicleModel getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(VehicleModel vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserVehicle [userVehicleId=" + userVehicleId + ", bId=" + bId + ", registrationNo=" + registrationNo
				+ ", seatingCapacity=" + seatingCapacity + ", acFlag=" + acFlag + ", milaegePerLtr=" + milaegePerLtr
				+ ", taxiFlag=" + taxiFlag + ", ownerFlag=" + ownerFlag + ", status=" + status + ", runningStatus="
				+ runningStatus + ", addDate=" + addDate + ", modDate=" + modDate + "]";
	}

}
