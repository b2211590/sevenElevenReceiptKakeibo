package com.example.linebot.presentation;

import com.example.linebot.presentation.replier.Follow;
import com.example.linebot.presentation.replier.Parrot;
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

    public Callback(ReceiptSaveService receiptSaveService) {
        this.receiptSaveService = receiptSaveService;
    }

    @EventMapping
    public Message handleFollow(FollowEvent event) {
        Follow follow = new Follow(event);
        return follow.reply();
    }

    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event) {
        Parrot parrot = new Parrot(event);
        return parrot.reply();
    }

    @EventMapping
    public List<Message> handleReceipt(MessageEvent<ImageMessageContent> event) throws Exception {
        ReceiptResult receiptResult = receiptSaveService.getReceipt(event);

        return List.of(new TextMessage(receiptResult.toString()));
    }
}
