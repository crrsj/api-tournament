package br.com.tournament.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.tournament.dto.TournamentDto;
import br.com.tournament.entity.Tournament;
import br.com.tournament.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TournamentService {

	
	private final TournamentRepository tournamentRepository;
	
	public Tournament createTournament(TournamentDto tournamentDto){
		var tournament = new Tournament(tournamentDto);
		return tournamentRepository.save(tournament);
	}
	
	public List<TournamentDto>finfAllTournaments(){
		var list = tournamentRepository.findAll().stream().map(TournamentDto::new).toList();
		return list;
	}
	
	public Tournament findById(Long id) {
		Optional<Tournament>findId = tournamentRepository.findById(id);
		return findId.get();
	}
	
	public Tournament updateTournament(TournamentDto tournamentDto) {
		var findId = tournamentRepository.getReferenceById(tournamentDto.id());
		findId.update(tournamentDto);
		return tournamentRepository.save(findId);
	}
	
	public void deleteTournament(Long id) {
		tournamentRepository.deleteById(id);
	}
}
