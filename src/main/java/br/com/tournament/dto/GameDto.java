package br.com.tournament.dto;

import br.com.tournament.entity.Game;
import br.com.tournament.entity.Player;
import br.com.tournament.entity.Tournament;
import jakarta.validation.constraints.NotBlank;

public record GameDto(
		Long id,
		@NotBlank(message = "não pode estar em branco")
		String name,
		@NotBlank(message = "não pode estar em branco")
		String genre,
		@NotBlank(message = "não pode estar em branco")
		String platform,	
		@NotBlank(message = "não pode estar em branco")
		String developer,		
	    Player player ,
	    Tournament tournament
		                ) {
     
	public GameDto(Game game) {
		this(
				game.getId(),
				game.getName(),
				game.getGenre(),
				game.getPlatform(),
				game.getDeveloper(),
				game.getPlayer(),
				game.getTournament() );
	}
}
