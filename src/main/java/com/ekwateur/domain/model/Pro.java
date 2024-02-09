package com.ekwateur.domain.model;

public record Pro(String referenceClient, String numeroSiret, String raisonSociale) implements Client {
}