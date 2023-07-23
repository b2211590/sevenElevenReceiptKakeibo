package com.example.linebot.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.core.io.Resource;
import com.example.linebot.sevice.ReceiptResponse;
import org.springframework.stereotype.Repository;


@Repository
public class ReceiptAPI {

    @Value("${receipt.api.url}")
    private String API_URL;

    // Spring で HTTP のリクエストメッセージを作成する
    private final RestTemplate restTemplate;

    public ReceiptAPI(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ReceiptResponse extractData(Resource imageResource) {
        HttpHeaders formHeaders = new HttpHeaders();
        formHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", imageResource);

        HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<>(map, formHeaders);
        ResponseEntity<ReceiptResponse> response = restTemplate.postForEntity(API_URL, formEntity, ReceiptResponse.class);

        return response.getBody();
    }
}
