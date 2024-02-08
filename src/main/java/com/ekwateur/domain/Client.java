package com.ekwateur.domain;

public interface Client {
    default double calculateEnergyUsage(double kwh, double tarif) {
        return kwh * tarif;
    }

}