package br.com.tournament.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tournament.dto.PlayerDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_player")
@Data
@NoArgsConstructor
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nickName;
	private Integer age;
	private String country;
	private Integer score;
	@ManyToOne	
	@JoinColumn(name = "tournamentId")	
	@JsonIgnore
	private Tournament tournament;	
	@OneToMany(mappedBy = "player",cascade = CascadeType.ALL,orphanRemoval = true )	
	private List<Game> game = new ArrayList<>();
	
	
	

	public Player(PlayerDto playerDto) {
		this.id = playerDto.id();
		this.nickName = playerDto.nickName();
		this.age = playerDto.age(); 
		this.country = playerDto.country();
		this.score = playerDto.score();		
		this.tournament = playerDto.tournament();
		this.game = playerDto.game();
	}



	public void update(PlayerDto playerDto) {

     if(playerDto.nickName() != null) {
    	 this.nickName = playerDto.nickName();
    	 
     }
		
     if(playerDto.country() != null) {
    	 this.country = playerDto.country();
     }
	}
	
}
