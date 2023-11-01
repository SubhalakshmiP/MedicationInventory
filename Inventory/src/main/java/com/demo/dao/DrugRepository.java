package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.entity.Drug;

@Repository
public interface DrugRepository extends JpaRepository <Drug, Integer>{

}
