package com.syr.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "SYR_USER_EMAILS")
public class UserEmail extends AbstractPersistentObject {

	@Id
	@NotNull(message = "email.notnull")
	@Column(name = "email_id", nullable = false, length = 200)
	private String emailId;
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "business_id", nullable = false, length = 100)
	private String bId = IdGenerator.createId();
	@Column(name = "dnd_status", nullable = false, length = 1)
	private String dndStatus = SyrStatus.NO.Status();
	@Column(name = "email_status", nullable = false, length = 1)
	private String emailStatus = SyrStatus.ACTIVE.Status();
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "add_date", nullable = false)
	private LocalDate addDate = SyrDefault.CURRENT_DATE();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "mod_date", nullable = false)
	private LocalDateTime modDate = SyrDefault.CURRENT_DATE_TIME();
	
	@JsonBackReference(value = "u_2_ue")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Override
	public String toString() {
		return "UserEmail [emailId=" + emailId + ", bId=" + bId + ", dndStatus=" + dndStatus + ", emailStatus="
				+ emailStatus + ", addDate=" + addDate + ", modDate=" + modDate + "]";
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public String getDndStatus() {
		return dndStatus;
	}

	public void setDndStatus(String dndStatus) {
		this.dndStatus = dndStatus;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
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
}
