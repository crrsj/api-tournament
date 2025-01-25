package br.com.tournament.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tournament.dto.TournamentDto;
import br.com.tournament.service.TournamentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tournament")
@RequiredArgsConstructor
public class TournamentController {

	private final TournamentService tournamentService;
	
	
	
	@PostMapping
	@Operation(summary = "Endpoint responsible for registering tournaments .") 
    @ApiResponse(responseCode = "201",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<TournamentDto>createTournament(@RequestBody @Valid TournamentDto tournamentDto){
		var create = tournamentService.createTournament(tournamentDto);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(create.getId()).toUri();
		return ResponseEntity.created(uri).body(new TournamentDto(create));
	}
	
	
	@GetMapping
	@Operation(summary = "Endpoint responsible for listing all tournaments.") 
    @ApiResponse(responseCode = "200",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<List<TournamentDto>>findAllTournaments(){
		var list = tournamentService.finfAllTournaments();
		return ResponseEntity.ok(list);
	}
	
	
	@GetMapping("{id}")
	@Operation(summary = "Endpoint responsible for search tournament by id.")
    @ApiResponse(responseCode = "200",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    }) 
	public ResponseEntity<TournamentDto>findById(@PathVariable Long id){
		var findId = tournamentService.findById(id);
		return ResponseEntity.ok(new TournamentDto(findId));
	}
	
	
	@PutMapping
	@Operation(summary = "Endpoint responsible for update tournaments.") 
    @ApiResponse(responseCode = "200",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    }) 
	public ResponseEntity<TournamentDto>updateTournament(@RequestBody @Valid TournamentDto tournamentDto){
		var update = tournamentService.updateTournament(tournamentDto);
		return ResponseEntity.ok().body(new TournamentDto(update));
	}
	
	
	
	@DeleteMapping("{id}")
	@Operation(summary = "Endpoint responsible for delete tournament by id.") 
    @ApiResponse(responseCode = "204",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    }) 
	public ResponseEntity<Void>deletetournament(@PathVariable Long id){
		tournamentService.deleteTournament(id);
		return ResponseEntity.noContent().build();
		
	}
}
