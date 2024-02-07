package com.ekwateur.infra.adapter;

import com.ekwateur.application.port.out.FinancialDataRepository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public class FinancialDataRepositoryImpl implements FinancialDataRepository {
    @Override
    public Mono<BigDecimal> getChiffreAffaireAnnuel(String clientReference, int year) {
        return Mono.just(BigDecimal.ZERO);
    }
}
