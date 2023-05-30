package com.example.currencychanger.service;

import com.example.currencychanger.model.CurrencyTable;
import com.example.currencychanger.model.Rate;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NbpApiService {

    private static final String API_URL_TABLE_A = "http://api.nbp.pl/api/exchangerates/tables/a/?format=json";
    private static final String API_URL_TABLE_B = "http://api.nbp.pl/api/exchangerates/tables/b/?format=json";
    private static final String API_URL_TABLE_C = "http://api.nbp.pl/api/exchangerates/tables/c/?format=json";

    private static void countMidValuesIfNull(Set<Rate> allRates) {
        allRates.forEach(rate -> {
            if (rate.getMid() == null) {
                rate.countMeanMid();
            }
        });
    }

    private static void formatCurrencyNames(Set<Rate> allRates) {
        allRates.forEach(rate -> {
            String toLowerCase = rate.getCurrency().toLowerCase();
            rate.setCurrency(toLowerCase.substring(0, 1).toUpperCase() + toLowerCase.substring(1));
        });
    }

    public Set<Rate> getAllRates() {

        try {
            URL url_A = new URL(API_URL_TABLE_A);
            URL url_B = new URL(API_URL_TABLE_B);
            URL url_C = new URL(API_URL_TABLE_C);

            InputStreamReader isr_A = new InputStreamReader(url_A.openStream());
            InputStreamReader isr_B = new InputStreamReader(url_B.openStream());
            InputStreamReader isr_C = new InputStreamReader(url_C.openStream());

            Gson gson = new Gson();

            CurrencyTable[] table_A = gson.fromJson(isr_A, CurrencyTable[].class);
            CurrencyTable[] table_B = gson.fromJson(isr_B, CurrencyTable[].class);
            CurrencyTable[] table_C = gson.fromJson(isr_C, CurrencyTable[].class);

            List<CurrencyTable> currencyTables_A = Arrays.asList(table_A);
            List<CurrencyTable> currencyTables_B = Arrays.asList(table_B);
            List<CurrencyTable> currencyTables_C = Arrays.asList(table_C);

            Set<Rate> allRates = new HashSet<>();

            currencyTables_A.stream().map(r -> r.getRates()).forEach(allRates::addAll);
            currencyTables_B.stream().map(r -> r.getRates()).forEach(allRates::addAll);
            currencyTables_C.stream().map(r -> r.getRates()).forEach(allRates::addAll);

            allRates.add(new Rate("Polski Złoty", "PLN", new BigDecimal(1)));

            formatCurrencyNames(allRates);
            countMidValuesIfNull(allRates);

            return allRates;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
