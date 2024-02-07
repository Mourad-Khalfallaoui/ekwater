package com.ekwateur.infra.adapter;

import com.ekwateur.application.port.out.TarifRepository;
import com.ekwateur.domain.enums.ClientType;
import com.ekwateur.domain.enums.EnergyType;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public class TarifRepositoryImpl implements TarifRepository {

    @Override
    public Mono<BigDecimal> getTarif(EnergyType energyType, ClientType typeClient, BigDecimal chiffreAffaire) {
        return Mono.just(BigDecimal.ZERO);
    }
}