package com.sec.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sec.entity.Story;
import com.sec.entity.User;
import com.sec.service.EmailService;
import com.sec.service.StoryService;
import com.sec.service.UserService;


@Controller
public class HomeController {
	
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    
    
    private UserService userService;
    
    private EmailService emailService;
    
    private StoryService storyService;
    


	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setStoryService(StoryService storyService) {
		this.storyService = storyService;
	}
	
	@RequestMapping("/")
	public String home(){
		return "index";
	}
	
	@RequestMapping("/bloggers")
	public String bloggers(Model model){
		model.addAttribute("users",userService.getUsers());
		return "bloggers";
	}
	
	@RequestMapping("/stories")
	public String stories(Model model){
		model.addAttribute("story", new Story());
		model.addAttribute("stories",storyService.getStories());
		return "stories";
	}
	
	@RequestMapping("/registration")
	public String registration(Model model){
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@PostMapping("/reg")
    public String reg(@ModelAttribute User user) {
		log.info("Uj user!");
		log.debug(user.getFullName());
		log.debug(user.getEmail());
		log.debug(user.getPassword());
		log.debug(user.getActivation());
		userService.registerUser(user);
		log.debug(user.getActivation());
		emailService.sendActivationMessage(user);
        return "auth/login";
    }
	
	@PostMapping("/stories")
    public String story(@ModelAttribute Story story, Model model) {
		log.info("Uj story!");
		log.debug(story.getTitle());
		log.debug(story.getContent());
		story.setPosted(new Date());
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		story.setUser(userService.findByEmail(authentication.getName()));
		storyService.addNewStory(story);
		model.addAttribute("stories",storyService.getStories());
		return "/stories";
    }
	
	 @RequestMapping(path = "/activation/{code}", method = RequestMethod.GET)
	    public String activation(@PathVariable("code") String code, HttpServletResponse response) {
		User user = userService.findByActivation(code);
		String result = userService.userActivation(code);
		log.debug(result);
		emailService.sendConfirmationMessage(user);
		return "auth/login";
	 }

}
