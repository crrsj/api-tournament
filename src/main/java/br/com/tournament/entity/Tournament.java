package br.com.tournament.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tournament.dto.TournamentDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_tournament")
@Data
@NoArgsConstructor
public class Tournament {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;	
	private LocalDate startDate;	
	private LocalDate endDate;
	private Double award;	
	@OneToMany(mappedBy = "tournament",cascade =  CascadeType.ALL,orphanRemoval = true)	
	@JsonIgnore
	private List<Player> player = new ArrayList<>();
	
	public Tournament(TournamentDto tournamentDto) {
		this.id = tournamentDto.id();
		this.name = tournamentDto.name();
		this.startDate = tournamentDto.startDate();
		this.endDate = tournamentDto.endDate(); 
		this.award = tournamentDto.award();
		this.player = tournamentDto.player();
	//	this.game = tournamentDto.game();
		
	}


	public void update(TournamentDto tournamentDto) {
		if(tournamentDto.name() != null) {
			this.name  =tournamentDto.name();
			
		}
		
		if(tournamentDto.startDate() != null) {
			this.startDate = tournamentDto.startDate();
		}
		
		if(tournamentDto.endDate() != null) {
			this.endDate = tournamentDto.endDate();
		}
		
		if(tournamentDto.award() != null) {
			this.award = tournamentDto.award();
		}
	}
}
