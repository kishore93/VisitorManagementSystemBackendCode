package com.infy.visitor.management.service.impl;


import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.infy.visitor.management.service.VelocityService;

@Service
public class VelocityServiceImpl implements VelocityService {

    @Autowired
    VelocityEngine velocityEngine;

    public String buildTemplate(Map<String, Object> model, String templateLocation) throws Exception {

        return VelocityEngineUtils.mergeTemplateIntoString(this.velocityEngine,templateLocation, "UTF-8", model);

    }
}
