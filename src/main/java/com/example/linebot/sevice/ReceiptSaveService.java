package com.example.linebot.sevice;

import com.example.linebot.data.Blob;
import com.example.linebot.data.ReceiptAPI;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ReceiptSaveService {

    private Blob blob;
    private ReceiptAPI receiptAPI;

    public ReceiptSaveService(Blob blob, ReceiptAPI receiptAPI) {
        this.blob = blob;
        this.receiptAPI = receiptAPI;
    }

    public ReceiptResult getReceipt(MessageEvent<ImageMessageContent> event) throws Exception {
        //画像
        Resource imageResource = blob.getImageResource(event);
        //保存
        ReceiptResponse receiptResponse = receiptAPI.extractData(imageResource);
        //処理の結果を返す
        ReceiptResult receiptResult = new ReceiptResult(imageResource.contentLength(), receiptResponse);
        return receiptResult;
    }
}
