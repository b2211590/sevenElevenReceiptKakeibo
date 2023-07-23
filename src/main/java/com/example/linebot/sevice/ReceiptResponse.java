package com.example.linebot.sevice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ReceiptResponse(String date, int total_amount) {
}
