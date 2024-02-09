package com.ekwateur.domain.port.out;

import com.ekwateur.domain.enums.ClientType;
import com.ekwateur.domain.enums.EnergyType;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface TarifRepository {
    Mono<BigDecimal> getTarif(EnergyType energyType, ClientType typeClient, BigDecimal chiffreAffaire);
}