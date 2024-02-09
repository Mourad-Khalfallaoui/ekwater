package com.ekwateur.infra.adapter;

import com.ekwateur.domain.port.out.ClientProRepository;
import com.ekwateur.domain.model.Client;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ClientProRepositoryImpl implements ClientProRepository {

    @Override
    public Mono<Client> findByReference(String clientReference) {
        return Mono.empty();
    }
}