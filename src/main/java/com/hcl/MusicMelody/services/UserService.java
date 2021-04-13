package com.hcl.MusicMelody.services;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcl.MusicMelody.models.Role;
import com.hcl.MusicMelody.models.UserCred;
import com.hcl.MusicMelody.repositories.RoleRepository;
import com.hcl.MusicMelody.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepo, RoleRepository roleRepo,
						BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public UserCred findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public UserCred findUserByUserName(String userName) {
		System.out.println("============================================ UserName" + userRepo.findByUserName(userName));

		return userRepo.findByUserName(userName);
	}
	
	public UserCred saveUser(UserCred userCred) {
		System.out.println("============================================" + userCred.getPassword());
		userCred.setPassword(bCryptPasswordEncoder.encode(userCred.getPassword()));
		userCred.setActive(true);
		Role userRole = roleRepo.findByRole("USER");
		userCred.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return userRepo.save(userCred);
		
	}
	
	public UserCred updateUser(UserCred userCred) {
		return userRepo.save(userCred);
		
	}

}
