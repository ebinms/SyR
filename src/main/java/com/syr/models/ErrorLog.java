package com.syr.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.syr.defaults.SyrDefault;

@Entity
@Table(
		name = "SYR_ERROR_LOG",
		indexes = {
			@Index(columnList="error_date",name="syr_idx_err_date"),
			@Index(columnList="error_property",name="syr_idx_err_prop")
		}
	)
public class ErrorLog {

	@Id
	@GeneratedValue
	private long errorId;
	
	@Column(name = "error_page",nullable = false,length = 200)
	private String errorPage = "#";
	@Column(name = "error_fn",nullable = false,length = 200)
	private String errorFunction = "#";
	@Column(name = "error_url",nullable = false,columnDefinition = "TEXT")
	private String errorUrl;
	@Column(name = "response_code",nullable = false,length = 10)
	private String responseCode;
	@Column(name = "error_prop",nullable = false,length = 200)
	private String errorProperty;
	@Column(name = "error_desc",nullable = false,columnDefinition="TEXT")
	private String errorDesc;
	@Column(name = "user_id",nullable = false,length = 30)
	private String userId;
	@Column(name = "language",nullable = false,length = 10)
	private String language = "en";
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "error_date",nullable = false)
	private LocalDate errorDate = SyrDefault.CURRENT_DATE();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "error_time",nullable = false)
	private LocalDateTime errorTime = SyrDefault.CURRENT_DATE_TIME();
	
	public ErrorLog(String errorPage, String errorFunction, String errorUrl, String responseCode,
			String errorProperty, String errorDesc, String userId,String language)
	{
		super();
		this.errorPage = errorPage;
		this.errorFunction = errorFunction;
		this.errorUrl = errorUrl;
		this.responseCode = responseCode;
		this.errorProperty = errorProperty;
		this.errorDesc = errorDesc;
		this.userId = userId;
		this.language	= language;
	}
	
	public long getErrorId() {
		return errorId;
	}
	public void setErrorId(long errorId) {
		this.errorId = errorId;
	}
	public String getErrorPage() {
		return errorPage;
	}
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
	public String getErrorFunction() {
		return errorFunction;
	}
	public void setErrorFunction(String errorFunction) {
		this.errorFunction = errorFunction;
	}
	public String getErrorUrl() {
		return errorUrl;
	}
	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getErrorProperty() {
		return errorProperty;
	}
	public void setErrorProperty(String errorProperty) {
		this.errorProperty = errorProperty;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LocalDate getErrorDate() {
		return errorDate;
	}
	public void setErrorDate(LocalDate errorDate) {
		this.errorDate = errorDate;
	}
	public LocalDateTime getErrorTime() {
		return errorTime;
	}
	public void setErrorTime(LocalDateTime errorTime) {
		this.errorTime = errorTime;
	}
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "ErrorLog [errorId=" + errorId + ", errorPage=" + errorPage + ", errorFunction=" + errorFunction
				+ ", errorUrl=" + errorUrl + ", responseCode=" + responseCode + ", errorProperty=" + errorProperty
				+ ", errorDesc=" + errorDesc + ", userId=" + userId + ", errorDate=" + errorDate + ", errorTime="
				+ errorTime + "]";
	}
}
