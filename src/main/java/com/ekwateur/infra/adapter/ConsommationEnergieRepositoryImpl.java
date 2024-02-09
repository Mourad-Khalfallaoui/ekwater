package com.ekwateur.infra.adapter;

import com.ekwateur.domain.port.out.ConsommationEnergieRepository;
import com.ekwateur.domain.model.ConsommationEnergie;
import com.ekwateur.domain.enums.EnergyType;
import com.ekwateur.infra.mock.MockData;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.YearMonth;

@Repository
public class ConsommationEnergieRepositoryImpl implements ConsommationEnergieRepository {

    @Override
    public Mono<BigDecimal> getConsommationEnergieMensuelle(String clientReference, EnergyType energyType, YearMonth yearMonth) {
        return MockData.getConsommation()
                .filter(c -> c.referenceClient().equals(clientReference)
                        && c.yearMonth().equals(yearMonth)
                        && c.energyType() == energyType)
                .log()
                .map(ConsommationEnergie::quantity).next();
    }
}