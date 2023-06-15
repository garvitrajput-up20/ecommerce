package com.lakshmi.exittestapi.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lakshmi.exittestapi.entities.Serviceability;

public  interface ServiceabilityRepo extends JpaRepository<Serviceability, Integer>{

	
    List<Serviceability> findByPidAndPincodes(int id ,int code);
}