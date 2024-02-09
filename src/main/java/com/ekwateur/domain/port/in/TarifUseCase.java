package com.ekwateur.domain.port.in;

import com.ekwateur.domain.enums.EnergyType;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface TarifUseCase {
    Mono<BigDecimal> calculateEnergyUsage(String clientReference, EnergyType typeDEnergie, int month, int year);
}