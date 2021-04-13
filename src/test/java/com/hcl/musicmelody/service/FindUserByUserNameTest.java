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


//@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindUserByUserNameTest {

	@InjectMocks
	private UserService userService;
	
	//@InjectMocks
	//private BillingInformationService billingInformationService;
	
	@MockBean
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
		//us.setBilling(billingInformation);
	
		userCred.add(us);
	
	}
	
	
	@Test
	public void test() {
		
	/*	Date expDate = new Date(2222-22-22);
		BillingInformation billingInformation = new BillingInformation();
		 billingInformation.setCardNumber("1234123412");
		 billingInformation.setCvv("123");
		 billingInformation.setCity("San Antonio");
		 billingInformation.setApt("10");
		 billingInformation.setZip("12345");
		 billingInformation.setState("TX");
		 billingInformation.setExpDate(expDate);
		 billingInformation.setStreet("123 Broadway st"); */
		
		UserCred us = new UserCred();
		us.setUserName("dummy");
		us.setLastName("user");
		us.setName("dummy user");
		us.setEmail("dummy@email.com");
		us.setPassword("dummyPassword");
		//us.setBilling(billingInformation);
		//userService.saveUser(us);
		UserCred u = new UserCred();
		when(userService.findUserByUserName("dummy")).thenReturn(userCred.get(0));
		u = userService.findUserByUserName("dummy");

        Assertions.assertEquals(u.getUserName(), "dummy");
    }
		
	
}
