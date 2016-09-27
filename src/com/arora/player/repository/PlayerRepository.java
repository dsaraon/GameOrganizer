package com.arora.player.repository;


import com.arora.player.model.Player;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
	Player findByUserid(Long id);
    
}