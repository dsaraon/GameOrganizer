package com.arora.player.web;


import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.Interval;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.arora.account.model.User;
import com.arora.account.repository.UserRepository;
import com.arora.account.service.UserService;
import com.arora.event.model.Event;
import com.arora.event.repository.EventRepository;
import com.arora.event.service.EventService;
import com.arora.player.model.Player;
import com.arora.player.repository.PlayerRepository;
import com.arora.player.service.PlayerService;
import com.arora.player.validator.PlayerValidator;
import com.arora.tournament.model.Tournament;
import com.arora.tournament.repository.TournamentRepository;



@Controller
public class PlayerController {
	
    @Autowired
	private PlayerService playerService;

    @Autowired
	private PlayerRepository playerRepository;    
    
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;    

    @Autowired
    private TournamentRepository tournamentRepository;
    
    @Autowired
    private HttpServletRequest context;
    
    /* GAMES */
    
    @RequestMapping(value = "/join-game", method = RequestMethod.GET)
    public String joinGame(Principal principal, @RequestParam("id") String eventId) throws ParseException {
        
    	String referer = context.getHeader("Referer");
    	String pageName = referer.substring(referer.lastIndexOf("/")+1);
    	
    	String redirectPage = "welcome";
    	if (!pageName.isEmpty()) {
    		redirectPage = pageName;
    	}
    	
    	User user = userRepository.findByUsername(principal.getName());
    	Player player = playerRepository.findByUserid(user.getId());
    	Event event = eventRepository.findById(Long.parseLong(eventId));
    	
    	/* validation here */
    	PlayerValidator validator = new PlayerValidator();
    	boolean isCloseGame = validator.isCloseGame(event, player, eventRepository);

    	if (!isCloseGame) {
    		System.out.println("contains");
    		playerService.joinGame(player, eventId);
    		return "redirect:/" + redirectPage;
    	} 
    
    	return "redirect:/" + redirectPage + "?error";
		
    }    
    
    @RequestMapping(value = "/leave-game", method = RequestMethod.GET)
    public String leaveGame(Principal principal, @RequestParam("id") String eventId) {
        
    	String referer = context.getHeader("Referer");
    	String pageName = referer.substring(referer.lastIndexOf("/")+1);
    	
    	String redirectPage = "welcome";
    	if (!pageName.isEmpty()) {
    		redirectPage = pageName;
    	}
    	
    	/* validation here */
    	
    	User user = userRepository.findByUsername(principal.getName());
    	Player player = playerRepository.findByUserid(user.getId());

    	playerService.leaveGame(player, eventId);
    	return "redirect:/"+redirectPage;
    }
	
    /* TOURNAMENTS */    

    @RequestMapping(value = "/join-tournament", method = RequestMethod.GET)
    public String joinTournament(Principal principal, @RequestParam("id") String tournamentId) throws ParseException {
        
    	String referer = context.getHeader("Referer");
    	String pageName = referer.substring(referer.lastIndexOf("/")+1);
    	
    	String redirectPage = "welcome";
    	if (!pageName.isEmpty()) {
    		redirectPage = pageName;
    	}
    	
    	
    	User user = userRepository.findByUsername(principal.getName());
    	Player player = playerRepository.findByUserid(user.getId());
    	Tournament tournament = tournamentRepository.findById(Long.parseLong(tournamentId));
    	
    	/* validation here */
    	PlayerValidator validator = new PlayerValidator();
    	boolean isCloseGame = validator.isCloseTournament(tournament, player, tournamentRepository);

    	if (!isCloseGame) {
    		System.out.println("contains");
    		playerService.joinTournament(player, tournamentId);
    		return "redirect:/" + redirectPage;
    	} 
    
    	return "redirect:/" + redirectPage + "?error";
		
    }     
 
    @RequestMapping(value = "/leave-tournament", method = RequestMethod.GET)
    public String leaveTournament(Principal principal, @RequestParam("id") String tournamentId) {
        
    	String referer = context.getHeader("Referer");
    	String pageName = referer.substring(referer.lastIndexOf("/")+1);
    	
    	String redirectPage = "welcome";
    	if (!pageName.isEmpty()) {
    		redirectPage = pageName;
    	}
    	
    	/* validation here */
    	
    	User user = userRepository.findByUsername(principal.getName());
    	Player player = playerRepository.findByUserid(user.getId());

    	playerService.leaveTournament(player, tournamentId);
    	return "redirect:/" + redirectPage;
    }  
    

    
    @RequestMapping(value = "/count/{event}/{id}", produces = "application/json", method = RequestMethod.GET)
    public String countMember(Model model, Principal principal, @PathVariable("event") String event, @PathVariable("id") String id) {    
        	
    	int s = 0;
    	boolean success = false;
    	
    	if ("game".equals(event)) {
    		
    		List<Player> players = playerRepository.findAll();
    		
    		for (int i = 0; i < players.size(); i++) {
    			Player pl = players.get(i);
    			String gamesId = pl.getGames_ids();
    			if (gamesId != null) {
        			if (gamesId.contains(id)) {
        				s++;
        			}
    			}
    		}
    		
    		success = true;
    		
    	}
    	
    	if ("tournament".equals(event)) {

    		List<Player> players = playerRepository.findAll();
    		
    		for (int i = 0; i < players.size(); i++) {
    			Player pl = players.get(i);
    			String tournamenstIds = pl.getTournaments_ids();
    			if (tournamenstIds != null) {
        			if (tournamenstIds.contains(id)) {
        				s++;
        			}
    			}
    		}
    		
    		success = true;
    		
    	}
    	
    	
    	model.addAttribute("success", success);
    	model.addAttribute("joined", s);
    	
    	return "count";
    }
    
	    
}
