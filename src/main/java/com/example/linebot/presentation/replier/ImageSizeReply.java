package com.example.linebot.presentation.replier;

import com.example.linebot.sevice.ReceiptResult;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class ImageSizeReply implements Replier {

    public static final String MESSAGE_FORMAT = "画像サイズ：%d";

    private final ReceiptResult receiptResult;

    public ImageSizeReply(ReceiptResult receiptResult) {
        this.receiptResult = receiptResult;
    }

    @Override
    public Message reply() {
        String msg = String.format(MESSAGE_FORMAT, receiptResult.imageSize());
        return new TextMessage(msg);
    }
}
