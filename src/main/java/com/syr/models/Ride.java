package com.syr.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
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
import com.syr.defaults.SyrGender;
import com.syr.defaults.SyrPaymentMode;
import com.syr.defaults.SyrRideRequestType;
import com.syr.defaults.SyrStatus;

/**
 * @author Ebin
 * @on 02-Oct-2018
 * @version 0.0
 */
@Entity
@Table(name = "SYR_RIDES",indexes = {
		@Index(columnList="ride_date",name="idx_ride_date"),
		@Index(columnList="to_latitude,to_longitude",name="idx_destination_lat_long")
	})
@NamedEntityGraphs({
	@NamedEntityGraph(name="ride_details_full"
			,attributeNodes={
				@NamedAttributeNode(value="vehicle",subgraph="userVehicle"),
				@NamedAttributeNode(value="coRiders",subgraph="coRiderUser"),
			}
			,subgraphs={
				@NamedSubgraph(name="userVehicle"
					,attributeNodes={
						@NamedAttributeNode(value="vehicleModel",subgraph="modelDetails"),
						@NamedAttributeNode(value="user")
					}
				),	
				@NamedSubgraph(name="coRiderUser"
					,attributeNodes={
						@NamedAttributeNode(value="user")	
					}
				),
				@NamedSubgraph(name = "modelDetails"
					,attributeNodes={
						@NamedAttributeNode(value = "vehicleType"),
						@NamedAttributeNode(value = "vehicleCompany")
					}
				)
			}
	)
})
public class Ride extends AbstractPersistentObject{

