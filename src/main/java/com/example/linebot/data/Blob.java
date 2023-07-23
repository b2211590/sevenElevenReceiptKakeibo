package com.example.linebot.data;

import com.linecorp.bot.client.LineBlobClient;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.InputStream;

@Repository
public class Blob {

    private LineBlobClient blob;

    public Blob(LineBlobClient blob) {
        this.blob = blob;
    }
    public Resource getImageResource(MessageEvent<ImageMessageContent> event) throws Exception {
        String msgId = event.getMessage().getId();
        MessageContentResponse contentResponse = blob.getMessageContent(msgId).get();

        try (InputStream is = contentResponse.getStream()) {
            // 画像をバイトデータとして取得
            // 画像が期限切れなどの場合には例外となるため try 文を使用した.
            LINEImageResource resource = new LINEImageResource(is.readAllBytes());
            return resource;
        }
    }
}
