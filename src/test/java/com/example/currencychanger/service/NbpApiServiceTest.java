package com.example.currencychanger.service;

import com.example.currencychanger.model.Rate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class NbpApiServiceTest {

    private NbpApiService underTest;

    @BeforeEach
    void setUp() {
        underTest = new NbpApiService();
    }

    @ParameterizedTest(name = "Params given=`{0}`, expected=`{1}`")
    @MethodSource("shouldNotCountMidValuesIfMidValueIsNullParams")
    void shouldNotCountMidValuesIfMidValueIsNull(Set<Rate> given, Set<Rate> expected) {
        //when
        try {
            accessPrivateCountMidValuesIfNullMethod().invoke(underTest, given);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        Set<BigDecimal> expectedRates = expected.stream().map(rate -> rate.getMid()).collect(Collectors.toSet());

        //then
        assertThat(given)
                .isNotNull()
                .containsExactlyInAnyOrderElementsOf(expected)
                .hasSameSizeAs(expected)
                .extracting("mid")
                .containsExactlyInAnyOrderElementsOf(expectedRates);
    }

    @ParameterizedTest(name = "Params given=`{0}`, expected=`{1}`")
    @MethodSource("shouldCountMidValuesIfMidValueIsNullParams")
    void shouldCountMidValuesIfMidValueIsNull(Set<Rate> given, Set<Rate> expected) {
        //when
        try {
            accessPrivateCountMidValuesIfNullMethod().invoke(underTest, given);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Set<BigDecimal> expectedRates = expected.stream().map(rate -> rate.getMid()).collect(Collectors.toSet());
        //then
        assertThat(given)
                .isNotNull()
                .hasSameSizeAs(expected)
                .extracting("mid").containsExactlyInAnyOrderElementsOf(expectedRates);
    }

    @ParameterizedTest(name = "Params given=`{0}`, expected=`{1}`")
    @MethodSource("shouldFormatCurrencyNamesParams")
    void shouldFormatCurrencyNames(Set<Rate> given, Set<Rate> expected) {
        //when
        try {
            accessPrivateFormatCurrencyNamesMethod().invoke(underTest, given);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        //then
        assertThat(given)
                .isNotNull()
                .containsExactlyInAnyOrderElementsOf(expected)
                .hasSameSizeAs(expected);
        for (Rate rate : given) {
            assertThat(rate.getCurrency()).startsWith(rate.getCurrency().substring(0, 1).toUpperCase());
            assertThat(rate.getCurrency().substring(1)).startsWith(rate.getCurrency().substring(1).toLowerCase());
        }
    }

    @Test
    void shouldGetAllRates() {
        //when
        Set<Rate> allRates = underTest.getAllRates();
        //then
        assertThat(allRates)
                .isNotNull()
                .isNotEmpty()
                .isInstanceOf(Set.class)
                .hasOnlyElementsOfType(Rate.class);
    }

    static Stream<Arguments> shouldNotCountMidValuesIfMidValueIsNullParams() {
        return Stream.of(
                arguments(
                        Set.of(
                                new Rate("dolar australijski", "USD", new BigDecimal("4.2242")),
                                new Rate("dolar Hongkongu", "HKD", new BigDecimal("0.5394")),
                                new Rate("dolar kanadyjski", "CAD", new BigDecimal("3.1096")),
                                new Rate("dolar nowozelandzki", "NZD", new BigDecimal("2.5560")),
                                new Rate("dolar singapurski", "SGD", new BigDecimal("3.1219"))
                        ),
                        Set.of(
                                new Rate("dolar australijski", "USD", new BigDecimal("4.2242")),
                                new Rate("dolar Hongkongu", "HKD", new BigDecimal("0.5394")),
                                new Rate("dolar kanadyjski", "CAD", new BigDecimal("3.1096")),
                                new Rate("dolar nowozelandzki", "NZD", new BigDecimal("2.5560")),
                                new Rate("dolar singapurski", "SGD", new BigDecimal("3.1219"))
                        )
                )
        );
    }

    static Stream<Arguments> shouldCountMidValuesIfMidValueIsNullParams() {
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

    static Stream<Arguments> shouldFormatCurrencyNamesParams() {
        return Stream.of(
                arguments(
                        Set.of(
                                new Rate("dolar australijski", "USD", new BigDecimal("4.2242")),
                                new Rate("dolar Hongkongu", "HKD", new BigDecimal("0.5394")),
                                new Rate("Dolar Kanadyjski", "CAD", new BigDecimal("3.1096")),
                                new Rate("doLar nowozeLandzki", "NZD", new BigDecimal("2.5560")),
                                new Rate("dolar singapurski", "SGD", new BigDecimal("3.1219"))
                        ),
                        Set.of(
                                new Rate("Dolar australijski", "USD", new BigDecimal("4.2242")),
                                new Rate("Dolar hongkongu", "HKD", new BigDecimal("0.5394")),
                                new Rate("Dolar kanadyjski", "CAD", new BigDecimal("3.1096")),
                                new Rate("Dolar nowozelandzki", "NZD", new BigDecimal("2.5560")),
                                new Rate("Dolar singapurski", "SGD", new BigDecimal("3.1219"))
                        )
                )
        );
    }

    Method accessPrivateCountMidValuesIfNullMethod() throws NoSuchMethodException {
        Method method = underTest.getClass().getDeclaredMethod("countMidValuesIfNull", Set.class);
        method.setAccessible(true);
        return method;
    }

    Method accessPrivateFormatCurrencyNamesMethod() throws NoSuchMethodException {
        Method method = underTest.getClass().getDeclaredMethod("formatCurrencyNames", Set.class);
        method.setAccessible(true);
        return method;
    }
}
