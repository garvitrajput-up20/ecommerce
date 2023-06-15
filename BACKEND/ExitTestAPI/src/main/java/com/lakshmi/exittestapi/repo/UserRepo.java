package com.lakshmi.exittestapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lakshmi.exittestapi.entities.UsersEntity;

public interface UserRepo extends JpaRepository<UsersEntity,String> {

}