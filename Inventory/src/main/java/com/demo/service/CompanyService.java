package com.demo.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.bo.CompanyBO;
import com.demo.dao.CompanyCustomized;
import com.demo.dao.CompanyDrugCustomized;
import com.demo.entity.Company;
import com.demo.exception.CompanyException;
import com.demo.exception.CompanyNotFoundException;

@Component
public class CompanyService {
	@Autowired
	CompanyBO bo;

	Logger log = Logger.getLogger(CompanyService.class.getName());
	
	public Company insertACompany(Company addCompany) throws CompanyException{
		log.info("Add method triggered.");
		try
		{
			log.info("Record added successfully");
			return bo.insert(addCompany);
		}
		catch (CompanyException e)
		{
			log.error("Error when adding company details.", e);
			throw new CompanyException("Company name invalid");
		}
	}

	public Company updateACompany(Company updateCompany) throws CompanyException{
		log.info("Update method triggered.");
		try
		{
			log.info("Record updated successfully");
			return bo.update(updateCompany);
		}
		catch (CompanyException e)
		{
			log.error("Error when updating company details.", e);
			throw new CompanyException("Company name invalid");
		}
	}
	
	public Company findACompany(int companyId) throws CompanyNotFoundException{
		log.info("Find by id method triggered.");
		try
		{
			log.info("Record fetched successfully");
			return bo.findCompany(companyId);
		}
		catch (CompanyNotFoundException e)
		{
			log.error("Error when fetching company details.", e);
			throw new CompanyNotFoundException (companyId + "- Company not found");
		}
	}

	public List<Company> findAllCompanies() throws CompanyNotFoundException{
		log.info("Find all method triggered.");
		try
		{
			log.info("Records fetched successfully");
			return bo.findCompanies();	
		}
		catch (CompanyNotFoundException e)
		{
			log.error("Error when fetching company details.", e);
			throw new CompanyNotFoundException ("Companies not found.");
		}
	}

	// Transaction
	/*
	 * @Autowired CompanyBO bo1;
	 * 
	 * @Autowired DrugBO bo2;
	 * 
	 * @Transactional(propagation =Propagation.REQUIRED) public void transactionDemo
	 * (Company c, Drug d) { System.out.println("Company Add ---- Before");
	 * bo1.insert(c); System.out.println("Company Add ---- After");
	 * System.out.println("Drug Add ---- Before"); bo2.insert(d);
	 * System.out.println("Drug Add ---- After"); }
	 */

	public List<Company> findCompaniesByCompanyId(int companyId1) {
		return bo.findCompaniesById(companyId1);
	}

	public List<Company> findCompanyByName(String companyName1) {
		return bo.findByCompanyNames(companyName1);
	}

	public List<CompanyCustomized> findByCustomizedCompanyName(String companyName1) {
		return bo.findByCompanyNameCustomized(companyName1);
	}

	public List<Company> findAllOrderByCompanyNames() {
		return bo.findAllOrderByCompanyName();
	}

	public List<Company> findByCity(String city) {
		return bo.findByCity(city);
	}

	public List<Company> findCompanyWithDrug() {
		return bo.findCompanyWithDrugs();
	}

	public List<Company> findCompanyWithDrugLeft() {
		return bo.findCompanyWithDrugsLeft();
	}

	public List<CompanyDrugCustomized> findByCompanyDrugsCustomized() {
		return bo.findByCompanyDrugCustomized();
	}

}