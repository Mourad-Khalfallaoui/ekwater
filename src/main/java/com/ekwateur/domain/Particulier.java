package com.ekwateur.domain;


import com.ekwateur.domain.enums.Civility;

public record Particulier(String referenceClient, Civility civilite, String nom, String prenom) implements Client {

}