package com.example.linebot.presentation.replier;

import com.example.linebot.sevice.Kakeibo;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import java.util.ArrayList;
import java.util.List;

public class KakeiboReply implements Replier{

    public static final String MESSAGE_FORMAT = "%s %d 円\n";

    private final Kakeibo kakeibo;

    public KakeiboReply(Kakeibo kakeibo) {
        this.kakeibo = kakeibo;
    }

    public String mapDateAndAmount() {
        StringBuilder msgKakeibo = new StringBuilder();
        for (int i = 0; i < kakeibo.allTotalAmount().size(); i++) {
            msgKakeibo.append(String.format(MESSAGE_FORMAT, kakeibo.allDate().get(i), kakeibo.allTotalAmount().get(i)));
        }
        return msgKakeibo.toString();
    }

    @Override
    public Message reply() {
        String msg = mapDateAndAmount() + "合計金額：" + kakeibo.sum() + " 円";
        return new TextMessage(msg);
    }
}
