package com.disqo.onboarding_flow_service.service.impl;


import com.disqo.onboarding_flow_service.persistance.RoadmapRepository;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.persistance.enums.RoadmapStatus;
import com.disqo.onboarding_flow_service.service.MenteeService;
import com.disqo.onboarding_flow_service.service.MentorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class RoadmapServiceImplTest {

    private RoadmapServiceImpl action;
    private RoadmapRepository roadmapRepository;
    private MenteeService menteeService;
    private MentorService mentorService;

    @BeforeEach
    public void init() {
        roadmapRepository = Mockito.mock(RoadmapRepository.class);
        menteeService = Mockito.mock(MenteeService.class);
        mentorService = Mockito.mock(MentorService.class);
        action = new RoadmapServiceImpl(roadmapRepository, menteeService, mentorService);
    }

    @Test
    public void isUpdatedStatus() {
        final Roadmap roadmap = new Roadmap();
        roadmap.setStatus(RoadmapStatus.STARTED);
        when(roadmapRepository.getById(1L)).thenReturn(roadmap);
        roadmap.setStatus(RoadmapStatus.COMPLETED);
        final Roadmap saved = roadmapRepository.save(roadmap);
        Assertions.assertNotEquals(roadmap, saved);
    }

}