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
import javax.persistence.NamedSubgraph;
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
import com.syr.defaults.SyrRequestStatus;
import com.syr.defaults.SyrStatus;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
@Entity
@Table(name = "SYR_RIDE_JOIN_REQUESTS")
@NamedEntityGraphs({
	@NamedEntityGraph(
			name = "requestDetails",
			attributeNodes={
					@NamedAttributeNode(value="ride",subgraph="ride_details_full"),
					@NamedAttributeNode(value = "joineeUser"/*,subgraph="userDetails"*/)
			},
			subgraphs={
					@NamedSubgraph(
						name="ride_details_full",
						attributeNodes={
							@NamedAttributeNode(value="vehicle"),
							@NamedAttributeNode(value="coRiders"),
						}
					),
					/*@NamedSubgraph(
						name = "userDetails",
						attributeNodes={
							@NamedAttributeNode(value="vehicle"),
							@NamedAttributeNode(value="coRiders"),	
						}
					)*/
			}
	),
	@NamedEntityGraph(
			name = "request",
			attributeNodes={
					@NamedAttributeNode(value="ride"),
					@NamedAttributeNode(value = "joineeUser")
			}
	)
})
public class RideJoinRequest extends AbstractPersistentObject{

	@Id
	@GenericGenerator(name = "jr_seq", strategy = "com.syr.id_generators.RideIdGenerator", parameters = @Parameter(name = "rideReqType", value = "RJ"))
	@GeneratedValue(generator = "jr_seq")
	@Column(name = "request_id",nullable = false,length = 20)
	private String requestId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "business_id", nullable = false, length = 100)
	private String bId = IdGenerator.createId();
	
	@Column(name = "request_status",nullable = false,length = 1)
	private String requestStatus = SyrRequestStatus.REQUESTED.Get();
	@Column(name = "ride_status",nullable = false,length = 1)
	private String rideStatus= SyrStatus.OPEN.Status();
	@Column(name = "message_to_host",nullable = false,columnDefinition = "TEXT")
	private String messageToHost = SyrDefault.HASH;
	@Column(name = "no_of_joinees",nullable = false)
	private int numberOfJoinees;
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
	@Column(name = "joining_time", nullable = false)
	private LocalDateTime startingTime = SyrDefault.CURRENT_DATE_TIME();
	@NotNull(message = "toLat.notnull")
	@Column(name="to_latitude",nullable = false,precision=12,scale=8)
	private float toLatitude;
	@NotNull(message = "toLong.notnull")
	@Column(name="to_longitude",nullable = false,precision=12,scale=8)
	private float toLongitude;
	@NotNull(message = "toLoc.notnull")
	@Column(name="to_locality",nullable = false,columnDefinition="TEXT")
	private String toLocality =SyrDefault.HASH;
	@NotNull(message = "toSubLoc.notnull")
	@Column(name="to_sub_locality",nullable = false,columnDefinition="TEXT")
	private String toSubLocality = SyrDefault.HASH;
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(name = "add_date", nullable = false)
	private LocalDate addDate	= SyrDefault.CURRENT_DATE();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "mod_date", nullable = false)
	private LocalDateTime modDate = SyrDefault.CURRENT_DATE_TIME();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ride_no")
	private Ride ride;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "joinee_user_id",referencedColumnName = "user_id")
	private User joineeUser;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getRideStatus() {
		return rideStatus;
	}

	public void setRideStatus(String rideStatus) {
		this.rideStatus = rideStatus;
	}

	public String getMessageToHost() {
		return messageToHost;
	}

	public void setMessageToHost(String messageToHost) {
		this.messageToHost = messageToHost;
	}

	public int getNumberOfJoinees() {
		return numberOfJoinees;
	}

	public void setNumberOfJoinees(int numberOfJoinees) {
		this.numberOfJoinees = numberOfJoinees;
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

	public User getJoineeUser() {
		return joineeUser;
	}

	public void setJoineeUser(User joineeUser) {
		this.joineeUser = joineeUser;
	}

	@Override
	public String toString() {
		return "RideJoinRequest [requestId=" + requestId + ", bId=" + bId + ", requestStatus=" + requestStatus
				+ ", rideStatus=" + rideStatus + ", messageToHost=" + messageToHost + ", numberOfJoinees="
				+ numberOfJoinees + ", fromLatitude=" + fromLatitude + ", fromLongitude=" + fromLongitude
				+ ", fromLocality=" + fromLocality + ", fromSubLocality=" + fromSubLocality + ", startingTime="
				+ startingTime + ", toLatitude=" + toLatitude + ", toLongitude=" + toLongitude + ", toLocality="
				+ toLocality + ", toSubLocality=" + toSubLocality + ", addDate=" + addDate + ", modDate=" + modDate
				+ "]";
	}
}
