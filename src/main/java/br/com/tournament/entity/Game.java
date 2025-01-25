package br.com.tournament.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tournament.dto.GameDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_games")
@Data
@NoArgsConstructor
public class Game {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String genre;
	private String platform;
	private String developer;
	@ManyToOne	
	@JoinColumn(name = "playerId")	
	@JsonIgnore
	private Player player;
	@ManyToOne		
	@JoinColumn(name = "tournamentId")	
	@JsonIgnore
	private Tournament tournament;
	
	public Game(GameDto gameDto) {
		this.id = gameDto.id();
		this.name = gameDto.name();
		this.genre = gameDto.genre();
		this.platform = gameDto.platform();
		this.developer = gameDto.developer();
		this.player = gameDto.player();
		this.tournament = gameDto.tournament();
	}

	public void Update(GameDto gameDto) {
	if(gameDto != null) {
		this.name = gameDto.name();		
	}
	 
	if(gameDto.genre() != null) {
		this.genre = gameDto.genre();
	}
	
	if(gameDto.platform() != null) {
		this.platform = gameDto.platform();
		
	}
	 
	if(gameDto.developer() != null) {
		this.developer = gameDto.developer();
	}
	}
}
