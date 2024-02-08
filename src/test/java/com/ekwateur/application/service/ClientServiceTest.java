package com.ekwateur.application.service;

import com.ekwateur.domain.ClientInfo;
import com.ekwateur.domain.enums.ClientType;
import com.ekwateur.domain.enums.EnergyType;
import com.ekwateur.infra.adapter.ClientInfoRepositoryImpl;
import com.ekwateur.infra.adapter.ConsommationEnergieRepositoryImpl;
import com.ekwateur.infra.adapter.FinancialDataRepositoryImpl;
import com.ekwateur.infra.adapter.TarifRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.YearMonth;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientInfoRepositoryImpl clientInfoRepository;

    @Mock
    private TarifRepositoryImpl tarifRepository;

    @Mock
    private ConsommationEnergieRepositoryImpl consommationEnergieRepository;

    @Mock
    FinancialDataRepositoryImpl financialDataRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void testCalculateEnergyUsage() {
        String clientRef = "EKW00000005";
        EnergyType energyType = EnergyType.ELECTRICITE;
        ClientType clientType = ClientType.PRO;
        BigDecimal chiffreAffaire = new BigDecimal("10000");
        BigDecimal tarif = new BigDecimal("0.111");
        BigDecimal consommationMensuelle = new BigDecimal("500");
        int year = 2024;
        int month = 1;
        YearMonth yearMonth = YearMonth.of(year, month);

        ClientInfo clientInfo = new ClientInfo(clientRef, clientType);
        when(clientInfoRepository.findByReference(clientRef)).thenReturn(Mono.just(clientInfo));
        when(financialDataRepository.getChiffreAffaireAnnuel(clientRef, year)).thenReturn(Mono.just(chiffreAffaire));
        when(tarifRepository.getTarif(energyType, clientType, chiffreAffaire)).thenReturn(Mono.just(tarif));
        when(consommationEnergieRepository.getConsommationEnergieMensuelle(clientRef, energyType, yearMonth)).thenReturn(Mono.just(consommationMensuelle));

        Mono<BigDecimal> result = clientService.calculateEnergyUsage(clientRef, energyType, month, year);

        StepVerifier.create(result)
                .expectNextMatches(usage -> usage.equals(tarif.multiply(consommationMensuelle)))
                .verifyComplete();
    }
}
