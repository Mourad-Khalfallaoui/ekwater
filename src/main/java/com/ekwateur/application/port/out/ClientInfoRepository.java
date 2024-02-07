package com.ekwateur.application.port.out;

import com.ekwateur.domain.ClientInfo;
import reactor.core.publisher.Mono;

public interface ClientInfoRepository {
    Mono<ClientInfo> findByReference(String clientReference);

}
