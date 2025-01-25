package br.com.tournament.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.tournament.dto.GameDto;
import br.com.tournament.entity.Game;
import br.com.tournament.repository.GameRepository;
import br.com.tournament.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {
	
	
	private final GameRepository gameRepository;
	private final PlayerRepository playerRepository;

	public Game creategame(GameDto gameDto, Long playerId) {
		var game = new Game(gameDto);
		var player = playerRepository.findById(playerId).get();
		game.setPlayer(player);
		return gameRepository.save(game);
	}
	
	public List<GameDto>FindAllGames() {
		return gameRepository.findAll().stream().map(GameDto::new).toList();
	}
	
	public Game findById(Long id) {
		Optional<Game>find = gameRepository.findById(id);
		return find.get();
	}
	
	public Game UpdateGame(GameDto gameDto) {
		var findGame = gameRepository.getReferenceById(gameDto.id());
		findGame.Update(gameDto);
		return gameRepository.save(findGame);
	}
	
	public Game findByGenre(String genre) {
		Optional<Game> findGenre = gameRepository.findByGenre(genre.trim().toUpperCase());
		return findGenre.get();
	}
	
	public void deleteGame(Long id) {
		gameRepository.deleteById(id);
	}
	
	public Game findGameByName(String name) {
		Optional<Game>find = gameRepository.findByNome(name.trim().toUpperCase());
		return find.get();
	}
}
