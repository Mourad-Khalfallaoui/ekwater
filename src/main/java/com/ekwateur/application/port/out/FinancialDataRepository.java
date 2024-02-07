package com.ekwateur.application.port.out;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface FinancialDataRepository {
    Mono<BigDecimal> getChiffreAffaireAnnuel(String clientReference, int year);

}
