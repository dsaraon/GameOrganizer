package com.arora.tournament.repository;

import java.util.Date;
import java.util.List;

import com.arora.tournament.model.Tournament;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
	Tournament findById(Long id);
	List<Tournament> findByDate(String date);
	List<Tournament> findByTime(String time);
	List<Tournament> findByDateAndTime(String date, String time);
	List<Tournament> findByOwner(String owner);
	List<Tournament> findByDateandtimeGreaterThan(Date date);
}