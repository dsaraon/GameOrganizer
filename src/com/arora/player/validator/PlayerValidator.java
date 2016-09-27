package com.arora.player.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.joda.time.Interval;

import com.arora.event.model.Event;
import com.arora.event.repository.EventRepository;
import com.arora.player.model.Player;
import com.arora.tournament.model.Tournament;
import com.arora.tournament.repository.TournamentRepository;


public class PlayerValidator {

	/* GAME */
	
	public boolean isCloseGame(Event event, Player player, EventRepository eventRepository) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
    	String eventDate = event.getDate();
    	String eventTime = event.getTime();
    	
    	String list = player.getGames_ids();
    	boolean closeGame = false;
    	
    	if (list !=null) {
    		
    		List<String> arr = new ArrayList<String>(Arrays.asList(list.split(",")));
 
    		for(int i = 0; i < arr.size(); i++) {

    			String s = arr.get(i);
    			
    			if (!s.isEmpty()) {

    				Event ev = eventRepository.findById(Long.valueOf(arr.get(i)));
    								
    				if (eventDate.equals(ev.getDate())) {
    					Date d1 = sdf.parse(ev.getTime());
    					Date d2 = sdf.parse(eventTime);

    					Interval interval = null;	
    					
    					if (d1.getTime() > d2.getTime()) {
    						interval = new Interval(d2.getTime(),d1.getTime());
    					}
    					else {
    						interval = new Interval(d1.getTime(),d2.getTime());
    					}
    						
    					if (interval.toDurationMillis() < 3599999) {
    						closeGame = true;
    						break;
    					}
    					
    				}
    			}
    		}
    	}
		
		return closeGame;
	}
	
	/* TOURNAMENT */
	
	public boolean isCloseTournament(Tournament tournament, Player player, TournamentRepository tournamentRepository) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
    	String tournamentDate = tournament.getDate();
    	String tournamentTime = tournament.getTime();
    	
    	String list = player.getTournaments_ids();
    	boolean closeTournament = false;
    	
    	if (list !=null) {
    		
    		List<String> arr = new ArrayList<String>(Arrays.asList(list.split(",")));
 
    		for(int i = 0; i < arr.size(); i++) {

    			String s = arr.get(i);
    			
    			if (!s.isEmpty()) {

    				Tournament ev = tournamentRepository.findById(Long.valueOf(arr.get(i)));
    								
    				if (tournamentDate.equals(ev.getDate())) {
    					Date d1 = sdf.parse(ev.getTime());
    					Date d2 = sdf.parse(tournamentTime);

    					Interval interval = null;	
    					
    					if (d1.getTime() > d2.getTime()) {
    						interval = new Interval(d2.getTime(),d1.getTime());
    					}
    					else {
    						interval = new Interval(d1.getTime(),d2.getTime());
    					}
    						
    					if (interval.toDurationMillis() < 3599999) {
    						closeTournament = true;
    						break;
    					}
    					
    				}
    			}
    		}
    	}
		
		return closeTournament;
	}
	
}
