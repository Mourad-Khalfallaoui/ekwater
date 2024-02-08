package com.ekwateur.domain;

import com.ekwateur.domain.enums.ClientType;

public record ClientInfo(String referenceClient, ClientType clientType) {
}