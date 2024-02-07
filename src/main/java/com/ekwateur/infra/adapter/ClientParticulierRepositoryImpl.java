package com.ekwateur.infra.adapter;

import com.ekwateur.application.port.out.ClientParticulierRepository;
import com.ekwateur.domain.Client;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ClientParticulierRepositoryImpl implements ClientParticulierRepository {

    @Override
    public Mono<Client> findByReference(String clientReference) {
        return Mono.empty();
    }
}