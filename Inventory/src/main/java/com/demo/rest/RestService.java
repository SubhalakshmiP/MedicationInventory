package com.demo.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.CompanyCustomized;
import com.demo.dao.CompanyDrugCustomized;
import com.demo.dao.UserRepository;
import com.demo.dto.CompanyDTO;
import com.demo.dto.LoginRequest;
import com.demo.entity.Company;
import com.demo.entity.Drug;
import com.demo.entity.User;
import com.demo.exception.CompanyException;
import com.demo.exception.CompanyNotFoundException;
import com.demo.response.ResponseObject;
import com.demo.service.CompanyService;
import com.demo.service.DrugService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Service
public class RestService {
	@Autowired
	CompanyService service1;

	@Autowired
	DrugService service2;

	@Autowired
	private UserRepository userRepository;

	Logger log = Logger.getLogger(RestService.class.getName());

	// No i/p
	@RequestMapping("/sayHello")
	public String sayHello() {
		return "Say Hello";
	}

	// Path variable
	@RequestMapping("/sayHelloFolks/{empid}/{rollid}")
	public String sayHelloFolks(@PathVariable("empid") int empid, @PathVariable("rollid") int rollid) {
		return "Say hello to employee ----->" + empid + "Rollid----->" + rollid;
	}

	// VO
	@RequestMapping("/GreetingVOUserName")
	public GreetingMessage greetingUser(GreetingMessage gm) {
		gm.setName(gm.getName().toUpperCase());
		gm.setGreeting(gm.getGreeting().toUpperCase());
		return gm;
	}

	@RequestMapping("/searchUsers")
	public List<Users> searchUsers(String searchString) {
		System.out.println(searchString);
		List<Users> l = new ArrayList<Users>();
		Users u1 = new Users();
		u1.setAge(19);
		u1.setName("Subha");

		Users u2 = new Users();
		u1.setAge(20);
		u1.setName("Adhira");

		l.add(u1);
		l.add(u2);
		return l;
	}

	// Map
	@RequestMapping(value = "/searchUsersMap")
	public Map<String, Users> searchUsersMap(String searchString) {
		Map<String, Users> l = new HashMap<String, Users>();
		Users u1 = new Users();
		u1.setAge(19);
		u1.setName("Subha");

		Users u2 = new Users();
		u1.setAge(20);
		u1.setName("Adhira");

		l.put(u1.getName(), u1);
		l.put(u2.getName(), u2);
		return l;
	}

	// Insert
	// @RequestMapping(value="/createCompany", method = RequestMethod.POST)
	@PostMapping(value = "/createCompany")
	public ResponseObject addCompany(@RequestBody CompanyDTO dto) {
		ResponseObject responseObject = new ResponseObject();

		try {
			log.info("Add method triggered.");
			System.out.println("Company Name: " + dto.getCompanyName());
			System.out.println("Contact Number: " + dto.getContactNumber());
			System.out.println("City: " + dto.getCity());

			Company c = new Company();
			c.setCompanyName(dto.getCompanyName());
			c.setContactNumber(dto.getContactNumber());
			c.setCity(dto.getCity());

			Company c1 = service1.insertACompany(c);
			dto.setCompanyId(c1.getCompanyId());
			dto.setCreatedAt(c1.getCreatedAt());
			dto.setUpdatedAt(c1.getUpdatedAt());

			responseObject.setSucessMessage("Company inserted successfully!!!");
			responseObject.setCompanyList(Collections.singletonList(dto));

			log.info("Record added successfully");

			return responseObject;
		} catch (CompanyException e) {
			log.error("Error when adding company details.", e);
			responseObject.setFailureMessage("Company name cannot be null.");
		} catch (Exception e) {
			responseObject.setFailureMessage("Error while inserting!");
		}
		return responseObject;
	}

