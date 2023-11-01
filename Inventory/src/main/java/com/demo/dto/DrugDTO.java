package com.demo.dto;

import java.io.Serializable;
import java.util.Date;

public class DrugDTO implements Serializable{
	private int drugId;
	private String drugName;
	private int cost;
	private Date createdAt;
	private Date updatedAt;
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
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
		return "DrugDTO [drugId=" + drugId + ", drugName=" + drugName + ", cost=" + cost + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	
}
