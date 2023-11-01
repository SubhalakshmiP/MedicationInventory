package com.demo.bo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.dao.DrugRepository;
import com.demo.entity.Drug;
import com.demo.exception.DrugException;


@Component
public class DrugBO {
	@Autowired
	DrugRepository redrug;
	
	public Drug insert(Drug d) { 
		return redrug.save(d);
	}
	
	public Drug findDrug(int drugId) {
		Optional<Drug> dru = redrug.findById(drugId);
		return dru.get();
	}
	
	public List<Drug> findDrugs() {
		return redrug.findAll();
	}
}
