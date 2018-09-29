package com.reactivespring.demo;

import com.reactivespring.demo.model.Game;
import com.reactivespring.demo.model.GameEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@Service
public class GameService {
    private final GameRepository gameRepository;


    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Flux<Game> getAllGames()
    {
        return Flux.fromIterable(this.gameRepository.findAll());
    }

    public Mono<Game> getGameById(String id)
    {
        return Mono.justOrEmpty(this.gameRepository.findById(id));
    }

    public Flux<GameEvent> getEvents(String gameId)
    {
        return Flux.<GameEvent>generate(s -> s.next(new GameEvent(gameId, new Date())))
                .delayElements(Duration.ofSeconds(1));
    }
}
