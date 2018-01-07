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
        lead.setForm(from);
        lead.setTo(to);

        log.info(to);
        log.info(to.split("@")[0]);
        log.info(Arrays.toString(to.split("@")[0].split("\\.")));

        String[] addressProperties = to.split("@")[0].split("\\.");
        lead.setSupplier(addressProperties[0]);
        lead.setPurpose(addressProperties[2]);
        lead.setDealerCenter(addressProperties[3]);
        lead.setForm(addressProperties[4]);

        String bodyProperties[] = body.split("\\r?\\n");
        lead.setClient(getPropertyFromLine(bodyProperties[0]));
        lead.setBrand("");
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
