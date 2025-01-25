package br.com.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tournament.entity.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}
