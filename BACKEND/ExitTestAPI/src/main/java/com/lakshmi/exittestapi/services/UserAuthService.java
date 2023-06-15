package com.lakshmi.exittestapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakshmi.exittestapi.body.AuthBody;
import com.lakshmi.exittestapi.dao.UsersDAO;
import com.lakshmi.exittestapi.entities.UsersEntity;
import com.lakshmi.exittestapi.exceptions.UserExistsException;

import jakarta.validation.Valid;

@Service
public class UserAuthService {
	
	private PasswordEncryptionService encryptionService;
	private JwtService jwtService;
	
	@Autowired
	UsersDAO userDAO;

	public UserAuthService(PasswordEncryptionService encryptionService, UsersDAO userDAO, JwtService jwtService) {
		super();
		this.encryptionService = encryptionService;
		this.userDAO = userDAO;
		this.jwtService = jwtService;
	}


	public UsersEntity registerUser(UsersEntity usersEntity) throws UserExistsException {
		if (userDAO.findByEmailIgnoreCase(usersEntity.getEmail()).isPresent()) {
			throw new UserExistsException("User Already Exists With Given");
		}else if(userDAO.findByUsernameIgnoreCase(usersEntity.getUsername()).isPresent()) {
			throw new UserExistsException("User Already Exists With Given Username");
		}
		UsersEntity user = new UsersEntity();
		user.setEmail(usersEntity.getEmail());
		user.setFullname(usersEntity.getFullname());
		user.setUsername(usersEntity.getUsername());
		user.setPassword(encryptionService.encryptPassword(usersEntity.getPassword()));
		return userDAO.save(user);
	}

	public String loginUserByUsername(@Valid AuthBody authBody) {
		Optional<UsersEntity> localUser = userDAO.findByUsernameIgnoreCase(authBody.getUsername());
		if (localUser.isPresent()) {
			UsersEntity user = localUser.get();
			if (encryptionService.verifyPassword(authBody.getPassword(), user.getPassword())) {
				return jwtService.generateJWT(user);
			}
		}
		return null;
	}

}
