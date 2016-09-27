package com.arora.tournament.service;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.arora.account.model.User;
import com.arora.event.model.Event;
import com.arora.event.repository.EventRepository;
import com.arora.tournament.model.Tournament;
import com.arora.tournament.repository.TournamentRepository;

@Service
public class TournamentServiceImpl implements TournamentService {
    
	@Autowired
    private TournamentRepository tournamentRepository;

    @Override
    public void save(Tournament tournament, Principal principal) {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    	
    	try {
			tournament.setDateandtime(sdf.parse(tournament.getDate() + " " + tournament.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	tournament.setOwner(principal.getName());
    	tournamentRepository.save(tournament);
    }

	@Override
	public List<Tournament> getTournaments(String date, String time) {
		
		List<Tournament> list = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		
		try {
			
			if (!StringUtils.isNotBlank(time)) {
				time = "00:00 AM";
			}
			
			if (!StringUtils.isNotBlank(date)) {
				date = "01/01/1970";
			}
			
			if (StringUtils.isNotBlank(date) && StringUtils.isNotBlank(time)) {
				System.out.println("select only date and time");
				list = tournamentRepository.findByDateandtimeGreaterThan(sdf.parse(date + " " + time));
			}
			
			if (!StringUtils.isNotBlank(time) && !StringUtils.isNotBlank(date)) {
				System.out.println("select all");
				list = tournamentRepository.findAll();			
			}
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return list;
		
		
	}

	@Override
	public void deleteTournament(Long id) {
		Tournament tournament = tournamentRepository.findById(id);
		tournamentRepository.delete(tournament);
	}

	@Override
	public void updateTournament(Tournament tournament) {
		
		Tournament tournament2 = tournamentRepository.findById(tournament.getId());
		
		System.out.println("OWNER BLA");
		System.out.println(tournament.getOwner());
		
		tournament2.setDate(tournament.getDate());
		tournament2.setTime(tournament.getTime());
		tournament2.setLocation(tournament.getLocation());
		tournament2.setNum_teams(tournament.getNum_teams());
		tournament2.setNum_per_team(tournament.getNum_per_team());
		tournament2.setOwner(tournament.getOwner());    	
		tournament2.setCity(tournament.getCity());
		
		tournamentRepository.saveAndFlush(tournament2);		
		
	}

}
