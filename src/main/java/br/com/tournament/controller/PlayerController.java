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

import br.com.tournament.dto.PlayerDto;
import br.com.tournament.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {

	private final PlayerService playerService;
	
	
	@PostMapping("{tournamentId}")
	@Operation(summary = "Endpoint responsible for registering players.") 
    @ApiResponse(responseCode = "201",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })        
	public ResponseEntity<PlayerDto>createPlayer(@RequestBody @Valid PlayerDto playerDto,@PathVariable Long tournamentId){
		var create = playerService.createPlayer(playerDto, tournamentId);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(create.getId()).toUri();
		return ResponseEntity.created(uri).body(new PlayerDto(create));
	}
	
	
	@GetMapping
	@Operation(summary = "Endpoint responsible for listing all players.") 
    @ApiResponse(responseCode = "200",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<List<PlayerDto>>findAllPlayers(){
		var findAll = playerService.findAllPlayers();
		return ResponseEntity.ok(findAll);
	}
	
	
	@GetMapping("{id}")
	@Operation(summary = "Endpoint responsible for seach player by id.") 
    @ApiResponse(responseCode = "200",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<PlayerDto>findById(@PathVariable Long id){
		var findId = playerService.findById(id);
		return ResponseEntity.ok().body(new PlayerDto(findId));
	}
	
	
	@PutMapping
	@Operation(summary = "Endpoint responsible for update players.") 
    @ApiResponse(responseCode = "200",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<PlayerDto>updatePlayer(@RequestBody @Valid PlayerDto playerDto){
		var update = playerService.updatePlayer(playerDto);
		return ResponseEntity.ok().body(new PlayerDto(update));
	}
	
	@GetMapping("nickName")
	@Operation(summary = "Endpoint responsible for search player by nickName.") 
    @ApiResponse(responseCode = "200",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<PlayerDto>findByNickName(@PathParam("nickName") String nickName){
		var findNickname = playerService.findByNickname(nickName);
		return ResponseEntity.ok().body(new PlayerDto(findNickname));
	}
	
	@DeleteMapping("{Id}")
	@Operation(summary = "Endpoint responsible for delete player by id.") 
    @ApiResponse(responseCode = "204",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
		playerService.deletePlayer(id);
		return ResponseEntity.noContent().build();
	}
	
}
