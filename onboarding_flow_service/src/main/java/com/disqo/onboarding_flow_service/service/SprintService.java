package com.disqo.onboarding_flow_service.service;

import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.JiraSprintDto;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.persistance.entity.Sprint;
import com.disqo.onboarding_flow_service.service.dto.SprintDto;

import java.util.List;

public interface SprintService {

    List<Sprint> findALl();

    Sprint findById(Long id);

    Sprint create(SprintDto sprintDto);

    Sprint update(Long id, SprintDto sprintDto);

    boolean deleteById(Long id);

    boolean isTodayTheEmailDate(Sprint sprint);

    void sendSprintCheckupEmail();

}
