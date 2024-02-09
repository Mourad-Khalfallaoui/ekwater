package com.ekwateur.application.rest.tarif;

import com.ekwateur.domain.enums.EnergyType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TarifRouter {

    public static final String MESSAGE_BAD_REQUEST_ID = "Veuillez fournir une valeur valide pour la référence client";
    public static final String MESSAGE_BAD_REQUEST_MONTH_YEAR = "Veuillez fournir des valeurs valides pour le mois et l'année";
    public static final String MESSAGE_BAD_REQUEST_ENERGY = "Veuillez fournir une valeur valide pour le type d'énergie";
    public static final String ERROR = "error";

    @Bean
    public RouterFunction<ServerResponse> tarifRoute(TarifHandler clientHandler) {
        return route(GET("/tarif/{id}/{energy}/{month}/{year}"), clientHandler::calculTarif)
                .filter(this::validateRequest);
    }

    private Mono<ServerResponse> validateRequest(ServerRequest request, HandlerFunction<ServerResponse> next) {
        var isIdValid = isIdValid(request);
        if (!isIdValid)
            return badRequest(MESSAGE_BAD_REQUEST_ID);

        try {
            EnergyType.valueOf(request.pathVariable("energy").toUpperCase());
            Integer.parseInt(request.pathVariable("month"));
            Integer.parseInt(request.pathVariable("year"));
        } catch (NumberFormatException e) {
            return badRequest(MESSAGE_BAD_REQUEST_MONTH_YEAR);
        } catch (IllegalArgumentException e) {
            return badRequest(MESSAGE_BAD_REQUEST_ENERGY);
        }

        return next.handle(request);
    }

    private static boolean isIdValid(ServerRequest request) {
        var id = request.pathVariable("id");
        final Pattern pattern = Pattern.compile("EKW\\d\\d\\d\\d\\d\\d\\d\\d", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    private static Mono<ServerResponse> badRequest(String message) {
        return ServerResponse
                .status(HttpStatus.BAD_REQUEST)
                .bodyValue(
                        Map.of(ERROR, message));
    }
}
