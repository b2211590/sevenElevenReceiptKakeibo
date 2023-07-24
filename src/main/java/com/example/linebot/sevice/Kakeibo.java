package com.example.linebot.sevice;

import java.util.List;

public record Kakeibo(List<String> allDate, List<Integer> allTotalAmount, int sum) {
}