	@Id
	@GenericGenerator(name = "ride_seq", strategy = "com.syr.id_generators.RideIdGenerator", parameters = @Parameter(name = "rideReqType", value = "RD"))
	@GeneratedValue(generator = "ride_seq")
	@Column(name = "ride_no",nullable = false,length = 20)
	private String rideNo;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "business_id", nullable = false, length = 100)
	private String bId = IdGenerator.createId();
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Version
	@Column(name = "version",nullable = false)
	private long version;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(name = "ride_date", nullable = false)
	private LocalDate rideDate	= SyrDefault.CURRENT_DATE();
	@NotNull(message = "rideType.notnull")
	@Column(name = "ride_type",nullable = false,length = 1)
	private String rideType;							//Enum-SyrRideType-Free/Paid
	@NotNull(message = "seatingCapacity.notnull") @Min(value=1,message="seating-capacity.min")
	@Column(name = "seat_availability",nullable = false,length = 1)
	private int seatAvailability;						//Except Driver/Host
	@Column(name = "ac_flag",nullable = false,length = 1)
	private String acFlag = SyrStatus.NO.Status();
	@Column(name="tot_fuel_amt",nullable = false,precision=10,scale=2)
	private double totalFuelAmount = SyrDefault.DOUBLE;
	@Column(name="per_head_amt",nullable = false,precision=10,scale=2)
	private double perHeadAmount = SyrDefault.DOUBLE;
	@Column(name = "ride_status",length = 1,nullable = false)
	private String rideStatus = SyrStatus.OPEN.Status();
	@Column(name = "gender_preference",nullable=false,length=1)
	private String genderPreference = SyrGender.ALL.Get();
	@Column(name = "ride_request_type",nullable = false,length = 1)
	private String rideRequestType = SyrRideRequestType.BOTH.Get();
	@NotNull(message = "fLat.notnull")
	@Column(name="from_latitude",nullable = false,precision=12,scale=8)
	private float fromLatitude;
	@NotNull(message = "fLong.notnull")
	@Column(name="from_longitude",nullable = false,precision=12,scale=8)
	private float fromLongitude;
	@NotNull(message = "fLoc.notnull")
	@Column(name="from_locality",nullable = false,columnDefinition="TEXT")
	private String fromLocality = SyrDefault.HASH;
	@NotNull(message = "fSubLoc.notnull")
	@Column(name="from_sub_locality",nullable = false,columnDefinition="TEXT")
	private String fromSubLocality = SyrDefault.HASH;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "starting_time", nullable = false)
	private LocalDateTime startingTime = SyrDefault.CURRENT_DATE_TIME();
	@NotNull(message = "toLat.notnull")
	@Column(name="to_latitude",nullable = false,precision=12,scale=8)
	private float toLatitude;
	@NotNull(message = "toLong.notnull")
	@Column(name="to_longitude",nullable = false,precision=12,scale=8)
	private float toLongitude;
	@NotNull(message = "toLoc.notnull")
	@Column(name="to_locality",nullable = false,columnDefinition="TEXT")
	private String toLocality 	 = SyrDefault.HASH;
	@NotNull(message = "toSubLoc.notnull")
	@Column(name="to_sub_locality",nullable = false,columnDefinition="TEXT")
	private String toSubLocality= SyrDefault.HASH;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "end_time", nullable = false)
	private LocalDateTime endTime = SyrDefault.CURRENT_DATE_TIME();
	@Column(name = "travel_time",nullable = false)
	private int travelTime = SyrDefault.INT;
	@Column(name = "travel_distance",nullable = false,precision = 10,scale = 2)
	private double travelDistance = SyrDefault.DOUBLE;
	@Column(name = "invite_count",nullable = false)
	private int inviteCount = SyrDefault.INT;
	@Column(name = "invitation_joinee_count",nullable = false)
	private int invitationJoineeCount = SyrDefault.INT;
	@Column(name = "join_in_req_count",nullable = false)
	private int joinInRequestCount = SyrDefault.INT;
	@Column(name = "join_in_req_joinee_count",nullable = false)
	private int joinInRequestAccpetCount = SyrDefault.INT;
	@Column(name = "co_riders_count",nullable = false)
	private int coRidersCount = SyrDefault.INT;
	@Column(name="ride_amount",nullable = false,precision=10,scale=2)
	private double rideAmount = SyrDefault.DOUBLE;
	@Column(name="charges",nullable = false,precision=10,scale=2)
	private double charges = SyrDefault.DOUBLE;
	@Column(name="offer",nullable = false,precision=10,scale=2)
	private double offer = SyrDefault.DOUBLE;
	@Column(name="net_amount",nullable = false,precision=10,scale=2)
	private double netReceivedAmount = SyrDefault.DOUBLE;
	@Column(name = "payment_status",nullable = false,length = 1)
	private String paymentStatus = SyrStatus.ACTIVE.Status();
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(name = "add_date", nullable = false)
	private LocalDate addDate	= SyrDefault.CURRENT_DATE();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "mod_date", nullable = false)
	private LocalDateTime modDate = SyrDefault.CURRENT_DATE_TIME();
	@Column(name="ride_rating",nullable = false,precision=10,scale=2)
	private double rideRating = SyrDefault.DOUBLE;
	@Column(name="rider_rating",nullable = false,precision=10,scale=2)
	private double riderRating = SyrDefault.DOUBLE;
	@Column(name = "payment_accept_mode",nullable = false,length = 3)
	private String paymentAcceptMode = SyrPaymentMode.ANY.Get();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_id",referencedColumnName="user_vehicle_id",nullable =false)
	private UserVehicle vehicle;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="ride")
	private Set<CoRider> coRiders;

	public String getRideNo() {
		return rideNo;
	}

	public void setRideNo(String rideNo) {
		this.rideNo = rideNo;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public LocalDate getRideDate() {
		return rideDate;
	}

	public void setRideDate(LocalDate rideDate) {
		this.rideDate = rideDate;
	}

	public String getRideType() {
		return rideType;
	}

	public void setRideType(String rideType) {
		this.rideType = rideType;
	}

	public int getSeatAvailability() {
		return seatAvailability;
	}

	public void setSeatAvailability(int seatAvailability) {
		this.seatAvailability = seatAvailability;
	}

	public String getAcFlag() {
		return acFlag;
	}

	public void setAcFlag(String acFlag) {
		this.acFlag = acFlag;
	}

	public double getTotalFuelAmount() {
		return totalFuelAmount;
	}

	public void setTotalFuelAmount(double totalFuelAmount) {
		this.totalFuelAmount = totalFuelAmount;
	}

	public double getPerHeadAmount() {
		return perHeadAmount;
	}

	public void setPerHeadAmount(double perHeadAmount) {
		this.perHeadAmount = perHeadAmount;
	}

	public String getRideStatus() {
		return rideStatus;
	}

	public void setRideStatus(String rideStatus) {
		this.rideStatus = rideStatus;
	}

	public String getGenderPreference() {
		return genderPreference;
	}

	public void setGenderPreference(String genderPreference) {
		this.genderPreference = genderPreference;
	}

	public String getRideRequestType() {
		return rideRequestType;
	}

	public void setRideRequestType(String rideRequestType) {
		this.rideRequestType = rideRequestType;
	}

	public float getFromLatitude() {
		return fromLatitude;
	}

	public void setFromLatitude(float fromLatitude) {
		this.fromLatitude = fromLatitude;
	}

	public float getFromLongitude() {
		return fromLongitude;
	}

	public void setFromLongitude(float fromLongitude) {
		this.fromLongitude = fromLongitude;
	}

	public String getFromLocality() {
		return fromLocality;
	}

	public void setFromLocality(String fromLocality) {
		this.fromLocality = fromLocality;
	}

	public String getFromSubLocality() {
		return fromSubLocality;
	}

	public void setFromSubLocality(String fromSubLocality) {
		this.fromSubLocality = fromSubLocality;
	}

	public LocalDateTime getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(LocalDateTime startingTime) {
		this.startingTime = startingTime;
	}

	public float getToLatitude() {
		return toLatitude;
	}

	public void setToLatitude(float toLatitude) {
		this.toLatitude = toLatitude;
	}

	public float getToLongitude() {
		return toLongitude;
	}

	public void setToLongitude(float toLongitude) {
		this.toLongitude = toLongitude;
	}

	public String getToLocality() {
		return toLocality;
	}

	public void setToLocality(String toLocality) {
		this.toLocality = toLocality;
	}

	public String getToSubLocality() {
		return toSubLocality;
	}

	public void setToSubLocality(String toSubLocality) {
		this.toSubLocality = toSubLocality;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public int getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}

	public double getTravelDistance() {
		return travelDistance;
	}

	public void setTravelDistance(double travelDistance) {
		this.travelDistance = travelDistance;
	}

	public int getInviteCount() {
		return inviteCount;
	}

	public void setInviteCount(int inviteCount) {
		this.inviteCount = inviteCount;
	}

	public int getInvitationJoineeCount() {
		return invitationJoineeCount;
	}

	public void setInvitationJoineeCount(int invitationJoineeCount) {
		this.invitationJoineeCount = invitationJoineeCount;
	}

	public int getJoinInRequestCount() {
		return joinInRequestCount;
	}

	public void setJoinInRequestCount(int joinInRequestCount) {
		this.joinInRequestCount = joinInRequestCount;
	}

	public int getJoinInRequestAccpetCount() {
		return joinInRequestAccpetCount;
	}

	public void setJoinInRequestAccpetCount(int joinInRequestAccpetCount) {
		this.joinInRequestAccpetCount = joinInRequestAccpetCount;
	}

	public int getCoRidersCount() {
		return coRidersCount;
	}

	public void setCoRidersCount(int coRidersCount) {
		this.coRidersCount = coRidersCount;
	}

	public double getRideAmount() {
		return rideAmount;
	}

	public void setRideAmount(double rideAmount) {
		this.rideAmount = rideAmount;
	}

	public double getCharges() {
		return charges;
	}

	public void setCharges(double charges) {
		this.charges = charges;
	}

	public double getOffer() {
		return offer;
	}

	public void setOffer(double offer) {
		this.offer = offer;
	}

	public double getNetReceivedAmount() {
		return netReceivedAmount;
	}

	public void setNetReceivedAmount(double netReceivedAmount) {
		this.netReceivedAmount = netReceivedAmount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
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

	public double getRideRating() {
		return rideRating;
	}

	public void setRideRating(double rideRating) {
		this.rideRating = rideRating;
	}

	public double getRiderRating() {
		return riderRating;
	}

	public void setRiderRating(double riderRating) {
		this.riderRating = riderRating;
	}

	public UserVehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(UserVehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Set<CoRider> getCoRiders() {
		return coRiders;
	}

	public void setCoRiders(Set<CoRider> coRiders) {
		this.coRiders = coRiders;
	}

	@Override
	public String toString() {
		return "Ride [rideNo=" + rideNo + ", bId=" + bId + ", version=" + version + ", rideDate=" + rideDate
				+ ", rideType=" + rideType + ", seatAvailability=" + seatAvailability + ", acFlag=" + acFlag
				+ ", totalFuelAmount=" + totalFuelAmount + ", perHeadAmount=" + perHeadAmount + ", rideStatus="
				+ rideStatus + ", genderPreference=" + genderPreference + ", rideRequestType=" + rideRequestType
				+ ", fromLatitude=" + fromLatitude + ", fromLongitude=" + fromLongitude + ", fromLocality="
				+ fromLocality + ", fromSubLocality=" + fromSubLocality + ", startingTime=" + startingTime
				+ ", toLatitude=" + toLatitude + ", toLongitude=" + toLongitude + ", toLocality=" + toLocality
				+ ", toSubLocality=" + toSubLocality + ", endTime=" + endTime + ", travelTime=" + travelTime
				+ ", travelDistance=" + travelDistance + ", inviteCount=" + inviteCount + ", invitationJoineeCount="
				+ invitationJoineeCount + ", joinInRequestCount=" + joinInRequestCount + ", joinInRequestAccpetCount="
				+ joinInRequestAccpetCount + ", coRidersCount=" + coRidersCount + ", rideAmount=" + rideAmount
				+ ", charges=" + charges + ", offer=" + offer + ", netReceivedAmount=" + netReceivedAmount
				+ ", paymentStatus=" + paymentStatus + ", addDate=" + addDate + ", modDate=" + modDate + ", rideRating="
				+ rideRating + ", riderRating=" + riderRating + "]";
	}
}
