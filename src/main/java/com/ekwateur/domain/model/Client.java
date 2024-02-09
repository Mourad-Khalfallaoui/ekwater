package com.ekwateur.domain.model;

public interface Client {
    default double calculateEnergyUsage(double kwh, double tarif) {
        return kwh * tarif;
    }

}