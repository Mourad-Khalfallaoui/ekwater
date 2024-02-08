package com.ekwateur.domain;

import com.ekwateur.domain.enums.EnergyType;

import java.math.BigDecimal;
import java.time.YearMonth;

public record ConsommationEnergie(String referenceClient, EnergyType energyType, BigDecimal quantity, YearMonth yearMonth) {
}
