package com.demo.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Company")
@EntityListeners(AuditingEntityListener.class)
@NamedQueries ({@NamedQuery (name="Company.findAllOrderByNameDescending",
query="select c from Company c order by c.companyName DESC")})
//@NamedQuery(name="Company.findByCity", query="select c from Company c where c.city=:city")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyId;

	@NotBlank
	@Column(unique = true, name = "Company_Name")
	String companyName;

	
	@NotNull
	private Long contactNumber;

	@NotBlank
	String city;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
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
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", contactNumber=" + contactNumber
				+ ", city=" + city + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "company")
	private List<Drug> drug;

	public List<Drug> getDrug() {
		return drug;
	}

	public void setDrug(List<Drug> drug) {
		this.drug = drug;
	}
}
