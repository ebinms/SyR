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
 * @on 03-Oct-2018
 * @version 0.0
 */
@Entity
@Table(name = "SYR_USER_PAYABLE_SPLIT")
public class UserPayableSplit extends AbstractPersistentObject{

	@Id
	@GenericGenerator(name = "split_seq", strategy = "com.syr.id_generators.MasterIdGenerator", parameters = @Parameter(name = "masterType", value = "USSP"))
	@GeneratedValue(generator = "split_seq")
	@Column(name = "split_txn_no", nullable = false)
	private long splitTxnNo;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "business_id", nullable = false, length = 100)
	private String bId = IdGenerator.createId();
	
	@Column(name = "debit_credit",nullable = false,length=1)
	private String debitCredit;
	@Column(name = "payment_amt",nullable = false,precision = 10,scale = 2)
	private double payableAmt;
	@Column(name = "commission",nullable = false,precision = 10,scale = 2)
	private double commission = SyrDefault.DOUBLE;
	@Column(name = "charges",nullable = false,precision = 10,scale = 2)
	private double charges = SyrDefault.DOUBLE;
	@Column(name = "ref_txn_no",nullable = false,length=20)
	private String refTxnNo = SyrDefault.HASH;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "add_date", nullable = false)
	private LocalDate addDate = SyrDefault.CURRENT_DATE();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "mod_date", nullable = false)
	private LocalDateTime modDate = SyrDefault.CURRENT_DATE_TIME();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",referencedColumnName = "user_id")
	private User user;

	public long getSplitTxnNo() {
		return splitTxnNo;
	}

	public void setSplitTxnNo(long splitTxnNo) {
		this.splitTxnNo = splitTxnNo;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public String getDebitCredit() {
		return debitCredit;
	}

	public void setDebitCredit(String debitCredit) {
		this.debitCredit = debitCredit;
	}

	public double getPayableAmt() {
		return payableAmt;
	}

	public void setPayableAmt(double payableAmt) {
		this.payableAmt = payableAmt;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public double getCharges() {
		return charges;
	}

	public void setCharges(double charges) {
		this.charges = charges;
	}

	public String getRefTxnNo() {
		return refTxnNo;
	}

	public void setRefTxnNo(String refTxnNo) {
		this.refTxnNo = refTxnNo;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserPayableSplit [splitTxnNo=" + splitTxnNo + ", bId=" + bId + ", debitCredit=" + debitCredit
				+ ", payableAmt=" + payableAmt + ", commission=" + commission + ", charges=" + charges + ", refTxnNo="
				+ refTxnNo + ", addDate=" + addDate + ", modDate=" + modDate + "]";
	}
}
