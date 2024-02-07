package com.ekwateur.application.service;

import com.ekwateur.application.port.in.ClientUseCase;
import com.ekwateur.application.port.out.*;
import com.ekwateur.domain.ClientInfo;
import com.ekwateur.domain.enums.ClientType;
import com.ekwateur.domain.enums.EnergyType;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.YearMonth;

public class ClientService implements ClientUseCase {
    private final ClientInfoRepository clientInfoRepository;
    private final TarifRepository tarifRepository;
    private final ConsommationEnergieRepository consommationEnergieRepository;
    private final FinancialDataRepository financialDataRepository;

    public ClientService(ClientInfoRepository clientInfoRepository,
                         TarifRepository tarifRepository,
                         ConsommationEnergieRepository consommationEnergieRepository,
                         FinancialDataRepository financialDataRepository) {
        this.clientInfoRepository = clientInfoRepository;
        this.tarifRepository = tarifRepository;
        this.consommationEnergieRepository = consommationEnergieRepository;
        this.financialDataRepository = financialDataRepository;
    }

    @Override
    public Mono<BigDecimal> calculateEnergyUsage(String clientReference, EnergyType typeDEnergie, YearMonth month, int year) {

        Mono<BigDecimal> tarif = getClientData(clientReference)
                .flatMap(clientInfo -> getTarif(clientReference, typeDEnergie, year, clientInfo.clientType()));

        Mono<BigDecimal> consommationMensuelle = getConsommationMensuelle(clientReference, typeDEnergie, month);

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