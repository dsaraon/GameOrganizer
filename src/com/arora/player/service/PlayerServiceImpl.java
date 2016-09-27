package com.arora.player.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arora.account.model.User;
import com.arora.account.repository.UserRepository;
import com.arora.event.repository.EventRepository;
import com.arora.player.model.Player;
import com.arora.player.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService{
	
	@Autowired
    private PlayerRepository playerRepository;

	/* GAME */
	
	@Override
	public void joinGame(Player player, String eventId) {
		
		String list= player.getGames_ids();
		List<String> arr = null;
		
		if (list == null) {
			arr = new ArrayList<String>();
		} else {
			arr = new ArrayList<String>(Arrays.asList(list.split(",")));
			arr.removeAll(Arrays.asList(null,""));
		}
		
		arr.add(eventId);
		
		String idList = arr.toString();
		System.out.println(idList);
		String csv = idList.substring(1, idList.length() - 1).replace(", ", ",");

		player.setGames_ids(csv);
		playerRepository.saveAndFlush(player);

	}

	@Override
	public void leaveGame(Player player, String eventId) {
				
		String list = player.getGames_ids();
		List<String> arr = new ArrayList<String>(Arrays.asList(list.split(",")));
		arr.removeAll(Arrays.asList(null,""));
		arr.remove(eventId);
		
		String idList = arr.toString();
		String csv = idList.substring(1, idList.length() - 1).replace(", ", ",");
		
		player.setGames_ids(csv);
		playerRepository.saveAndFlush(player);
	}

	/* TOURNAMENT */
	
	@Override
	public void joinTournament(Player player, String tournamentId) {
		
		String list= player.getTournaments_ids();
		List<String> arr = null;
		
		if (list == null) {
			arr = new ArrayList<String>();
		} else {
			arr = new ArrayList<String>(Arrays.asList(list.split(",")));
			arr.removeAll(Arrays.asList(null,""));
		}
		
		arr.add(tournamentId);
		
		String idList = arr.toString();
		System.out.println(idList);
		String csv = idList.substring(1, idList.length() - 1).replace(", ", ",");

		player.setTournaments_ids(csv);
		playerRepository.saveAndFlush(player);

	}	
	
	@Override
	public void leaveTournament(Player player, String tournamentId) {
				
		String list = player.getTournaments_ids();
		List<String> arr = new ArrayList<String>(Arrays.asList(list.split(",")));
		arr.removeAll(Arrays.asList(null,""));
		arr.remove(tournamentId);
		
		String idList = arr.toString();
		String csv = idList.substring(1, idList.length() - 1).replace(", ", ",");
		
		player.setTournaments_ids(csv);
		playerRepository.saveAndFlush(player);
	}	
	
	
	
	@Override
	public boolean checkGameInList(Player player, String eventId) {
		
		String list = player.getGames_ids();
		List<String> arr = new ArrayList<String>(Arrays.asList(list.split(",")));
		
		if (arr.contains(eventId)) {
			return true;
		} else {
			return false;
		}
		
	}
	
		
}
