package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.bo.DrugBO;
import com.demo.entity.Drug;

@Component
public class DrugService {
	@Autowired 
	DrugBO bo;
	public Drug insertMedicine(Drug addDrug) {
		return bo.insert(addDrug);
	}
	public Drug findMedicine(int drugId) {
		return bo.findDrug(drugId);
	}
	public List <Drug> findAllMedicines() {
		return bo.findDrugs();
	}
}
