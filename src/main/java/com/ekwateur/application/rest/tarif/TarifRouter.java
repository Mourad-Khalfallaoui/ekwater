package com.ekwateur.application.rest.tarif;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TarifRouter {

    @Bean
    public RouterFunction<ServerResponse> tarifRoute(TarifHandler clientHandler) {
        return route(GET("/tarif/{id}/{energy}/{month}/{year}"), clientHandler::calculTarif);
    }
}
