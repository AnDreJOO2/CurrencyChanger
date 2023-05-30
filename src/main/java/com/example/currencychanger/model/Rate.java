package com.example.currencychanger.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Rate {

    private String currency;
    private String code;
    private BigDecimal mid;
    private BigDecimal ask;
    private BigDecimal bid;

    public Rate(String currency, String code, BigDecimal mid) {
        this.currency = currency;
        this.code = code;
        this.mid = mid;
    }

    public Rate(String currency, String code, BigDecimal ask, BigDecimal bid) {
        this.currency = currency;
        this.code = code;
        this.ask = ask;
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getMid() {
        return mid;
    }

    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }

    public void countMeanMid() {
        this.mid = this.bid.add(this.ask).divide(new BigDecimal(2));
    }

    @Override
    public String toString() {
        return "Rate{" +
                "currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", mid=" + mid +
                ", ask=" + ask +
                ", bid=" + bid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rate rate)) return false;
        return Objects.equals(currency, rate.currency) && Objects.equals(code, rate.code) && Objects.equals(mid, rate.mid)
                && Objects.equals(ask, rate.ask) && Objects.equals(bid, rate.bid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, code, mid, ask, bid);
    }
}
