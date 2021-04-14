package com.hcl.musicmelody.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


import com.hcl.MusicMelody.models.*;
import com.hcl.MusicMelody.repositories.UserRepository;
import com.hcl.MusicMelody.services.*;

import org.junit.jupiter.api.Assertions;

import java.util.Date;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindUserByUserNameTest {

	
	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepo;
	
	private List<UserCred> userCred = new ArrayList<UserCred>();
	
	@BeforeAll
	public void init() {
		
		MockitoAnnotations.openMocks(this);
		
		UserCred us = new UserCred();
		us.setUserName("dummy");
		us.setLastName("user");
		us.setName("dummy user");
		us.setEmail("dummy@email.com");
		us.setPassword("dummyPassword");	
		userCred.add(us);
	
	}
	
	
	@Test
	public void test() {
		
		UserCred u = new UserCred();
		when(userService.findUserByUserName("dummy")).thenReturn(userCred.get(0));
		u = userService.findUserByUserName("dummy");

        Assertions.assertEquals(u.getUserName(), "dummy");
    }
		
	
}
