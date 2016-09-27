package com.arora.tournament.service;

import java.security.Principal;
import java.util.List;
import com.arora.tournament.model.Tournament;

public interface TournamentService {
	void save(Tournament tournament, Principal principal);
	void updateTournament(Tournament tournament);
	List<Tournament> getTournaments(String date, String time);
	void deleteTournament(Long id);
}
