package com.reactivespring.demo.controller;

import com.reactivespring.demo.GameService;
import com.reactivespring.demo.model.Game;
import com.reactivespring.demo.model.GameEvent;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/games")
@Log
public class GameTraditionalMVC {

    private final GameService service;

    public GameTraditionalMVC(GameService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<Game> getAllgames()
    {
        log.info("Received a request for games list");
        return this.service.getAllGames();
    }

    @GetMapping("/{id}")
    public Mono<Game> getGameById(@PathVariable String id)
    {
        log.info("Received a request for a game with id: " + id);
        return this.service.getGameById(id);
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<GameEvent> getGameEvents(@PathVariable String id)
    {
        log.info("Received a request for events with id: " + id);
        return this.service.getEvents(id);
    }

}
