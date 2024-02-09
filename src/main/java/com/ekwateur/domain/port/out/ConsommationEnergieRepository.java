package com.ekwateur.domain.port.out;

import com.ekwateur.domain.enums.EnergyType;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.YearMonth;

public interface ConsommationEnergieRepository {
    Mono<BigDecimal> getConsommationEnergieMensuelle(String clientReference, EnergyType energyType, YearMonth month);
}