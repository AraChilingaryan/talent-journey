package com.disqo.onboarding_flow_service.util.impl;

import com.disqo.onboarding_flow_service.service.dto.RoadmapDTO;
import com.disqo.onboarding_flow_service.util.Util;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;


@Service
public class UtilImpl implements Util {

    //todo check if this method is okay
    @Override
    public String generateJiraKeyFor(RoadmapDTO roadmapDTO) {
        String name = roadmapDTO.getName();
        String key = "" + name.charAt(0) + name.charAt(name.length() - 1);
        return key;
    }
}