	//Update
	@RequestMapping(value = "/updateCompany", method = RequestMethod.POST)
	public ResponseObject updateCompany(@RequestBody CompanyDTO msg) {
		ResponseObject responseObject = new ResponseObject();
		try {
			log.info("Update method triggered.");
		System.out.println("Company Id:" + msg.getCompanyId());
		System.out.println("Company Name:" + msg.getCompanyName());
		System.out.println("City:" + msg.getCity());
		System.out.println("Contact Number:" + msg.getContactNumber());
		//Company c1 = service1.findACompany(msg.getCompanyId());
		//c1.setCompanyName("Oxyto");
		//c1.setCity("Banglore");
		//c1.setContactNumber(9787654987L);
		//Company c2 = service1.updateACompany(c1);
		//CompanyDTO c3 = new CompanyDTO();
		Company c3 = new Company();
		c3.setCompanyId(msg.getCompanyId());
		c3.setCompanyName(msg.getCompanyName());
		c3.setCity(msg.getCity());
		c3.setContactNumber(msg.getContactNumber());
		
		Company c4 = service1.updateACompany(c3);
		msg.setCreatedAt(c4.getCreatedAt());
		msg.setUpdatedAt(c4.getUpdatedAt());
		
		responseObject.setSucessMessage("Company updated successfully!!!");
		responseObject.setCompanyList(Collections.singletonList(msg));
		
		log.info("Record updated successfully");
		return responseObject;
		} catch (CompanyException e) {
			log.error("Error when adding company details.", e);
			responseObject.setFailureMessage("Company name cannot be null.");
		} catch (Exception e) {
			responseObject.setFailureMessage("Error while inserting!");
		}
		return responseObject;
	}
	
	// Fetch by id
	@RequestMapping(value = "/fetchCompanyById", method = RequestMethod.GET)
	public ResponseObject fetchCompany(@RequestParam int companyId) {
		ResponseObject responseObject = new ResponseObject();

		try {
			System.out.println("Company Id: " + companyId);
			Company c = service1.findACompany(new Integer(companyId));

			CompanyDTO dto = new CompanyDTO();
			dto.setCreatedAt(c.getCreatedAt());
			dto.setUpdatedAt(c.getUpdatedAt());
			dto.setCompanyName(c.getCompanyName());
			dto.setContactNumber(c.getContactNumber());
			dto.setCity(c.getCity());
			dto.setCompanyId(c.getCompanyId());

			responseObject.setSucessMessage("Company fetched successfully!");
			responseObject.setCompanyList(Collections.singletonList(dto));
			return responseObject;
		} catch (CompanyNotFoundException e) {
			responseObject.setFailureMessage("Company not found");
		} catch (Exception e) {
			responseObject.setFailureMessage("Error while fetching.");
		}
		return responseObject;
	}

	// Fetch all
	@RequestMapping(value = "/fetchAllCompanies", method = RequestMethod.GET)
	public ResponseObject fetchAllCompanies() {
		ResponseObject responseObject = new ResponseObject();

		try {
			List<Company> companies = service1.findAllCompanies();
			List<CompanyDTO> dto1 = new ArrayList<>();

			for (Company c : companies) {
				CompanyDTO dto = new CompanyDTO();
				dto.setCompanyId(c.getCompanyId());
				dto.setCompanyName(c.getCompanyName());
				dto.setCity(c.getCity());
				dto.setContactNumber(c.getContactNumber());
				dto.setCreatedAt(c.getCreatedAt());
				dto.setUpdatedAt(c.getUpdatedAt());
				dto1.add(dto);
			}
			responseObject.setSucessMessage("Companies fetched successfully!");
			responseObject.setCompanyList(dto1);
			return responseObject;
		} catch (CompanyNotFoundException e) {
			responseObject.setFailureMessage("Companies not found.");
		} catch (Exception e) {
			responseObject.setFailureMessage("Error while fetchig companies.");
		}
		return responseObject;
	}

	// Insert

