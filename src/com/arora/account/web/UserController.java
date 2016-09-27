package com.arora.account.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.arora.account.model.User;
import com.arora.account.repository.UserRepository;
import com.arora.account.service.SecurityService;
import com.arora.account.service.UserService;
import com.arora.account.validator.UserValidator;
import com.arora.event.model.Event;
import com.arora.event.repository.EventRepository;
import com.arora.player.model.Player;
import com.arora.player.repository.PlayerRepository;
import com.arora.player.service.PlayerService;
import com.arora.tournament.model.Tournament;
import com.arora.tournament.repository.TournamentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
	private PlayerService playerService;

    @Autowired
	private PlayerRepository playerRepository;    
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private TournamentRepository tournamentRepository;
    
    /* REGISTRATION */
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    /* LOGIN */
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    /* WELCOME PAGE */
    
    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model, Principal principal) {
    	
    	User user = userRepository.findByUsername(principal.getName());
    	Player player = playerRepository.findByUserid(user.getId());
    	List<Event> events = new ArrayList<Event>();
    	List<Tournament> tournaments = new ArrayList<Tournament>();
    	
    	// GAMES (EVENTS)
    	if ("player".equals(user.getUsertype())) {
    		
        	String games = player.getGames_ids();
        	ArrayList<String> arr = null;
        	
        	if (games == null) {
    			arr = new ArrayList<String>();
    		} else {
    			arr = new ArrayList<String>(Arrays.asList(games.split(",")));
    			arr.removeAll(Arrays.asList(null,""));
    		}


        	for (int i = 0; i < arr.size(); i++) {
        		events.add(eventRepository.findById(Long.parseLong(arr.get(i))));
        	}    	
        	

    	} else {
    		
    		events = eventRepository.findByOwner(principal.getName());
    		
    	}
    	
    	// TOURNAMENTS

    	if ("player".equals(user.getUsertype())) {
    		
        	String games = player.getTournaments_ids();
        	ArrayList<String> arr = null;
        	
        	if (games == null) {
    			arr = new ArrayList<String>();
    		} else {
    			arr = new ArrayList<String>(Arrays.asList(games.split(",")));
    			arr.removeAll(Arrays.asList(null,""));
    		}


        	for (int i = 0; i < arr.size(); i++) {
        		tournaments.add(tournamentRepository.findById(Long.parseLong(arr.get(i))));
        	}    	
        	

    	} else {
    		
    		tournaments = tournamentRepository.findByOwner(principal.getName());
    		
    	}    	
    	
    	model.addAttribute("playerInfo", player);
    	model.addAttribute("user", user);
    	model.addAttribute("events", events);
    	model.addAttribute("tournaments", tournaments);

        return "welcome";
    }
    
    
    /* EDIT PROFILE */

    @RequestMapping(value = "/update-profile", method = RequestMethod.GET)
    public String editProfile(Principal principal, Model model) {
    	    	
    	if (principal != null) {
    		model.addAttribute("userForm", userService.findByUsername(principal.getName()));
    	}
    	
        return "update-profile";
    }

    @RequestMapping(value = "/update-profile", method = RequestMethod.POST)
    public String editProfile(Principal principal, @ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        
    	System.out.println(bindingResult);
    	
    	User user2 = userRepository.findByUsername(principal.getName());
    	userValidator.validateUpdateProfile(userForm, bindingResult, user2);

        if (bindingResult.hasErrors()) {
            return "update-profile";
        }
        
        userService.updateProfile(userForm);

        return "redirect:/welcome";
    } 
    
    /* CHANGE PASSWORD */    
    
    @RequestMapping(value = "/change-password", method = RequestMethod.GET)
    public String changePassword(Principal principal, Model model) {
    	    	
    	if (principal != null) {
    		
    		User u = userService.findByUsername(principal.getName());
    		u.setPassword("");
    		
    		model.addAttribute("userForm", u);
    	}
    	
        return "change-password";
    }   
    
    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public String changePassword(Principal principal, @ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        
    	userValidator.validatePassword(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "change-password";
        }
        
        userService.updatePassword(userForm, principal);

        return "redirect:/welcome";
    }     
  
    /* DELETE PROFILE */
    
    @RequestMapping(value = "/delete-profile", method = RequestMethod.GET)
    public String deleteProfile(Principal principal) {
        
        userService.deleteProfile(principal);

        return "redirect:/login";
    }       
    
}
