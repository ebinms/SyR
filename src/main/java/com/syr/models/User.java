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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.syr.app_config.AbstractPersistentObject;
import com.syr.app_config.IdGenerator;
import com.syr.defaults.SyrDefault;
import com.syr.defaults.SyrEmploymentStatus;
import com.syr.defaults.SyrMaritalStatus;
import com.syr.defaults.SyrStatus;
import com.syr.defaults.SyrUserPrevilage;

/**
 * @author Ebin
 * @on 26-Sep-2018
 * @version 0.0
 */
@Entity
@Table(name = "SYR_USERS")
@NamedEntityGraphs({
		@NamedEntityGraph(name = "userDetails", attributeNodes = { @NamedAttributeNode(value = "userPhones"),
				@NamedAttributeNode(value = "userEmails") }) })
public class User extends AbstractPersistentObject {

	@Id
	@GenericGenerator(name = "user_seq", strategy = "com.syr.id_generators.MasterIdGenerator", parameters = @Parameter(name = "masterType", value = "USER"))
	@GeneratedValue(generator = "user_seq")
	@Column(name = "user_id", nullable = false)
	private long userId;
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "business_id", nullable = false, length = 100)
	private String bId = IdGenerator.createId();
	@NotNull(message = "name.notnull")
	@Column(name = "first_name", nullable = false, length = 100)
	private String firstName;
	@NotNull(message = "name.notnull")
	@Column(name = "last_name", nullable = false, length = 100)
	private String lastName;
	@NotNull(message = "dob.notnull")
	@Past(message = "dob.past")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dob", nullable = false)
	private LocalDate dob;
	@NotNull(message = "gender.notnull")
	@Size(min = 1, max = 1, message = "gender.size")
	@Column(name = "gender", nullable = false, length = 1)
	private String gender;
	@Column(name = "marital_status", nullable = false, length = 1)
	private String maritalStatus = SyrMaritalStatus.SINGLE.Status();
	@Column(name = "employment_status", nullable = false, length = 1)
	private String employmentStatus = SyrEmploymentStatus.SALARIED.Status();
	@Column(name = "user_status", nullable = false, length = 1)
	private String userStatus = SyrStatus.ACTIVE.Status();
	@Column(name = "host_rating", nullable = false, precision = 2, scale = 1)
	private double hostRating = SyrDefault.DOUBLE;
	@Column(name = "host_rate_count", nullable = false, precision = 10, scale = 1)
	private double hostRateCount = SyrDefault.DOUBLE;
	@Column(name = "host_review_count", nullable = false, precision = 10, scale = 1)
	private double hostReviewCount = SyrDefault.DOUBLE;
	@Column(name = "guest_rating", nullable = false, precision = 2, scale = 1)
	private double guestRating = SyrDefault.DOUBLE;
	@Column(name = "guest_rate_count", nullable = false, precision = 10, scale = 1)
	private double guestRateCount = SyrDefault.DOUBLE;
	@Column(name = "guest_review_count", nullable = false, precision = 10, scale = 1)
	private double guestReviewCount = SyrDefault.DOUBLE;
	@Column(name = "user_previlage", nullable = false, length = 2)
	private String userPrevilage = SyrUserPrevilage.NORMAL.Type();
	@Column(name = "user_type", nullable = false, length = 2)
	private String userType = SyrUserPrevilage.NORMAL.Type(); // Admin[AD] Or
																// Normal[NR]
	@NotNull(message = "email.notnull")
	@Email(message = "email.valid", regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
	@Column(name = "primary_email", nullable = false, length = 200)
	private String primaryEmail;
	@NotNull(message = "country-code.notnull")
	@Column(name = "primary_mob_country_code", nullable = false)
	private long primaryMobCountryCode;
	@NotNull(message = "mobile-no.notnull")
	@Column(name = "primary_mobile_no", nullable = false)
	private long primaryMobileNo;
	@Column(name = "mob_verfication_status", nullable = false, length = 1)
	private String mobileVerificationStatus = SyrStatus.ACTIVE.Status();
	@Column(name = "email_verfication_status", nullable = false, length = 1)
	private String emailVerificationStatus = SyrStatus.ACTIVE.Status();
	@Column(name = "user_block_count", nullable = false)
	private int userBlockCount = SyrDefault.INT;
	@Column(name = "reg_channel", nullable = false, length = 4)
	private String registrationChannel = SyrDefault.HASH;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "add_date", nullable = false)
	private LocalDate addDate = SyrDefault.CURRENT_DATE();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "mod_date", nullable = false)
	private LocalDateTime modDate = SyrDefault.CURRENT_DATE_TIME();

	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonManagedReference(value = "u_2_up")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserPhone> userPhones;

	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonManagedReference(value = "u_2_ue")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserEmail> userEmails;

	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonManagedReference(value = "u_2_ul")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserLogin> userLogins;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Transient
	private String password = "#";

	@Override
	public String toString() {
		return "User [userId=" + userId + ", bId=" + bId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dob=" + dob + ", gender=" + gender + ", maritalStatus=" + maritalStatus + ", employmentStatus="
				+ employmentStatus + ", userStatus=" + userStatus + ", hostRating=" + hostRating + ", hostRateCount="
				+ hostRateCount + ", hostReviewCount=" + hostReviewCount + ", guestRating=" + guestRating
				+ ", guestRateCount=" + guestRateCount + ", guestReviewCount=" + guestReviewCount + ", userPrevilage="
				+ userPrevilage + ", userType=" + userType + ", primaryEmail=" + primaryEmail
				+ ", primaryMobCountryCode=" + primaryMobCountryCode + ", primaryMobileNo=" + primaryMobileNo
				+ ", mobileVerificationStatus=" + mobileVerificationStatus + ", emailVerificationStatus="
				+ emailVerificationStatus + ", userBlockCount=" + userBlockCount + ", registrationChannel="
				+ registrationChannel + ", addDate=" + addDate + ", modDate=" + modDate + ", password=" + password
				+ "]";
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public double getHostRating() {
		return hostRating;
	}

	public void setHostRating(double hostRating) {
		this.hostRating = hostRating;
	}

	public double getHostRateCount() {
		return hostRateCount;
	}

	public void setHostRateCount(double hostRateCount) {
		this.hostRateCount = hostRateCount;
	}

	public double getHostReviewCount() {
		return hostReviewCount;
	}

	public void setHostReviewCount(double hostReviewCount) {
		this.hostReviewCount = hostReviewCount;
	}

	public double getGuestRating() {
		return guestRating;
	}

	public void setGuestRating(double guestRating) {
		this.guestRating = guestRating;
	}

	public double getGuestRateCount() {
		return guestRateCount;
	}

	public void setGuestRateCount(double guestRateCount) {
		this.guestRateCount = guestRateCount;
	}

	public double getGuestReviewCount() {
		return guestReviewCount;
	}

	public void setGuestReviewCount(double guestReviewCount) {
		this.guestReviewCount = guestReviewCount;
	}

	public String getUserPrevilage() {
		return userPrevilage;
	}

	public void setUserPrevilage(String userPrevilage) {
		this.userPrevilage = userPrevilage;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	public long getPrimaryMobCountryCode() {
		return primaryMobCountryCode;
	}

	public void setPrimaryMobCountryCode(long primaryMobCountryCode) {
		this.primaryMobCountryCode = primaryMobCountryCode;
	}

	public long getPrimaryMobileNo() {
		return primaryMobileNo;
	}

	public void setPrimaryMobileNo(long primaryMobileNo) {
		this.primaryMobileNo = primaryMobileNo;
	}

	public String getMobileVerificationStatus() {
		return mobileVerificationStatus;
	}

	public void setMobileVerificationStatus(String mobileVerificationStatus) {
		this.mobileVerificationStatus = mobileVerificationStatus;
	}

	public String getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(String emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public int getUserBlockCount() {
		return userBlockCount;
	}

	public void setUserBlockCount(int userBlockCount) {
		this.userBlockCount = userBlockCount;
	}

	public String getRegistrationChannel() {
		return registrationChannel;
	}

	public void setRegistrationChannel(String registrationChannel) {
		this.registrationChannel = registrationChannel;
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

	public Set<UserPhone> getUserPhones() {
		return userPhones;
	}

	public void setUserPhones(Set<UserPhone> userPhones) {
		this.userPhones = userPhones;
	}

	public Set<UserEmail> getUserEmails() {
		return userEmails;
	}

	public void setUserEmails(Set<UserEmail> userEmails) {
		this.userEmails = userEmails;
	}

	public Set<UserLogin> getUserLogins() {
		return userLogins;
	}

	public void setUserLogins(Set<UserLogin> userLogins) {
		this.userLogins = userLogins;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
