package br.com.tournament.dto;

import java.util.List;

import br.com.tournament.entity.Game;
import br.com.tournament.entity.Player;
import br.com.tournament.entity.Tournament;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlayerDto(
		Long id,
		@NotBlank(message = "cannot be blank")
		String nickName,
		@NotNull(message = "cannot be blank")
		Integer age,
		@NotBlank(message = "cannot be blank")
		String country,
		Integer score, 
		Tournament tournament,
		List<Game> game     
		                  ) {

	public PlayerDto(Player player) {
		this(
				player.getId(),
				player.getNickName(),
				player.getAge(), 
				player.getCountry(),
				player.getScore(),
				player.getTournament(),
				player.getGame() 
				                );
	}
}
