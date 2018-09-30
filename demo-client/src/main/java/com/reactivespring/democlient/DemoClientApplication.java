package com.reactivespring.democlient;

import com.reactivespring.democlient.model.Game;
import com.reactivespring.democlient.model.GameEvent;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@Log
public class DemoClientApplication {

	@Bean
	WebClient client(){
		return WebClient
				.create("http://localhost:8080/games")
				.mutate()
				.filter(ExchangeFilterFunctions.basicAuthentication("admin", "pass"))
				.build();
	}

	@Bean
	CommandLineRunner commandLineRunner(WebClient client){
		return args -> {
			client.get()
					.uri("")
					.retrieve()
					.bodyToFlux(Game.class)
					.filter(g -> g.getName().equalsIgnoreCase("The Sims 4"))
					.flatMap(g ->
							client.get()
								.uri("/{id}/events", g.getId())
								.retrieve()
								.bodyToFlux(GameEvent.class))
					.subscribe(ge -> log.info(ge.toString()), Throwable::printStackTrace);

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoClientApplication.class, args);
	}
}
