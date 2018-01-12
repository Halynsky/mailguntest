package com.cv.mailguntest.service;

import com.cv.mailguntest.entity.Lead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LeadService {

    private final Logger log = LoggerFactory.getLogger(LeadService.class);

    public Lead parse (String from, String to, String body) {
        Lead lead = new Lead();
        lead.setFrom(from);
        lead.setTo(to);
        lead = parseEmailTarget(lead, to);
        lead = parseEmailBody(lead, body);
        return lead;
    }

    public Lead parseEmailTarget(Lead lead, String address) {
        String[] addressProperties = address.split("@")[0].split("\\.");
        lead.setBrand(addressProperties[0]);
        lead.setPurpose(addressProperties[1]);
        lead.setDealerCenter(addressProperties[2]);
        lead.setForm(addressProperties[3]);
        return lead;
    }

    public Lead parseEmailBody(Lead lead, String body) {
        String bodyProperties[] = body.split("\\r?\\n");
        lead.setClient(getPropertyFromLine(bodyProperties[0]));
        lead.setModel(getPropertyFromLine(bodyProperties[1]));
        lead.setEmail(getPropertyFromLine(bodyProperties[2]));
        lead.setPhone(getPropertyFromLine(bodyProperties[3]));
        return lead;
    }

    private String getPropertyFromLine(String line) {
        String[] tmp = line.split(":");
        String property = tmp[1].trim();
        return property;
    }

}
