package com.abhishek.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.abhishek.entity.User;
import com.abhishek.repo.UserJpaRepository;


public class NewUserServiceImpl implements UserDetailsService {

	@Autowired
	private UserJpaRepository userJpaReposiitory;
	
//	@Autowired
//	private RoleJpaRepository roleJpaRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userJpaReposiitory.findByEmail(username);
	//	Role role = roleJpaRepository.findByName(name);
		if(user == null) {
			throw new UsernameNotFoundException("Could not found user");
		}
		UserPriviliages userPriviliages = new UserPriviliages(user);
		return userPriviliages;
	}
	
}
