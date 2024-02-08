package com.ekwateur.domain;

import java.math.BigDecimal;

public record ChiffreAffaire(String clientReference, BigDecimal chiffreAffaire, int year) {
}
