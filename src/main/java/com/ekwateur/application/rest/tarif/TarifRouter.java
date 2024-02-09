package com.ekwateur.application.rest.tarif;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TarifRouter {

    @Bean
    public RouterFunction<ServerResponse> tarifRoute(TarifHandler clientHandler) {
        return route(GET("/tarif/{id}/{energy}/{month}/{year}"), clientHandler::calculTarif)
                .filter(this::validateRequest);
    }

    private Mono<ServerResponse> validateRequest(ServerRequest request, HandlerFunction<ServerResponse> next) {
        var id = request.pathVariable("id");
        final Pattern pattern = Pattern.compile("EKW\\d\\d\\d\\d\\d\\d\\d\\d", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(id);
        var isIdValid = matcher.matches();
        if (!isIdValid) return ServerResponse.badRequest().build();

        try {
            request.pathVariable("energy");
            Integer.parseInt(request.pathVariable("month"));
            Integer.parseInt(request.pathVariable("year"));
        } catch (Exception e){
            return ServerResponse.badRequest().build();
        }

        return next.handle(request);
    }
}
