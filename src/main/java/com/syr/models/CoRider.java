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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.syr.app_config.IdGenerator;
import com.syr.defaults.SyrDefault;
import com.syr.defaults.SyrRideRequestType;
import com.syr.defaults.SyrStatus;

/**
 * @author Ebin
 * @on 02-Oct-2018
 * @version 0.0
 */
@Entity
@Table(name = "SYR_CO_RIDERS",indexes = {
		@Index(columnList="to_latitude,to_longitude",name="idx_destination_lat_long")
	})
@NamedEntityGraphs({
	@NamedEntityGraph(
		name = "coRiderUser",
		attributeNodes={
			@NamedAttributeNode(value="user",subgraph="userDetails")	
		},
		subgraphs={
			@NamedSubgraph(
				name = "userDetails",
				attributeNodes={
					@NamedAttributeNode(value="userPhones"),
					@NamedAttributeNode(value="userEmails")
				}
			)	
		}
	)
})
public class CoRider {

	@Id
	@GenericGenerator(name = "cr_seq", strategy = "com.syr.id_generators.RideIdGenerator", parameters = @Parameter(name = "rideReqType", value = "CR"))
	@GeneratedValue(generator = "cr_seq")
	@Column(name = "id",nullable = false)
	private long coRiderId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "business_id", nullable = false, length = 100)
	private String bId = IdGenerator.createId();
	
	@Column(name = "no_of_joinees",nullable = false)
	private int numberOfJoinees;
	@NotNull(message = "fLat.notnull")
	@Column(name="joining_latitude",nullable = false,precision=12,scale=8)
	private float joiningLatitude;
	@NotNull(message = "fLong.notnull")
	@Column(name="joining_longitude",nullable = false,precision=12,scale=8)
	private float joiningLongitude;
	@NotNull(message = "fLoc.notnull")
	@Column(name="joining_locality",nullable = false,columnDefinition="TEXT")
	private String joiningLocality;
	@NotNull(message = "fSubLoc.notnull")
	@Column(name="joining_sub_locality",nullable = false,columnDefinition="TEXT")
	private String joiningSubLocality;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "joining_time", nullable = false)
	private LocalDateTime joiningTime = SyrDefault.CURRENT_DATE_TIME();
	@NotNull(message = "tLat.notnull")
	@Column(name="to_latitude",nullable = false,precision=12,scale=8)
	private float toLatitude;
	@NotNull(message = "tLong.notnull")
	@Column(name="to_longitude",nullable = false,precision=12,scale=8)
	private float toLongitude;
	@NotNull(message = "tLoc.notnull")
	@Column(name="to_locality",nullable = false,columnDefinition="TEXT")
	private String toLocality;
	@NotNull(message = "tSubLoc.notnull")
	@Column(name="to_sub_locality",nullable = false,columnDefinition="TEXT")
	private String toSubLocality;
	@Column(name="ride_amount",nullable = false,precision=10,scale=2)
	private double rideAmount = SyrDefault.DOUBLE;
	@Column(name="charges",nullable = false,precision=10,scale=2)
	private double charges = SyrDefault.DOUBLE;
	@Column(name="net_amount",nullable = false,precision=10,scale=2)
	private double netReceivedAmount = SyrDefault.DOUBLE;
	@Column(name = "payment_status",nullable = false,length = 1)
	private String paymentStatus = SyrStatus.ACTIVE.Status();
	@Column(name = "ride_status",nullable = false,length = 1)
	private String rideStatus = SyrStatus.ACTIVE.Status();
	@Column(name="ride_rating",nullable = false,precision=10,scale=2)
	private double rideRating = SyrDefault.DOUBLE;
	@Column(name="rider_rating",nullable = false,precision=10,scale=2)
	private double riderRating = SyrDefault.DOUBLE;
	@Column(name="co_rider_rating",nullable = false,precision=10,scale=2)
	private double coRiderRating = SyrDefault.DOUBLE;
	@Column(name = "join_req_type",nullable = false,length = 1)
	private String joinReqType = SyrRideRequestType.JOINREQUEST.Get();
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "add_date", nullable = false)
	private LocalDate addDate	= SyrDefault.CURRENT_DATE();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "mod_date", nullable = false)
	private LocalDateTime modDate = SyrDefault.CURRENT_DATE_TIME();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ride_no")
	private Ride ride;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public long getCoRiderId() {
		return coRiderId;
	}

	public void setCoRiderId(long coRiderId) {
		this.coRiderId = coRiderId;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public float getJoiningLatitude() {
		return joiningLatitude;
	}

	public void setJoiningLatitude(float joiningLatitude) {
		this.joiningLatitude = joiningLatitude;
	}

	public float getJoiningLongitude() {
		return joiningLongitude;
	}

	public void setJoiningLongitude(float joiningLongitude) {
		this.joiningLongitude = joiningLongitude;
	}

	public String getJoiningLocality() {
		return joiningLocality;
	}

	public void setJoiningLocality(String joiningLocality) {
		this.joiningLocality = joiningLocality;
	}

	public String getJoiningSubLocality() {
		return joiningSubLocality;
	}

	public void setJoiningSubLocality(String joiningSubLocality) {
		this.joiningSubLocality = joiningSubLocality;
	}

	public LocalDateTime getJoiningTime() {
		return joiningTime;
	}

	public void setJoiningTime(LocalDateTime joiningTime) {
		this.joiningTime = joiningTime;
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

	public String getRideStatus() {
		return rideStatus;
	}

	public void setRideStatus(String rideStatus) {
		this.rideStatus = rideStatus;
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

	public double getCoRiderRating() {
		return coRiderRating;
	}

	public void setCoRiderRating(double coRiderRating) {
		this.coRiderRating = coRiderRating;
	}

	public String getJoinReqType() {
		return joinReqType;
	}

	public void setJoinReqType(String joinReqType) {
		this.joinReqType = joinReqType;
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

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNumberOfJoinees() {
		return numberOfJoinees;
	}

	public void setNumberOfJoinees(int numberOfJoinees) {
		this.numberOfJoinees = numberOfJoinees;
	}

	@Override
	public String toString() {
		return "CoRider [coRiderId=" + coRiderId + ", bId=" + bId + ", numberOfJoinees=" + numberOfJoinees
				+ ", joiningLatitude=" + joiningLatitude + ", joiningLongitude=" + joiningLongitude
				+ ", joiningLocality=" + joiningLocality + ", joiningSubLocality=" + joiningSubLocality
				+ ", joiningTime=" + joiningTime + ", toLatitude=" + toLatitude + ", toLongitude=" + toLongitude
				+ ", toLocality=" + toLocality + ", toSubLocality=" + toSubLocality + ", rideAmount=" + rideAmount
				+ ", charges=" + charges + ", netReceivedAmount=" + netReceivedAmount + ", paymentStatus="
				+ paymentStatus + ", rideStatus=" + rideStatus + ", rideRating=" + rideRating + ", riderRating="
				+ riderRating + ", coRiderRating=" + coRiderRating + ", joinReqType=" + joinReqType + ", addDate="
				+ addDate + ", modDate=" + modDate + "]";
	}
	
}
