package com.arora.tournament.web;


import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.arora.account.model.User;
import com.arora.account.repository.UserRepository;
import com.arora.account.service.UserService;
import com.arora.account.validator.UserValidator;
import com.arora.event.model.Event;
import com.arora.event.repository.EventRepository;
import com.arora.event.service.EventService;
import com.arora.event.validator.EventValidator;
import com.arora.player.model.Player;
import com.arora.player.repository.PlayerRepository;
import com.arora.player.service.PlayerService;
import com.arora.player.validator.PlayerValidator;
import com.arora.tournament.model.Tournament;
import com.arora.tournament.repository.TournamentRepository;
import com.arora.tournament.service.TournamentService;
import com.arora.tournament.validator.TournamentValidator;



@Controller
public class TournamentController {
	
    @Autowired
	private TournamentService tournamentService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PlayerRepository playerRepository;
    
    @Autowired
    private TournamentRepository tournamentRepository;
    
    @Autowired
    private TournamentValidator tournamentValidator;

    @Autowired
    private HttpServletRequest context;    
    
    @RequestMapping(value = "/tournaments", method = RequestMethod.GET)
    public String events(String error, Model model, Principal principal, @RequestParam(value = "date", required=false) String date, @RequestParam(value = "time", required=false) String time) {
           
    	
    	if (error != null) {
            model.addAttribute("error", "You cannot join to the tournament because you already have tournament in same time.");
    	}
    	
    	User user = userRepository.findByUsername(principal.getName());
    	Player player = playerRepository.findByUserid(user.getId());   
    	
    	model.addAttribute("tournamentForm", tournamentService.getTournaments(date, time));
    	model.addAttribute("playerInfo", player);
    	

        return "tournaments";
    }    
    
    
    
    @RequestMapping(value = "/create-tournament", method = RequestMethod.GET)
    public String createTournament(Model model) {
        
    	model.addAttribute("tournamentForm", new Tournament());
        return "create-tournament";
    }    
    
    
    @RequestMapping(value = "/create-tournament", method = RequestMethod.POST)
    public String createTournament(@ModelAttribute("tournamentForm") Tournament tournamentForm, BindingResult bindingResult, Model model, Principal principal) {
        
    	
    	tournamentValidator.validate(tournamentForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	return "create-tournament";
        }
        
    	
    	tournamentService.save(tournamentForm, principal);
        return "redirect:/tournaments";
    }
    
    
    @RequestMapping(value = "/update-tournament", method = RequestMethod.GET)
    public String updateTournament(Model model, @RequestParam("id") String id, Principal principal) {
    	
    	String redirectPage = tournamentValidator.getRedirectPage(context);
    	
    	Tournament tournament = tournamentRepository.findById(Long.parseLong(id));
    	if (!principal.getName().equals(tournament.getOwner())) {
    		return "redirect:/" + redirectPage;
    	}
    	
    	model.addAttribute("tournamentForm", tournamentRepository.findById(Long.parseLong(id)));

        return "update-tournament";
    }     

    
    @RequestMapping(value = "/update-tournament", method = RequestMethod.POST)
    public String editTournament(Principal principal, @ModelAttribute("tournamentForm") Tournament tournamentForm, BindingResult bindingResult, Model model) {
        
    	String redirectPage = tournamentValidator.getRedirectPage(context);
    	
    	
    	tournamentValidator.validate(tournamentForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "update-tournament";
	    }
    	
		tournamentForm.setOwner(principal.getName());
    	tournamentService.updateTournament(tournamentForm);

        return "redirect:/tournaments";
    }     
    
   
    
    
    
    @RequestMapping(value = "/delete-tournament", method = RequestMethod.GET)
    public String deleteEvent(@RequestParam("id") String id, Principal principal) {
        
    	String redirectPage = tournamentValidator.getRedirectPage(context);
    	
    	Tournament tournament = tournamentRepository.findById(Long.parseLong(id));
    	if (!principal.getName().equals(tournament.getOwner())) {
    		return "redirect:/" + redirectPage;
    	}
    	
    	tournamentService.deleteTournament(Long.parseLong(id));
        return "redirect:/" + redirectPage;
    } 
    

}
