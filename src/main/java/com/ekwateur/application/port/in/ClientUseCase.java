package com.ekwateur.application.port.in;

import com.ekwateur.domain.enums.EnergyType;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.YearMonth;

public interface ClientUseCase {
    Mono<BigDecimal> calculateEnergyUsage(String clientReference, EnergyType typeDEnergie, YearMonth month, int year);
}