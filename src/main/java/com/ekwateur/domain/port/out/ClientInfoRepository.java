package com.ekwateur.domain.port.out;

import com.ekwateur.domain.model.ClientInfo;
import reactor.core.publisher.Mono;

public interface ClientInfoRepository {
    Mono<ClientInfo> findByReference(String clientReference);

}
