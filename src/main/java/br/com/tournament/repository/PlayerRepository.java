package br.com.tournament.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.tournament.entity.Player;


public interface PlayerRepository extends JpaRepository<Player, Long> {

	@Query(value = "select p from Player p where upper(trim(p.nickName)) like %?1% ") 	
    Optional<Player> findByNickName(String nickName);

}
