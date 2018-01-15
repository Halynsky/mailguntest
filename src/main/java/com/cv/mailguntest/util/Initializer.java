package com.cv.mailguntest.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Initializer {

    private static final  Logger log = LoggerFactory.getLogger(Initializer.class);

    private static final String API_KEY = "key-10ec6ceb910bd7393c7d727824fe9fd9";
    private static final String DOMAIN = "sandbox0b2887a0fcec4fe7ab67297c0f04ebdf.mailgun.org";
    private static final String ENDPOINT = "https://mailguntestcv.herokuapp.com/api/inbox/post";


    @PostConstruct
    public void init () throws Exception {
        log.info("Initializing...");
        createRoute();
    }

    public static JsonNode createRoute() throws UnirestException {

        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/routes")
                .basicAuth("api", API_KEY)
                .field("priority", "0")
                .field("description", "sample route")
                .field("expression", "match_recipient('.*@" + DOMAIN + "')")
                .field("action", "forward('" + ENDPOINT + "')")
                .field("action", "stop()")
                .asJson();

        log.info(String.valueOf(request.getBody()));

        return request.getBody();
    }


}
