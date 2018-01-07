package com.cv.mailguntest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/inbox")
public class InboxController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping("post")
    public ResponseEntity<Void> postEmail(HttpServletRequest request) {
        log.info(String.valueOf(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
