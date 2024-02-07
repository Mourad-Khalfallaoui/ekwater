package com.ekwateur.application.port.out;

import com.ekwateur.domain.Client;
import reactor.core.publisher.Mono;

public interface ClientParticulierRepository {
    Mono<Client> findByReference(String clientReference);
}
