package com.ekwateur.domain.model;

import com.ekwateur.domain.enums.ClientType;

public record ClientInfo(String referenceClient, ClientType clientType) {
}