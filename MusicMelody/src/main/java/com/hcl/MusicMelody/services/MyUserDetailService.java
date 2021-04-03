package com.hcl.MusicMelody.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hcl.MusicMelody.models.Role;
import com.hcl.MusicMelody.models.UserCred;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) {
		UserCred user = userService.findUserByUserName(userName);
		List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
		return buildUserForAuthentication(user, authorities);
	}
	
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles){
		Set<GrantedAuthority> roles = new HashSet<>();
		for(Role role : userRoles) {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return new ArrayList<>(roles);
	}
	
	private UserDetails buildUserForAuthentication(UserCred userCred, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(userCred.getUserName(), 
																		userCred.getPassword(), 
																		userCred.isActive(), 
																		true, 
																		true, 
																		true, 
																		authorities);
	}

}
