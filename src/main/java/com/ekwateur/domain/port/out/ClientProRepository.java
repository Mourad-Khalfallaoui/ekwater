package com.ekwateur.domain.port.out;

import com.ekwateur.domain.model.Client;
import reactor.core.publisher.Mono;

public interface ClientProRepository {
    Mono<Client> findByReference(String clientReference);
}
