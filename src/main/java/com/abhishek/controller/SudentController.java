package com.abhishek.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abhishek.entity.Marksheet;
import com.abhishek.entity.User;
import com.abhishek.helper.Message;
import com.abhishek.repo.MarksheetJpaRepository;
import com.abhishek.repo.UserJpaRepository;

@Controller
@RequestMapping("/student")
public class SudentController {
	
	@Autowired
	UserJpaRepository userJpaRepository;
	
	@Autowired
	MarksheetJpaRepository marksheetJpaRepository;

	@RequestMapping(value = {"/","/dashboard"})
	public String home() {
		return "student/dashboard";
	}
	
	@PostMapping("/show-marksheet")
	public String show(@RequestParam("roll_no")int roll_no,Model model,HttpSession session) {
		Marksheet marksheet = marksheetJpaRepository.findByRoll_No(roll_no);
		if(marksheet == null) {
			session.setAttribute("message", new Message("alert-danger", "OOOOPS!!!!!!    Marksheet Not Generated/Found"));
			return "student/dashboard";
		}
		
		String passvalue = null;
		
		if(marksheet.getBiology()<32 || marksheet.getBiology()<32 || marksheet.getChemistry()<32 || marksheet.getMaths()<32) {
			passvalue = "Fail";
		}
		else {
			passvalue = "Pass";
		}
		
		int total = marksheet.getPhysics() + marksheet.getChemistry() + marksheet.getBiology() + marksheet.getMaths();
		if(total < 35) {
			passvalue = "Fail";
		}
		model.addAttribute("total", total);
		model.addAttribute("passvalue", passvalue);
		model.addAttribute("marksheet", marksheet);
		model.addAttribute("title", "Marksheet");
		return "student/marksheet-summary";
	}
	
	@GetMapping("/my-profile")
	public String viewdetail(Model model,Principal principal) {
		String name = principal.getName();
		
		User user = userJpaRepository.findByEmail(name);
		
		model.addAttribute("user", user);
		model.addAttribute("title", "My Profile");
		return "student/user-profile";
		
	}
	
	
}
