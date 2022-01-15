package com.disqo.onboarding_flow_service.service.impl;

import com.disqo.onboarding_flow_service.client.mailclient.MailGenerator;
import com.disqo.onboarding_flow_service.client.mailclient.MailSenderClient;
import com.disqo.onboarding_flow_service.exception.SprintNotFoundException;
import com.disqo.onboarding_flow_service.persistance.SprintRepository;
import com.disqo.onboarding_flow_service.persistance.entity.Sprint;
import com.disqo.onboarding_flow_service.service.RoadmapService;
import com.disqo.onboarding_flow_service.service.SprintService;
import com.disqo.onboarding_flow_service.service.dto.SprintDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SprintServiceImpl implements SprintService {

    private final RoadmapService roadmapService;
    private final SprintRepository sprintRepository;
    private final MailSenderClient mailSenderClient;
    private final MailGenerator mailGenerator;

    @Override
    public List<Sprint> findALl() {
        return null;
    }

    @Override
    public Sprint findById(Long id) {
        return null;
    }

    @Override
    public Sprint create(SprintDto sprintDto) {
        return sprintRepository.save(buildSprintFrom(sprintDto));
    }

    @Override
    public Sprint update(Long id, SprintDto sprintDto) {
        final Sprint sprint = this.sprintRepository.findById(id)
                .orElseThrow(() -> new SprintNotFoundException("Not found sprint by id ", id));
        sprint.setName(sprintDto.getName());
        sprint.setStartDate(sprintDto.getStartDate());
        sprint.setEndDate(sprintDto.getEndDate());
        return this.sprintRepository.save(sprint);
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    private Sprint buildSprintFrom(final SprintDto sprintDto) {
        final Sprint sprint = new Sprint();
        sprint.setStartDate(sprintDto.getStartDate());
        sprint.setEndDate(sprintDto.getEndDate());
        sprint.setRoadmap(this.roadmapService.findById(sprintDto.getRoadMapId()));
        sprint.setName(sprintDto.getName());
        return sprint;
    }

    @Override
    public boolean isTodayTheEmailDate(Sprint sprint) {
        return sprint.getEndDate().equals(LocalDate.now());
    }

    @Scheduled(cron = "0 0 14 * * ?", zone = "GMT+4")
    @Override
    public void sendSprintCheckupEmail() {
        final List<Sprint> allSprints = this.findALl();
        for (Sprint sprint : allSprints) {
            if (this.isTodayTheEmailDate(sprint)) {
                mailSenderClient.sendEmail(mailGenerator.sprintCheckupMailGenerator(sprint.getRoadmap().getMentee()));
            }
        }
    }
}
