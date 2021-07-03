package com.abhishek.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserJpaRepository userJpaRepository;
//	@Autowired
//	private UserService userService;


//	@Autowired
//	public AdminController(UserService userService) {
//	this.userService = userService;
//}
	@Autowired
	MarksheetJpaRepository marksheetJpaRepository;

//	@Autowired
//	private MarksheetService marksheetService;
//	
//	@Autowired
//	public AdminController(MarksheetService marksheetService) {
//		this.marksheetService = marksheetService;
//	}

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
//	@ModelAttribute
//	public void commondata(Model model,Principal principal) {
//		String name = principal.getName();
//		User user = userJpaRepository.findByEmail(name);
//		
//		model.addAttribute("user", user);
//		
//		System.out.println(name);
//	}
	
	@RequestMapping(value = {"/","/dashboard"})
	public String home(){
		return "admin/dashboard";
	}
	
	@GetMapping("/add-user")
	public String userform(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("title", "user form");
		return "admin/user-form";
	}
	
	@PostMapping("/user-register")
	public String register(@Valid @ModelAttribute("user")User user,BindingResult theBindingResult,Model model,HttpSession session) {
			if(theBindingResult.hasErrors()) {
				System.out.println("Binding result block");
			//	model.addAttribute("errors", theBindingResult);
				return "admin/user-form";
			}
			User exists= userJpaRepository.findByEmail(user.getEmail());
			
			if(exists != null) {
				model.addAttribute("user", new User());
				model.addAttribute("registrationError", "Usernam/email already exists" );
				return "admin/user-form";
			}
					
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			System.out.println(user);
			userJpaRepository.save(user);
			
			model.addAttribute("user", new User());
			model.addAttribute("title", "User Form");
			session.setAttribute("message", new Message("alert-success", "User added successfully"));
			return "admin/user-form";
			
	}
	
	@RequestMapping("/user-list")
	public String get(Model model) {
		List<User> allUsers = userJpaRepository.findAll();
		model.addAttribute("users", allUsers);
		model.addAttribute("title", "User list");
		return "admin/user-list";
	}
	
	@GetMapping("/delete-user")
	public String deleteUser(@RequestParam("userId")int id,Model model) {
		userJpaRepository.deleteById(id);
		List<User> allUsers = userJpaRepository.findAll();
		model.addAttribute("users", allUsers);
		model.addAttribute("title", "User list");
		return "admin/user-list";
	}
	

	
	@GetMapping("/user-by-email")
	public String find() {
		return "admin/search-by-email";
	}
	
	@PostMapping("/search-user-process")
	public String searching(Model model,@RequestParam("email") String email,HttpSession session) {
		User result = userJpaRepository.findByEmail(email);
		if(result == null) {
			session.setAttribute("message", new Message("alert-danger", "User not found"));
			return "admin/search-by-email";
		}
		
		model.addAttribute("user", result);
		model.addAttribute("title", "USER PROFILE");
		return "admin/user-profile";
	}
	
	@PostMapping("/search-user-by-role")
	public String searching1(Model model,@RequestParam("role")String role) {
		List<User> result1 = userJpaRepository.findByRole(role);
		if(result1 == null) {
			model.addAttribute("notfound", "No user found");
			return "admin/search-by-email";
		}
		
		model.addAttribute("users", result1);
		return "admin/user-list";
	}
	
	@RequestMapping("/showFormMarksheet")
	public String showMarksheetForm(Model model) {
		model.addAttribute("marksheet", new Marksheet());
		return "admin/marksheet-form";
	}
	
	@PostMapping("/add-marksheet")
	public String add(@Valid @ModelAttribute("marksheet") Marksheet marksheet,BindingResult result,HttpSession session,Model model) {
		try {
		if(result.hasErrors()) {
			return "admin/marksheet-form";
		}
		
		Marksheet existing = marksheetJpaRepository.findByRoll_No(marksheet.getRoll_no());
		if(existing != null) {
			model.addAttribute("marksheet", new Marksheet());
			model.addAttribute("addingError", "User name Already exist ");
			return "admin/marksheet-form";
		}
		
		marksheetJpaRepository.save(marksheet);
		model.addAttribute("title", "Marksheet Form");
		model.addAttribute("marksheet", new Marksheet());
		session.setAttribute("message", new Message("alert-success", "Marksheet added successfully"));

		return "admin/marksheet-form";
		
		} catch(Exception e) {
			e.printStackTrace();
			return "admin/user-list";
		}
	}
	
	@GetMapping("/view")
	public String view(Model model,Principal principal) {
		String result = principal.getName();
		User user = userJpaRepository.findByEmail(result);
		model.addAttribute("user", user);
		return "admin/user-profile";
	}
	
	@GetMapping("/view-all-marksheet")
	public String viewallmarksheet(Model model) {
		List<Marksheet> marksheets = marksheetJpaRepository.findAll();
		model.addAttribute("marksheets", marksheets);
		model.addAttribute("title", "Marksheet List");
		return "admin/marksheet-list";
	}
	
	@GetMapping("/delete-marksheet")
	public String deletemarksheet(@RequestParam("marksheetId")int stud_id,Model model) {
		marksheetJpaRepository.deleteById(stud_id);
		List<Marksheet> marksheets = marksheetJpaRepository.findAll();
		model.addAttribute("marksheets", marksheets);
		model.addAttribute("title", "Marksheet List");
		return "admin/marksheet-list";
	}
	
	
	@GetMapping("/search-marksheet-page")
	public String searchmarksheet(Model model) {
		model.addAttribute("title", "Search Marksheet");
		return "admin/search-marksheet-form";
	}
	
	@PostMapping("/search-marksheet")
	public String searchmarksheet1(@RequestParam("roll_no")int roll_no,Model model,HttpSession session) {
		Marksheet theMarksheet = marksheetJpaRepository.findByRoll_No(roll_no);
		if(theMarksheet == null) {
			session.setAttribute("message", new Message("alert-danger", "OOOPS!!!!! Marksheet not found"));
			return "admin/search-marksheet-form";
		}
		model.addAttribute("marksheet", theMarksheet);
		model.addAttribute("title", "Marksheet");
		
		return "admin/marksheet-summary";
	}
	
	@GetMapping("/view-detail")
	public String viewmarks(@RequestParam("marksheetId")int stud_id,Model model) {
		
		Optional<Marksheet> byId = marksheetJpaRepository.findById(stud_id);
		Marksheet marksheet = byId.get();
		
		model.addAttribute("marksheet", marksheet);
		model.addAttribute("title", "Marksheet");
		
		return "admin/marksheet-summary";
	}
	
	@GetMapping("/update-marksheet")
	public String updatemethod(@RequestParam("marksheetId")int stud_id,Model model) {
		Optional<Marksheet> byId = marksheetJpaRepository.findById(stud_id);
		Marksheet marksheet = byId.get();
		model.addAttribute("title", "Update marksheet");
		model.addAttribute("marksheet", marksheet);
		return "admin/update-marksheet-page";
	}
	
	@PostMapping("/update-marksheet-process")
	public String updateing(@Valid @ModelAttribute("marksheet")Marksheet marksheet,BindingResult result,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title","Update Marksheet");
			return "admin/update-marksheet-page";
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
		return "admin/marksheet-summary";
	}
	
	
}
