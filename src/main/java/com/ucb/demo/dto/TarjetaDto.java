package com.ucb.demo.dto;

public class TarjetaDto {
    private String card_number;
    private int cvv;
    private String expiration_date;
public TarjetaDto(String card_number, int cvv, String expiration_date) {
    this.card_number = card_number;
    this.cvv = cvv;
    this.expiration_date = expiration_date;
}

public String getCard_number() {
    return card_number;
}
public int getCvv() {
    return cvv;
}
public String getExpiration_date() {
    return expiration_date;
}
public void setCard_number(String card_number) {
    this.card_number = card_number;
}
public void setCvv(int cvv) {
    this.cvv = cvv;
}
public void setExpiration_date(String expiration_date) {
    this.expiration_date = expiration_date;
}
}