package com.ekwateur.infra.mock;

import com.ekwateur.domain.ChiffreAffaire;
import com.ekwateur.domain.ClientInfo;
import com.ekwateur.domain.ConsommationEnergie;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static com.ekwateur.domain.enums.ClientType.PARICULIER;
import static com.ekwateur.domain.enums.ClientType.PRO;
import static com.ekwateur.domain.enums.EnergyType.ELECTRICITE;
import static com.ekwateur.domain.enums.EnergyType.GAZ;
import static java.util.stream.Collectors.toList;

public class MockData {

    public static final List<String> clientRefs = IntStream
            .range(0, 10)
            .mapToObj(i -> "EKW0000000" + i)
            .collect(toList());


    public static Flux<ClientInfo> getClientInfo() {
        return Flux.range(0, clientRefs.size())
                .map(i -> new ClientInfo(clientRefs.get(i), i % 2 == 0 ? PARICULIER : PRO));
    }

    public static Flux<ChiffreAffaire> getChiffreAffaire() {
        return Flux.range(0, clientRefs.size())
                .map(i -> {
                            BigDecimal chiffre;
                            if (i % 2 != 0) {
                                Random random = new Random();
                                double v = random.nextDouble(50_000, 2_000_000);
                                chiffre = BigDecimal.valueOf(v);
                            } else {
                                chiffre = BigDecimal.ZERO;
                            }
                            return new ChiffreAffaire(clientRefs.get(i), chiffre, 2023);
                        }
                );
    }


    public static Flux<ConsommationEnergie> getConsommation() {
        Flux<ConsommationEnergie> gaz = Flux.range(0, clientRefs.size())
                .map(i -> {
                            BigDecimal chiffre;
                            if (i % 2 != 0) {
                                double v = Math.random() * 5000;
                                chiffre = BigDecimal.valueOf(v);
                            } else {
                                chiffre = BigDecimal.valueOf(50);
                            }
                            return new ConsommationEnergie(clientRefs.get(i), GAZ, chiffre, YearMonth.of(2024, 1));
                        }
                );

        return Flux.range(0, clientRefs.size())
                .map(i -> {
                            BigDecimal chiffre;
                            if (i % 2 != 0) {
                                double v = Math.random() * 10000;
                                chiffre = BigDecimal.valueOf(v);
                            } else {
                                chiffre = BigDecimal.valueOf(500);
                            }
                            return new ConsommationEnergie(clientRefs.get(i), ELECTRICITE ,chiffre, YearMonth.of(2024, 1));
                        }
                ).mergeWith(gaz);
    }


}