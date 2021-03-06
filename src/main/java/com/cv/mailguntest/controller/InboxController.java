package com.cv.mailguntest.controller;

import com.cv.mailguntest.entity.Lead;
import com.cv.mailguntest.service.LeadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
@RequestMapping("api/inbox")
public class InboxController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired private LeadService leadService;

    @PostMapping("post")
    public ResponseEntity<Void> postEmail(HttpServletRequest request) {
//        log.info("========================================================");
//        log.info(String.valueOf(request));
//        request.getParameterMap().forEach((k,v)->log.info(k + " => " + Arrays.toString(v)));
        log.info("========================================================");
        String from = request.getParameter("From");
        log.info(from);
        String to = request.getParameter("To");
        log.info(to);
        String bodyPlain = request.getParameter("body-plain");
        log.info(bodyPlain);
        log.info("========================================================");

        Lead lead = leadService.parse(from, to, bodyPlain);

        log.info(lead.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
