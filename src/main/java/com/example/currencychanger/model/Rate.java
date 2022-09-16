package com.example.currencychanger.model;

public class Rate {

    public String currency;
    public String code;
    public Double mid;
    public Double ask;
    public Double bid;

    public Rate(String currency, String code, Double mid) {
        this.currency = currency;
        this.code = code;
        this.mid = mid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
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

    public Double getMid() {
        return mid;
    }

    public void setMid(Double mid) {
        this.mid = mid;
    }

    public void countMeanMid() {
        this.mid = (this.bid + this.ask) / 2;
    }
}
