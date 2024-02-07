package com.ekwateur.infra.adapter;

import com.ekwateur.application.port.out.ClientInfoRepository;
import com.ekwateur.domain.ClientInfo;
import reactor.core.publisher.Mono;

public class ClientInfoRepositoryImpl implements ClientInfoRepository {
    @Override
    public Mono<ClientInfo> findByReference(String clientReference) {
        return null;
    }
}
