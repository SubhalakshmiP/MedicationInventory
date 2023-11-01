package com.example.demo;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.demo.entity.Company;
import com.demo.exception.CompanyException;
import com.demo.service.CompanyService;

@SpringBootApplication
@ComponentScan("com.demo")
@EntityScan(basePackages = { "com.demo.entity" })
@EnableJpaAuditing
@EnableJpaRepositories("com.demo.dao")

public class InventoryApplication {
	
	static Logger log = Logger.getLogger(InventoryApplication.class.getName());

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(InventoryApplication.class, args);
		log.info("Application Started!!!");
		/*
		 * 
		 * System.out.println("Welcome."); MedicineService service =
		 * ctx.getBean(MedicineService.class); service.printMessage();
		 * service.saveMedicine(); service.fetchMedicine(); service.fetchAllMedicine();
		 * service.updateMedicine();
		 */

		CompanyService service = ctx.getBean(CompanyService.class);

		// TASK 1

		/*
		 * Company c = new Company(); //c.setCompanyId(1); c.setCompanyName("pharma");
		 * c.setCity("Chennai"); c.setContactNumber(9876543201L);
		 * service.insertACompany(c);
		 */
		// System.out.println(service.findACompany(2));
		// System.out.println(service.findAllCompanies());

		// TASK 2
		/*
		 * Drug d = new Drug(); //d.setDrugId(2L); d.setDrugName("dolo"); d.setCost(5);
		 * 
		 * Company c2 = new Company(); c2.setCompanyId(1);; d.setCompany(c2);
		 * 
		 * service.insertMedicine(d);
		 */
		// System.out.println(service.findMedicine(1));
		// System.out.println(service.findAllMedicines());

		// TASK 3

		// create company
		/*
		 * Company c3 = new Company(); c3.setCompanyName("A pharma");;
		 * c3.setCity("Kolkata"); c3.setContactNumber(9878654987L);
		 * 
		 * //create drugs Drug d2 = new Drug(); d2.setDrugName("abc"); d2.setCost(8);
		 * 
		 * Drug d3 = new Drug(); d3.setDrugName("vicks"); d3.setCost(4);
		 * 
		 * //Associating drugs to company d2.setCompany(c3); d3.setCompany(c3);
		 * 
		 * //Associating company to drugs List<Drug> c4 = new ArrayList<Drug>();
		 * c4.add(d2); c4.add(d3);
		 * 
		 * c3.setDrug(c4); System.out.println("Adding company...");
		 * service.insertACompany(c3);
		 */
		
		//update
		/*Company c5 = new Company();
		c5.setCompanyId(8);
		c5.setCompanyName("nicotex");
		c5.setCity("Bangalore");
		c5.setContactNumber(9664336349L);
		try {
		    service.updateACompany(c5);
		    System.out.println("Company updated successfully.");
		} catch (CompanyException e) {
		    System.out.println("Error updating company: " + e.getMessage());
		}*/


		// Join process
		/*
		 * Company c4 = service.findACompany(1); System.out.println("Company" + c4);
		 */

		/*
		 * List<Drug> drugList = c4.getDrug();
		 * 
		 * System.out.println("***************Prescribed Drugs***************"); for
		 * (Drug dObj : drugList) { System.out.println("Drug Name: " +
		 * dObj.getDrugName()); }
		 */

		// Transaction

		/*
		 * Drug drug = new Drug(); drug.setDrugName("parax"); drug.setCost(8);
		 * 
		 * Company company = new Company(); company.setCompanyId(1);
		 * drug.setCompany(company);
		 * 
		 * Company company1 = new Company(); company1.setCompanyName("tab pharma");
		 * company1.setCity("Shimla"); company1.setContactNumber(9845754386L);
		 * 
		 * service.transactionDemo(company1, drug);
		 */

		// Custom Query

		/*
		 * List<Company> list = service.findCompaniesByCompanyId(1);
		 * System.out.println("Company based on Id -----> " + list);
		 */

		/*
		 * List<Company> listNames = service.findCompanyByName("a");
		 * System.out.println("Company based on company names -----> " + listNames);
		 */

		
		  /*List<CompanyCustomized> companyListCustomized = service.findByCustomizedCompanyName("A pharma"); 
		  for (CompanyCustomized co :companyListCustomized) { 
			  System.out.println("Library Customized Data -----> "+ co.getCompanyName() + " - " + co.getCity()); 
			  } */
		  // Named query
		 // System.out.println("Company Data ORDERBY Name " + service.findAllOrderByCompanyNames());
		  //System.out.println("Company Data by city " + service.findByCity("Chennai")); 
		 

		// Inner join
		/*
		 * List<Company> companylist = service.findCompanyWithDrug();
		 * System.out.println("Company with drugs INNER JOIN " + companylist);
		 */

		// Left outer join

		/*
		 * List<Company> companylist1 = service.findCompanyWithDrugLeft();
		 * System.out.println("Company with drugs LEFT OUTER JOIN " + companylist1);
		 */

		// Customized join
		/*List<CompanyDrugCustomized> companydrugList = service.findByCompanyDrugsCustomized();
		for (CompanyDrugCustomized cd : companydrugList) {
			System.out.println("Customized joined data by using joins -----> " + cd.getCompanyName() + " - "
					+ cd.getCity());
		}*/

	}
}