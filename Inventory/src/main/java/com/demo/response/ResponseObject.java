package com.demo.response;

import java.util.List;
import com.demo.dto.CompanyDTO;

public class ResponseObject {
	private String sucessMessage;
	private String failureMessage;
	private List<CompanyDTO> companyList;
	
	
	public String getSucessMessage() {
		return sucessMessage;
	}
	public void setSucessMessage(String sucessMessage) {
		this.sucessMessage = sucessMessage;
	}
	public String getFailureMessage() {
		return failureMessage;
	}
	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}
	public List<CompanyDTO> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<CompanyDTO> companyList) {
		this.companyList = companyList;
	}
}
