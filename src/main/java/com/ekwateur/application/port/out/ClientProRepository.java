package com.ekwateur.application.port.out;

import com.ekwateur.domain.Client;
import reactor.core.publisher.Mono;

public interface ClientProRepository {
    Mono<Client> findByReference(String clientReference);
}
