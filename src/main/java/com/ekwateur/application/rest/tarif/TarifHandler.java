package com.ekwateur.application.rest.tarif;

import com.ekwateur.domain.port.in.TarifUseCase;
import com.ekwateur.domain.enums.EnergyType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

import static com.ekwateur.application.rest.tarif.TarifRouter.ERROR;

@Slf4j
@Component
@AllArgsConstructor
public class TarifHandler {

    public static final String MESSAGE_NOT_FOUND = "Calcul de tarif non trouvé pour le client ou la période en entrée. " +
            "Les references client doivent être dans l'intervalle [EKW00000000, EKW00000009]. " +
            "Et la période O1/2024";

    private final TarifUseCase clientService;

    public Mono<ServerResponse> calculTarif(ServerRequest request) {
        var id = request.pathVariable("id");
        var energy = request.pathVariable("energy");
        var month = Integer.parseInt(request.pathVariable("month"));
        var year = Integer.parseInt(request.pathVariable("year"));
        log.info("#######################################################################################");

        Mono<BigDecimal> tarif = clientService
                .calculateEnergyUsage(id, EnergyType.valueOf(energy.toUpperCase()), month, year);
        return tarif.flatMap(value ->
                        ServerResponse.ok().bodyValue(value))
                .switchIfEmpty(ServerResponse
                        .status(HttpStatus.NOT_FOUND)
                        .bodyValue(Map.of(ERROR, MESSAGE_NOT_FOUND))
                );
    }
}