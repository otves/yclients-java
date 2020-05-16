package ru.otveska.yclients.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class SmsMessage {
    String phone;
    String text;

    SmsMessage(String phone, String text) {
        this.phone = phone;
        this.text = text;
    }

    @Override
    public String toString() {
        return "phone:" + phone + ", text:" + text;
    }
}