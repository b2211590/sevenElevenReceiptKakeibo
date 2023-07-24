package com.example.linebot.presentation.replier;

import com.example.linebot.sevice.ReceiptResult;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class ReceiptReply implements Replier {

    public static final String MESSAGE_FORMAT = "購入日：%s\n支　出：%d 円";

    private final ReceiptResult receiptResult;

    public ReceiptReply(ReceiptResult receiptResult) {
        this.receiptResult = receiptResult;
    }

    @Override
    public Message reply() {
        String msg = String.format(MESSAGE_FORMAT, receiptResult.response().date(), receiptResult.response().total_amount());
        return new TextMessage(msg);
    }
}
