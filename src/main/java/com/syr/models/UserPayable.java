package com.syr.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.syr.app_config.AbstractPersistentObject;
import com.syr.app_config.IdGenerator;
import com.syr.defaults.SyrDefault;
import com.syr.defaults.SyrStatus;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
@Entity
@Table(name = "SYR_USER_PAYABLES")
public class UserPayable extends AbstractPersistentObject{

	@Id
	@Column(name = "user_id",nullable = false)
	private long userId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "business_id", nullable = false, length = 100)
	private String bId = IdGenerator.createId();
	
	@NotNull(message="payableAmt.notnull")
	@Column(name = "payable_amt",nullable = false,precision=10,scale = 2)
	private double payableAmount = SyrDefault.DOUBLE;
	@Column(name = "pending_request_amount",nullable = false,precision=10,scale = 2)
	private double requestedAmount = SyrDefault.DOUBLE;
	@Column(name = "status",length = 1,nullable = false)
	private String status = SyrStatus.ACTIVE.Status();
	@Column(name = "account_code",nullable = false,length = 40)
	private String accountCode = "#";
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "add_date", nullable = false)
	private LocalDate addDate = SyrDefault.CURRENT_DATE();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "mod_date", nullable = false)
	private LocalDateTime modDate = SyrDefault.CURRENT_DATE_TIME();
	
	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Override
	public String toString() {
		return "UserPayable [userId=" + userId + ", bId=" + bId + ", payableAmount=" + payableAmount
				+ ", requestedAmount=" + requestedAmount + ", status=" + status + ", accountCode=" + accountCode + "]";
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public double getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(double payableAmount) {
		this.payableAmount = payableAmount;
	}

	public double getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(double requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

}
