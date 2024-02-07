package com.ekwateur.infra.adapter;

import com.ekwateur.application.port.out.ConsommationEnergieRepository;
import com.ekwateur.domain.enums.EnergyType;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.YearMonth;

public class ConsommationEnergieRepositoryImpl implements ConsommationEnergieRepository {

    @Override
    public Mono<BigDecimal> getConsommationEnergieMensuelle(String clientReference, EnergyType energyType, YearMonth month) {
        // Ici, vous chercheriez dans votre base de données la consommation d'énergie mensuelle du client pour le type d'énergie donné
        // Pour l'instant, je vais seulement retourner une valeur fictive
        return Mono.just(BigDecimal.ZERO);
    }
}