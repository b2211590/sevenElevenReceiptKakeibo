package com.example.linebot.sevice;

import com.example.linebot.data.ReceiptLog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KakeiboDisplayService {

    private ReceiptLog receiptLog;

    public KakeiboDisplayService(ReceiptLog receiptLog) {
        this.receiptLog = receiptLog;
    }

    public Kakeibo displayKakeibo() {
        List<ReceiptResponse> receiptResponses = receiptLog.selectAll();

        List<String> allDate = new ArrayList<>();
        for(ReceiptResponse response : receiptResponses) {
            allDate.add(response.date());
        }

        List<Integer> allTotalAmount = new ArrayList<>();
        int sum = 0;
        for(ReceiptResponse response : receiptResponses) {
            allTotalAmount.add(response.total_amount());
            sum += response.total_amount();
        }

        return new Kakeibo(allDate, allTotalAmount, sum);
    }
}
