package com.ekwateur.domain;

import com.ekwateur.domain.enums.ClientType;
import com.ekwateur.domain.enums.EnergyType;

public record Tarif(EnergyType energyType, ClientType typeClient, double maxChiffreAffaire, double value) {}
