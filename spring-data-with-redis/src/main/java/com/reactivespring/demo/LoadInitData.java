package com.reactivespring.demo;

import com.reactivespring.demo.model.Game;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class LoadInitData implements ApplicationRunner {

    private final GameRepository repo;

    public LoadInitData(GameRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        repo.deleteAll();

        Flux.just("The Sims 4", "Hole.io", "Age of Empires")
                .map(g -> new Game(null, g))
                .map(repo::save)
                .subscribe(System.out::println);

    }
}

