package com.ekwateur.domain.model;

import java.math.BigDecimal;

public record ChiffreAffaire(String clientReference, BigDecimal chiffreAffaire, int year) {
}
