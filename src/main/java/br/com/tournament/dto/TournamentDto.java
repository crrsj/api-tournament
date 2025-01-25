package br.com.tournament.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.tournament.entity.Player;
import br.com.tournament.entity.Tournament;
import jakarta.validation.constraints.NotBlank;

public record TournamentDto(
		Long id,
		@NotBlank(message = "cannot be blank")
		String name,		
		@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
		LocalDate startDate, 
		@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
		LocalDate endDate,
		Double award,	
	    List<Player> player
		                     ) {
	public TournamentDto(Tournament tournament) {
		this(
				tournament.getId(),
				tournament.getName(),
				tournament.getStartDate(),
				tournament.getEndDate(),
				tournament.getAward(),			
				tournament.getPlayer());
	}

	
}
