package com.ekwateur.infra.adapter;

import com.ekwateur.application.port.out.ClientInfoRepository;
import com.ekwateur.domain.ClientInfo;
import com.ekwateur.infra.mock.MockData;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Repository
public class ClientInfoRepositoryImpl implements ClientInfoRepository {
    @Override
    public Mono<ClientInfo> findByReference(String clientReference) {

        return MockData.getClientInfo()
                .filter(cl -> Objects.equals(cl.referenceClient(), clientReference))
                .log()
                .next();
    }
}
