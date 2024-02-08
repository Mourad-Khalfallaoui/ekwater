package com.ekwateur.application.rest.tarif;

import com.ekwateur.application.port.in.ClientUseCase;
import com.ekwateur.domain.enums.EnergyType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class TarifHandler {

    private final ClientUseCase clientService;

    public TarifHandler(ClientUseCase clientService) {
        this.clientService = clientService;
    }

    public Mono<ServerResponse> calculTarif(ServerRequest request) {
        String id = request.pathVariable("id");
        String energy = request.pathVariable("energy");
        int month = Integer.parseInt(request.pathVariable("month"));
        int year = Integer.parseInt(request.pathVariable("year"));

        Mono<BigDecimal> tarif = clientService
                .calculateEnergyUsage(id, EnergyType.valueOf(energy.toUpperCase()), month, year);

        return tarif.flatMap(value ->
                        ServerResponse.ok().bodyValue(value))
                .switchIfEmpty(ServerResponse
                        .status(HttpStatus.NOT_FOUND)
                        .bodyValue(
                                Map.of("error", "Calcul de tarif non trouvé pour le client ou la période en entrée. " +
                                        "Les references client doivent être dans l'interval [EKW00000000, EKW00000009]. " +
                                        "Et la période O1/2024"))
                );
    }
}