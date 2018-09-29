package com.reactivespring.demo;

import com.reactivespring.demo.model.Game;
import com.reactivespring.demo.model.GameEvent;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class GameHandler {
    private final GameService service;

    public GameHandler(GameService service) {
        this.service = service;
    }

    public Mono<ServerResponse> getAllgames(ServerRequest request) {
        return ServerResponse.ok()
                .body(service.getAllGames(), Game.class);
    }

    public Mono<ServerResponse> getGameById(ServerRequest request) {
        return ServerResponse.ok()
                .body(service.getGameById(request.pathVariable("id")), Game.class);
    }

    public Mono<ServerResponse> getGameEvents(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(service.getEvents(request.pathVariable("id")), GameEvent.class);
    }
}
