package com.ekwateur.domain.service;

import com.ekwateur.domain.port.in.TarifUseCase;
import com.ekwateur.domain.port.out.ClientInfoRepository;
import com.ekwateur.domain.port.out.ConsommationEnergieRepository;
import com.ekwateur.domain.port.out.FinancialDataRepository;
import com.ekwateur.domain.port.out.TarifRepository;
import com.ekwateur.domain.model.ClientInfo;
import com.ekwateur.domain.enums.ClientType;
import com.ekwateur.domain.enums.EnergyType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.YearMonth;

@Slf4j
@Service
@AllArgsConstructor
public class TarifService implements TarifUseCase {
    private final ClientInfoRepository clientInfoRepository;
    private final TarifRepository tarifRepository;
    private final ConsommationEnergieRepository consommationEnergieRepository;
    private final FinancialDataRepository financialDataRepository;

    @Override
    public Mono<BigDecimal> calculateEnergyUsage(String clientReference, EnergyType typeDEnergie, int month, int year) {

        var tarif = getClientData(clientReference)
                .flatMap(clientInfo -> getTarif(clientReference, typeDEnergie, year, clientInfo.clientType()));

        var yearMonth = YearMonth.of(year, month);
        var consommationMensuelle = getConsommationMensuelle(clientReference, typeDEnergie, yearMonth);


        return Mono.zip(consommationMensuelle, tarif)
                .map(data -> calculateUsage(data.getT1(), data.getT2()));

    }

    private Mono<ClientInfo> getClientData(String clientReference) {
        return clientInfoRepository.findByReference(clientReference);
    }

    private Mono<BigDecimal> getTarif(String clientReference, EnergyType energyType, int year, ClientType clientType) {
        return financialDataRepository.getChiffreAffaireAnnuel(clientReference, year)
                .flatMap(chiffreAffaire -> tarifRepository.getTarif(energyType, clientType, chiffreAffaire));
    }

    private Mono<BigDecimal> getConsommationMensuelle(String clientReference, EnergyType energyType, YearMonth month) {
        return consommationEnergieRepository.getConsommationEnergieMensuelle(clientReference, energyType, month);
    }

    private BigDecimal calculateUsage(BigDecimal consommationMensuelle, BigDecimal tarif) {
        return consommationMensuelle.multiply(tarif);
    }
}