package com.ekwateur.domain;

import com.ekwateur.domain.enums.ClientType;

public interface Client {
    default double calculateEnergyUsage(double kwh, double tarif) {
        return kwh * tarif;
    }

}