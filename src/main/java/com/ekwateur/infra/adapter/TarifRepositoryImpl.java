package com.ekwateur.infra.adapter;

import com.ekwateur.application.port.out.TarifRepository;
import com.ekwateur.domain.enums.ClientType;
import com.ekwateur.domain.enums.EnergyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static com.ekwateur.domain.enums.EnergyType.ELECTRICITE;

@Slf4j
@Repository
public class TarifRepositoryImpl implements TarifRepository {

    @Override
    public Mono<BigDecimal> getTarif(EnergyType energyType, ClientType typeClient, BigDecimal chiffreAffaire) {
        // Chaque énergie est facturée au kWh.
        //       - Pour les particuliers, le prix du kWh est de 0,121 € pour l'électricité et 0,115€ pour le gaz
        //     - Pour les pro, ayant un CA supérieur à 1 000 000 €, le prix du kWh est de 0,114 € pour l'électricité et 0,111€ pour le gaz
        //   - Pour les pro, ayant un CA inférieur à 1 000 000 €, le prix du kWh est de 0,118 € pour l'électricité et 0,113€ pour le gaz

        var tarif = 0.0;

        switch (typeClient) {
            case PARICULIER -> tarif = energyType == ELECTRICITE ? 0.121 : 0.115;
            case PRO -> {
                if (chiffreAffaire.floatValue() > 1000000) {
                    tarif = energyType == ELECTRICITE ? 0.114 : 0.111;
                } else {
                    tarif = energyType == ELECTRICITE ? 0.118 : 0.113;
                }
            }
        }

        log.debug("Le tarif {} du client {} est : {}", energyType, typeClient, tarif);
        return Mono.just(BigDecimal.valueOf(tarif));
    }

}