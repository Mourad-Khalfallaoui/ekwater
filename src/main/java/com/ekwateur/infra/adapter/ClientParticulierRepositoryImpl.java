package com.ekwateur.infra.adapter;

import com.ekwateur.domain.port.out.ClientParticulierRepository;
import com.ekwateur.domain.model.Client;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ClientParticulierRepositoryImpl implements ClientParticulierRepository {

    @Override
    public Mono<Client> findByReference(String clientReference) {
        return Mono.empty();
    }
}