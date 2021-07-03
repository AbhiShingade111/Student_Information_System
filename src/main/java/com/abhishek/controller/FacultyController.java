package com.abhishek.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abhishek.entity.Marksheet;
import com.abhishek.entity.User;
import com.abhishek.helper.Message;
import com.abhishek.repo.MarksheetJpaRepository;
import com.abhishek.repo.UserJpaRepository;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
	
	@Autowired
	UserJpaRepository userJpaRepository;
	
	@Autowired
	MarksheetJpaRepository marksheetJpaRepository;

	@RequestMapping(value = {"/","/dashboard"})
	public String home() {
		return "faculty/home";
	}
	
	@GetMapping("/my-profile")
	public String viewme(Model model,Principal principal) {
		String name = principal.getName();
		User user = userJpaRepository.findByEmail(name);
		
		model.addAttribute("user", user);
		model.addAttribute("title", "User Profile");
		
		return "faculty/user-profile";
	}
	
	@GetMapping("/view-all")
	public String viewall(Model model) {
		List<Marksheet> marksheets = marksheetJpaRepository.findAll();
		
		model.addAttribute("marksheets", marksheets);
		model.addAttribute("title", "Marksheet List");
		
		return "faculty/marksheet-list";
	}
	
	@GetMapping("/showFormMarksheet")
	public String formMarksheet(Model model) {
		model.addAttribute("title", "Add marksheet");
		model.addAttribute("marksheet", new Marksheet());
		return "faculty/marksheet-form";
	}
	
	@PostMapping("/add-marksheet")
	public String add(@Valid @ModelAttribute("marksheet") Marksheet marksheet,BindingResult result,HttpSession session,Model model) {
		
		if(result.hasErrors()) {
			session.setAttribute("message", new Message("alert-danger", "Marksheet Not Added"));
			model.addAttribute("title", "Add Marksheet");
			return "faculty/marksheet-form";
		}
		
		Marksheet findByRoll_No = marksheetJpaRepository.findByRoll_No(marksheet.getRoll_no());
		
		if(findByRoll_No != null) {
			session.setAttribute("message", new Message("alert-danger", "Marksheet Exist"));
			model.addAttribute("title", "Add Marksheet");
			return "faculty/marksheet-form";
		}
		
		marksheetJpaRepository.save(marksheet);
		
		
		model.addAttribute("marksheet", new Marksheet());
		model.addAttribute("title", "Add Marksheet");
		session.setAttribute("message", new Message("alert-success", "Marksheet added Successfully"));
		
		
		
		return "faculty/marksheet-form";
	}
	
	@GetMapping("/view-detail")
	public String viewdetail(@RequestParam("marksheetId") int stud_id,Model model) {
		
		Optional<Marksheet> findById = marksheetJpaRepository.findById(stud_id);
		
		Marksheet marksheet = findById.get();
		model.addAttribute("marksheet", marksheet);
		model.addAttribute("title", "Marksheet");
		return "faculty/marksheet-summary";
	}
	
//	@GetMapping("/update-marksheet")
//	public String update(@RequestParam("marksheetId")int stud_id,Model model) {
//		
//		Optional<Marksheet> findById = marksheetJpaRepository.findById(stud_id);
//		
//		Marksheet marksheet = findById.get();
//		marksheet.setStud_id(0);
//		model.addAttribute("marksheet", marksheet);
//		model.addAttribute("title", "Update Marksheet");
//		
//		return "faculty/marksheet-form";
//		
//	}
	
	@GetMapping("/delete-marksheet")
	public String deletefaculty(@RequestParam("marksheetId")int stud_id,Model model) {
		
		marksheetJpaRepository.deleteById(stud_id);
		
		List<Marksheet> marksheets = marksheetJpaRepository.findAll();
		
		model.addAttribute("marksheets", marksheets);
		model.addAttribute("title", "Marksheet List");
		return "faculty/marksheet-list";
	}
	
	@GetMapping("/search")
	public String search(Model model) {
		model.addAttribute("title", "Search Marksheet");
		return "faculty/search-marksheet-form";
	}
	
	@PostMapping("/search-marksheet")
	public String searchbyrollno(@RequestParam("roll_no")int roll_no,Model model,HttpSession session) {
		Marksheet marksheet = marksheetJpaRepository.findByRoll_No(roll_no);
		
		if(marksheet == null) {
			session.setAttribute("message", new Message("alert-danger", "Marksheet Not found"));
			return "faculty/search-marksheet-form";
		}
		
		model.addAttribute("marksheet", marksheet);
		model.addAttribute("title", "Marksheet Deatil");
		
		return "faculty/marksheet-summary";
		
	}
	
	@GetMapping("/update-marksheet")
	public String updatemethod(@RequestParam("marksheetId")int stud_id,Model model) {
		Optional<Marksheet> byId = marksheetJpaRepository.findById(stud_id);
		Marksheet marksheet = byId.get();
		model.addAttribute("title", "Update marksheet");
		model.addAttribute("marksheet", marksheet);
		return "faculty/update-marksheet-page";
	}
	
//	@PostMapping("/update")
//	public String updatemarks(@Valid @ModelAttribute("marksheet")Marksheet marksheet,BindingResult result,Model model) {
//		if(result.hasErrors()) {
//			model.addAttribute("title", "Update Marksheet");
//			return "faculty/update-marksheet-page";
//		}
//		
//		Marksheet updateMarksheet = marksheetJpaRepository.updateMarksheet(marksheet.getStud_id(), marksheet.getStud_name(), marksheet.getRoll_no(), marksheet.getPhysics(), marksheet.getChemistry(), marksheet.getMaths(), marksheet.getBiology(), marksheet.getCollege_name());
//		
//		Marksheet marksheet1 = marksheetJpaRepository.save(updateMarksheet);
//		model.addAttribute("marksheet", marksheet1);
//		model.addAttribute("title", "Marksheet Summary");
//		
//		return "faculty/marksheet-summary";
//	}
	
	@PostMapping("/update")
	public String updateing(@Valid @ModelAttribute("marksheet")Marksheet marksheet,BindingResult result,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title","Update Marksheet");
			return "faculty/update-marksheet-page";
		}
		
		
		marksheet.setStud_name(marksheet.getStud_name());
		marksheet.setRoll_no(marksheet.getRoll_no());
		marksheet.setChemistry(marksheet.getChemistry());
		marksheet.setPhysics(marksheet.getPhysics());
		marksheet.setBiology(marksheet.getBiology());
		marksheet.setCollege_name(marksheet.getCollege_name());
		marksheet.setMaths(marksheet.getMaths());
		@Valid
		Marksheet marksheet2 = marksheetJpaRepository.save(marksheet);
		
		
		model.addAttribute("title", "Marksheet Summary");
		model.addAttribute("marksheet", marksheet2);
		return "faculty/marksheet-summary";
	}
	
}
