package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository <Company, Integer>{
	@Query(value = "select c from Company c where c.companyId> :companyId1")
	List <Company> findCompanyById(@Param("companyId1") int companyId);
	
	@Query("select c from Company c where c.companyName LIKE %:companyName1%")
	List<Company> findByName(@Param("companyName1") String companyName);
	
	//Fetch only few columns from company 
	@Query("select c.city as city, c.companyName as companyName from  Company c where c.companyName=:companyName1")
	List<CompanyCustomized> findByCompanyNameCustomized(@Param("companyName1") String companyName);
	
	//Named query
	List<Company> findAllOrderByNameDescending();
	List <Company> findByCity(String city);
	
	//Inner join
	@Query("select c from Company c JOIN c.drug d")
	List <Company> findCompanyWithDrugs();
	
	//Left outer join
	@Query("select c from Company c Left JOIN c.drug d")
	List <Company> findCompanyWithDrugsLeft();
	
	//Customized data by join
	@Query("select c.companyName as companyName, c.city as city from Company c JOIN c.drug d")
	List<CompanyDrugCustomized> findByCompanyDrugCustomized();
}
