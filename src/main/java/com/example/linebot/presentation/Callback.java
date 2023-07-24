package com.example.linebot.presentation;

import com.example.linebot.presentation.replier.*;
import com.example.linebot.sevice.Kakeibo;
import com.example.linebot.sevice.KakeiboDisplayService;
import com.example.linebot.sevice.ReceiptResult;
import com.example.linebot.sevice.ReceiptSaveService;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@LineMessageHandler
public class Callback {

    private static final Logger log = LoggerFactory.getLogger(Callback.class);

    private ReceiptSaveService receiptSaveService;
    private KakeiboDisplayService kakeiboDisplayService;

    public Callback(ReceiptSaveService receiptSaveService, KakeiboDisplayService kakeiboDisplayService) {
        this.receiptSaveService = receiptSaveService;
        this.kakeiboDisplayService = kakeiboDisplayService;
    }

    @EventMapping
    public Message handleFollow(FollowEvent event) {
        Follow follow = new Follow(event);
        return follow.reply();
    }

    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event) {
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        switch (text) {
            case "確認" -> {
                Kakeibo kakeibo = kakeiboDisplayService.displayKakeibo();
                return new KakeiboReply(kakeibo).reply();
            }
            default -> {
                Parrot parrot = new Parrot(event);
                return parrot.reply();
            }
        }
    }

    @EventMapping
    public List<Message> handleReceipt(MessageEvent<ImageMessageContent> event) throws Exception {
        ReceiptResult receiptResult = receiptSaveService.getReceipt(event);

        return List.of(new ImageSizeReply(receiptResult).reply(),
                       new ReceiptReply(receiptResult).reply());
    }
}
