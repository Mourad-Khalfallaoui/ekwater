package com.ekwateur.domain.port.out;

import com.ekwateur.domain.model.Client;
import reactor.core.publisher.Mono;

public interface ClientParticulierRepository {
    Mono<Client> findByReference(String clientReference);
}
