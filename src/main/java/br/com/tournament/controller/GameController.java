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

import br.com.tournament.dto.GameDto;
import br.com.tournament.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
	
	private final GameService gameService;
	
	@PostMapping("{playerId}")
	@Operation(summary = "Endpoint responsible for registering games.") 
    @ApiResponse(responseCode = "201",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })        
	public ResponseEntity<GameDto>createGame(@RequestBody @Valid GameDto gameDto,@PathVariable Long playerId){
		var create = gameService.creategame(gameDto,playerId);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(create.getId()).toUri();
		return ResponseEntity.created(uri).body(new GameDto(create));
	}

	
	
	@GetMapping("/genre")
	@Operation(summary = "Endpoint responsible for search game by genre.")
    @ApiResponse(responseCode = "200",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    }) 
	public ResponseEntity<GameDto>findByGenre(@PathParam("genre") String genre){
		var findGenre = gameService.findByGenre(genre);
		return  ResponseEntity.ok().body(new GameDto(findGenre));
	}
	
	 
	@PutMapping
	@Operation(summary = "Endpoint responsible for update games.") 
    @ApiResponse(responseCode = "200",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    }) 
	public ResponseEntity<GameDto>updateGame(@RequestBody @Valid GameDto gameDto){
		var update = gameService.UpdateGame(gameDto);
		return ResponseEntity.ok().body(new GameDto(update));
	}
	
	
	@GetMapping("{id}")
	@Operation(summary = "Endpoint responsible for search game by id.")
    @ApiResponse(responseCode = "200",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    }) 
	public ResponseEntity<GameDto>findById(@PathVariable Long id){
		var findId = gameService.findById(id);
		return ResponseEntity.ok().body(new GameDto(findId));
	}
	
	
	@GetMapping
	@Operation(summary = "Endpoint responsible for listing all games.") 
    @ApiResponse(responseCode = "200",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<List<GameDto>>findAllgames(){
		var findAll = gameService.FindAllGames();
		return ResponseEntity.ok(findAll);
	}
	
	
	@DeleteMapping("{id}")
	@Operation(summary = "Endpoint responsible for delete game by id.")
    @ApiResponse(responseCode = "204",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    }) 
	public ResponseEntity<Void>deleteGame(@PathVariable Long id){
		gameService.deleteGame(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/name")
	@Operation(summary = "Endpoint responsible for search game by name.")
    @ApiResponse(responseCode = "200",description = " success",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    }) 
	public ResponseEntity<GameDto>findGameByname(@PathParam("name")String name){
		var findByName = gameService.findGameByName(name);
		return ResponseEntity.ok().body(new GameDto(findByName));
	}
}
