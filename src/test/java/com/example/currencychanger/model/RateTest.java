package com.example.currencychanger.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RateTest {


    @ParameterizedTest(name = "Params given=`{0}`, expected=`{1}`")
    @MethodSource("shouldCountMidValuesParams")
    void shouldCountMidValuesIfMidValueIsNull(Set<Rate> given, Set<Rate> expected) {

        //when
        given.forEach(rate -> rate.countMeanMid());
        //then
        assertThat(given)
                .isNotNull()
                .hasSameSizeAs(expected)
                .extracting("mid").isNotNull();
    }

    static Stream<Arguments> shouldCountMidValuesParams() {
        return Stream.of(
                arguments(
                        Set.of(
                                new Rate("frank szwajcarski", "CHF", new BigDecimal("4.7169"), new BigDecimal("4.6235")),
                                new Rate("funt szterling", "GBP", new BigDecimal("5.2626"), new BigDecimal("5.1584")),
                                new Rate("korona czeska", "CZK", new BigDecimal("0.1927"), new BigDecimal("0.1889")),
                                new Rate("korona duńska", "DKK", new BigDecimal("0.6128"), new BigDecimal("0.6006")),
                                new Rate("korona norweska", "NOK", new BigDecimal("0.3831"), new BigDecimal("0.3755"))
                        ),
                        Set.of(
                                new Rate("frank szwajcarski", "CHF", new BigDecimal("4.7169").add(new BigDecimal("4.6235")).divide(new BigDecimal(2))),
                                new Rate("funt szterling", "GBP", new BigDecimal("5.2626").add(new BigDecimal("5.1584")).divide(new BigDecimal(2))),
                                new Rate("korona czeska", "CZK", new BigDecimal("0.1927").add(new BigDecimal("0.1889")).divide(new BigDecimal(2))),
                                new Rate("korona duńska", "DKK", new BigDecimal("0.6128").add(new BigDecimal("0.6006")).divide(new BigDecimal(2))),
                                new Rate("korona norweska", "NOK", new BigDecimal("0.3831").add(new BigDecimal("0.3755")).divide(new BigDecimal(2)))
                        )
                )
        );
    }

}
