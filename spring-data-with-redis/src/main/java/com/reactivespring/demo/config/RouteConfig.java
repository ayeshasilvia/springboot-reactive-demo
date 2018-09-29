package com.reactivespring.demo.config;

import com.reactivespring.demo.GameHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouteConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(GameHandler handler){
        return route(GET("/reactive/games"), handler::getAllgames)
                .andRoute(GET("/reactive/games/{id}"), handler::getGameById)
                .andRoute(GET("/reactive/games/{id}/events"), handler::getGameEvents);
    }

}