	/*@RequestMapping(value = "/createDrug", method = RequestMethod.POST)
	public Drug addDrug(@RequestBody Drug msg) {
		System.out.println("Drug Name: " + msg.getDrugName());
		System.out.println("Company Name: " + msg.getCompany().getCompanyId());
		Company companyResponse = service1.findACompany(msg.getCompany().getCompanyId());
		System.out.println("Company Response -----> " + companyResponse);
		Drug d = new Drug();
		d.setDrugName(msg.getDrugName());
		d.setCost(msg.getCost());
		msg.setCompany(companyResponse);
		Drug response = service2.insertMedicine(msg);
		System.out.println("Drug added successfully.....");
		return response;
	}*/
	
	// FetchById
	@RequestMapping(value = "/fetchDrugById", method = RequestMethod.GET)
	public Drug findDrug(@RequestParam int drugId) {
		System.out.println("Drug ID: " + drugId);
		Drug d = service2.findMedicine(drugId);
		d.getDrugName();
		d.getCost();
		d.getCreatedAt();
		d.getUpdatedAt();

		System.out.println("Drug details fetched !!!");
		return d;
	}

	// Fetch all
	@RequestMapping(value = "/fetchAllDrugs", method = RequestMethod.GET)
	public List<Drug> fetchAllDrugs() {
		List<Drug> fetchAll = service2.findAllMedicines();
		return fetchAll;
	}

	// Queries
	// find Companies By CompanyId
	@RequestMapping(value = "/findCompaniesByCompanyId", method = RequestMethod.GET)
	public List<Company> findCompaniesByCompanyId(@RequestParam int companyId) {
		List<Company> list = service1.findCompaniesByCompanyId(companyId);
		return list;

	}

	// FindByName
	@RequestMapping(value = "/findByCompanyNames", method = RequestMethod.GET)
	public List<Company> findByCompanyNames(@RequestParam String companyName) {
		List<Company> list = service1.findCompanyByName(companyName);
		return list;
	}

	// FindByNameCustomized
	@RequestMapping(value = "/findByNameCustomized", method = RequestMethod.GET)
	public List<CompanyCustomized> findCompanyByNameCustomized(@RequestParam String companyName) {
		List<CompanyCustomized> list = service1.findByCustomizedCompanyName(companyName);
		return list;
	}

	// FindAllOrderByNameDesc
	@RequestMapping(value = "/findAllOrderByNameDescending", method = RequestMethod.GET)
	public List<Company> findAllOrderByNameDescending() {
		List<Company> list = service1.findAllOrderByCompanyNames();
		return list;
	}

	// FindByCity
	@RequestMapping(value = "/findByCity", method = RequestMethod.GET)
	public List<Company> findByCity(@RequestParam String city) {
		List<Company> list = service1.findByCity(city);
		return list;
	}

	// Inner Join
	@RequestMapping(value = "findCompanyWithDrug", method = RequestMethod.GET)
	public List<Company> findCompanyWithDrug() {
		List<Company> list = service1.findCompanyWithDrug();
		return list;
	}

	// Left Outer Join
	@RequestMapping(value = "/findCompanyWithDrugLeft", method = RequestMethod.GET)
	public List<Company> findCompanyWithDrugLeft() {
		List<Company> list = service1.findCompanyWithDrugLeft();
		return list;
	}

	// Customized join
	@RequestMapping(value = "/findByCompanyDrugsCustomized", method = RequestMethod.GET)
	public List<CompanyDrugCustomized> findByCompanyDrugsCustomized() {
		List<CompanyDrugCustomized> list = service1.findByCompanyDrugsCustomized();
		return list;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
		Map<String, Object> response = new HashMap<>();

		User user = userRepository.findByUsername(request.getUsername());

		if (user != null && user.getPassword().equals(request.getPassword())) {
			response.put("success", true);
			response.put("message", "Login successful");
			return ResponseEntity.ok(response);
		} else {
			response.put("success", false);
			response.put("message", "Invalid credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}
}
