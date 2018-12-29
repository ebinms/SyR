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
@Table(name = "SYR_USER_LOGINS")
public class UserLogin extends AbstractPersistentObject{

	@Id
	@Column(name = "user_name", nullable = false, length = 200)
	private String userName;
	@NotNull(message = "password.notnull")
	@Column(name = "password", nullable = false, columnDefinition = "TEXT")
	private String password;
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "business_id", nullable = false, length = 100)
	private String bId = IdGenerator.createId();
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "last_login", nullable = false)
	private LocalDateTime lastLogin = SyrDefault.PAST_DEF_DATE;
	@Column(name = "block_status", nullable = false, length = 1)
	private String blockStatus = SyrStatus.NO.Status();
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "last_password_reset", nullable = false)
	private LocalDateTime lastPasswordReset = SyrDefault.PAST_DEF_DATE;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "add_date", nullable = false)
	private LocalDate addDate = SyrDefault.CURRENT_DATE();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "mod_date", nullable = false)
	private LocalDateTime modDate = SyrDefault.CURRENT_DATE_TIME();
	
	@JsonBackReference(value = "u_2_ul")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Override
	public String toString() {
		return "UserLogin [userName=" + userName + ", password=" + password + ", bId=" + bId + ", lastLogin="
				+ lastLogin + ", blockStatus=" + blockStatus + ", lastPasswordReset=" + lastPasswordReset + ", addDate="
				+ addDate + ", modDate=" + modDate + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getBlockStatus() {
		return blockStatus;
	}

	public void setBlockStatus(String blockStatus) {
		this.blockStatus = blockStatus;
	}

	public LocalDateTime getLastPasswordReset() {
		return lastPasswordReset;
	}

	public void setLastPasswordReset(LocalDateTime lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
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
