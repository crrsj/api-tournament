package br.com.tournament.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.tournament.dto.PlayerDto;
import br.com.tournament.entity.Player;
import br.com.tournament.repository.PlayerRepository;
import br.com.tournament.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService {

	public final PlayerRepository playerRepository;
	private final TournamentRepository tournamentRepository;
	
	public Player createPlayer(PlayerDto playerDto,Long tournamentId) {
		var newPlayer  = new Player(playerDto);
		var tournnament = tournamentRepository.findById(tournamentId).get();
		newPlayer.setTournament(tournnament);
		return playerRepository.save(newPlayer);
	}
	
	public List<PlayerDto>findAllPlayers(){
		return playerRepository.findAll().stream().map(PlayerDto::new).toList();
	}
	
	public Player findByNickname(String nickName) {
		Optional<Player> find = playerRepository.findByNickName(nickName.trim().toUpperCase());
		return find.get();
	}
	
	public Player updatePlayer(PlayerDto playerDto) {
		var findPlayer = playerRepository.getReferenceById(playerDto.id());
		findPlayer.update(playerDto);
		return playerRepository.save(findPlayer);
	}
	
	public Player findById(Long id) {
		Optional<Player>findId = playerRepository.findById(id);
		return findId.get();
	}
	
	public void deletePlayer(Long id) {
		playerRepository.deleteById(id);
	}
}
