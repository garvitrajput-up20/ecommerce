package com.lakshmi.exittestapi.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lakshmi.exittestapi.entities.UsersEntity;

public interface UsersDAO extends CrudRepository<UsersEntity, Long>{
	
	Optional<UsersEntity> findByUsernameIgnoreCase(String username);
	
	Optional<UsersEntity> findByEmailIgnoreCase(String email);

}
