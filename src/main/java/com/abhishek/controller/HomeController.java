package com.abhishek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abhishek.entity.User;
import com.abhishek.repo.UserJpaRepository;

@Controller
public class HomeController {

	@Autowired
	UserJpaRepository userJpaRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/access-denied")
	public String acc() {
		return "access-denied";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "login";
	}
	
	@RequestMapping("/do-register")
	public String sampleCreation(Model model) {
		
		User user = new User();
		user.setFirstname("admin");
		user.setLastname("admin");
		user.setEmail("admin@gmail.com");
		user.setAddress("upper");
		user.setCity("pune");
		user.setState("maharastra");
		user.setRole("ROLE_ADMIN");
		user.setPassword(passwordEncoder.encode("admin"));
		userJpaRepository.save(user);
		model.addAttribute("user", user);
		return "/login";
	}
	
	@RequestMapping("/welcome")
	public String wel() {
		return "welcome";
	}
	
//	@RequestMapping("/error")
//	public String wrong() {
//		return "error";
//	}
//	
	
}
