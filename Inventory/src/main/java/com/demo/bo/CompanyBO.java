package com.demo.bo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.dao.CompanyCustomized;
import com.demo.dao.CompanyDrugCustomized;
import com.demo.dao.CompanyRepository;
import com.demo.entity.Company;
import com.demo.exception.CompanyException;
import com.demo.exception.CompanyNotFoundException;

@Component
public class CompanyBO {

	@Autowired
	CompanyRepository recomp;

	public Company insert(Company c) throws CompanyException{
		if(c.getCompanyName()==null || c.getCompanyName().isEmpty())
		{
			throw new CompanyException("Company name cannot be empty!!!");
		}
		return recomp.save(c);
	}
	
	public Company update(Company c) throws CompanyException {

	    if (c.getCompanyName() == null || c.getCompanyName().isEmpty()) {
	        throw new CompanyException("Company name cannot be empty.");
	    }
	    return recomp.save(c);
	}

	public Company findCompany(int companyId) throws CompanyNotFoundException{
		Optional<Company> comp = recomp.findById(companyId);
		if(comp.isPresent())
		{
			return comp.get();
		}
		else
		{
			throw new CompanyNotFoundException(companyId + "- Company not found.");
		}
	}

	public List<Company> findCompanies() throws CompanyNotFoundException{
		try
		{
		return recomp.findAll();
		}
		catch(Exception e)
		{
			throw new CompanyNotFoundException("Companies not found. ", e);
		}
	}

	// Query

	public List<Company> findCompaniesById(int companyId1) {
		return recomp.findCompanyById(companyId1);
	}

	public List<Company> findByCompanyNames(String companyName1) {
		return recomp.findByName(companyName1);
	}

	public List<CompanyCustomized> findByCompanyNameCustomized(String companyName1) {
		return recomp.findByCompanyNameCustomized(companyName1);
	}

	public List<Company> findAllOrderByCompanyName() {
		return recomp.findAllOrderByNameDescending();
	}

	public List<Company> findByCity(String city) {
		return recomp.findByCity(city);
	}

	public List<Company> findCompanyWithDrugs() {
		return recomp.findCompanyWithDrugs();
	}

	public List<Company> findCompanyWithDrugsLeft() {
		return recomp.findCompanyWithDrugsLeft();
	}

	public List<CompanyDrugCustomized> findByCompanyDrugCustomized() {
		return recomp.findByCompanyDrugCustomized();
	}

}
