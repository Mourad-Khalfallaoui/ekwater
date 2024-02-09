package com.ekwateur.infra.adapter;

import com.ekwateur.domain.port.out.FinancialDataRepository;
import com.ekwateur.domain.model.ChiffreAffaire;
import com.ekwateur.infra.mock.MockData;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Repository
public class FinancialDataRepositoryImpl implements FinancialDataRepository {


    @Override
    public Mono<BigDecimal> getChiffreAffaireAnnuel(String clientReference, int year) {
        return MockData.getChiffreAffaire()
                .filter(c -> c.clientReference().equals(clientReference) && c.year() == year - 1)
                .log()
                .map(ChiffreAffaire::chiffreAffaire).next();
    }
}
