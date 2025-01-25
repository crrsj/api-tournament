package br.com.tournament.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.tournament.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

	@Query(value = "select g from Game g where upper(trim(g.genre)) like %?1% ") 
	Optional<Game> findByGenre(String genre);

	@Query(value = "select g from Game g where upper(trim(g.name)) like %?1% ") 
	Optional<Game> findByNome(String name);

}
