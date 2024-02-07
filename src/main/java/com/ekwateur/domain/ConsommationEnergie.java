package com.ekwateur.domain;

import java.math.BigDecimal;
import java.time.YearMonth;

public record ConsommationEnergie(String referenceClient, BigDecimal quantity, YearMonth month) {
}
