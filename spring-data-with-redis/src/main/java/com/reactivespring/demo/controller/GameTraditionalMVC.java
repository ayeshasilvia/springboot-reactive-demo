package com.reactivespring.demo.controller;

import com.reactivespring.demo.GameService;
import com.reactivespring.demo.model.Game;
import com.reactivespring.demo.model.GameEvent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/games")
public class GameTraditionalMVC {

    private final GameService service;

    public GameTraditionalMVC(GameService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<Game> getAllgames()
    {
        return this.service.getAllGames();
    }

    @GetMapping("/{id}")
    public Mono<Game> getGameById(@PathVariable String id)
    {
        return this.service.getGameById(id);
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<GameEvent> getGameEvents(@PathVariable String id)
    {
        return this.service.getEvents(id);
    }

}
