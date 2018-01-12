package com.cv.mailguntest.controller;

import com.cv.mailguntest.entity.Lead;
import com.cv.mailguntest.service.LeadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/parse")
public class ParserController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private LeadService leadService;

    @PostMapping()
    public ResponseEntity<Lead> postEmail(@RequestParam String from, @RequestParam String to, @RequestParam String body) {
        log.info(from);
        log.info(to);
        log.info(body);
        Lead lead = leadService.parse(from, to, body);
        log.info(lead.toString());
        return new ResponseEntity<>(lead, HttpStatus.OK);
    }

}
