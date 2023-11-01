package com.demo.dto;

import java.io.Serializable;
import java.util.Date;

public class CompanyDTO implements Serializable{
	private int companyId;
	private String companyName;
	private Long contactNumber;
	private String city;
	private Date createdAt;
	private Date updatedAt;
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "CompanyDTO [companyId=" + companyId + ", companyName=" + companyName + ", contactNumber="
				+ contactNumber + ", city=" + city + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
