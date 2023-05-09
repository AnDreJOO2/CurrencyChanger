package com.example.currencychanger.model;

import java.math.BigDecimal;

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
}
