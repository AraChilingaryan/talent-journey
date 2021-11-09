package com.disqo.interview_flow_service.service.serviceImpl;

import com.disqo.interview_flow_service.client.MailClient;
import com.disqo.interview_flow_service.client.dto.MailDTO;
import com.disqo.interview_flow_service.converter.InterviewConverter;
import com.disqo.interview_flow_service.excaption.TalentNotFoundException;
import com.disqo.interview_flow_service.persistance.entity.interview.Interview;
import com.disqo.interview_flow_service.persistance.entity.talent.Talent;
import com.disqo.interview_flow_service.persistance.enums.EventType;
import com.disqo.interview_flow_service.persistance.enums.InterviewStatus;
import com.disqo.interview_flow_service.persistance.enums.InterviewType;
import com.disqo.interview_flow_service.persistance.repositories.InterviewRepository;
import com.disqo.interview_flow_service.service.InterviewService;
import com.disqo.interview_flow_service.service.TalentService;
import com.disqo.interview_flow_service.service.dto.InterviewEventDTO;
import com.disqo.interview_flow_service.service.dto.InterviewRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@Slf4j
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final InterviewConverter interviewConverter;
    private final TalentService talentService;
    private final MailClient mailClient;


    @Autowired
    public InterviewServiceImpl(InterviewRepository interviewRepository, InterviewConverter interviewConverter, TalentService talentService, MailClient mailClient) {
        this.interviewRepository = interviewRepository;
        this.interviewConverter = interviewConverter;
        this.talentService = talentService;
        this.mailClient = mailClient;
    }

    @Override
    public String preparationInterview(InterviewRequestDTO interviewRequestDTO) {

        log.info("Started search prepared interview ");
        Interview interview = interviewRepository.findAllByTalent_IdAndInterviewStatus(interviewRequestDTO.getTalentDTO().getId(), InterviewStatus.PREPARED);
        if (interview == null) {
            log.info("Creating preparation Interview");

            interview = interviewConverter.convertToEntity(interviewRequestDTO);
            log.info("Search interview count in Waiting_stage");
            Interview interviewCount = interviewRepository.findAllByTalent_IdAndInterviewStatus(interviewRequestDTO.getTalentDTO().getId(), InterviewStatus.WAITING_STAGE);

            log.info("chose interview type");
            InterviewType interviewType = interviewCount == null ? InterviewType.HR : InterviewType.TECHNICAL;

            interview.setTalent(talentService.findById(interviewRequestDTO.getTalentDTO().getId()));
            interview.setInterviewType(interviewType);
            interview.setInterviewStatus(InterviewStatus.PREPARED);
            interviewRepository.save(interview);
            log.info("save  preparation Interview");

            String subject = "Interview invitation with Talent_Journey company";
            String text = "We want to interview you, please choose the time that are convenient for you";
            log.info("send email to Talent");
            MailDTO mailDTO = new MailDTO(interviewRequestDTO.getTalentDTO().getEmail(), subject, text, interview.getUrl());
            mailClient.sendEmail(mailDTO);
            log.info("finish sending email to Talent");
        } else {

            String subject = "Interview invitation with Talent_Journey company";
            String text = "We have made new time calendar, Please choose the time from the new calendar that are convenient for you";
            log.info("send email to Talent");
            MailDTO mailDTO = new MailDTO(interview.getTalent().getEmail(), subject, text, interview.getUrl());
            mailClient.sendEmail(mailDTO);
            log.info("finish sending email to Talent");

            interview.setInterviewStatus(InterviewStatus.PREPARED);
            interviewRepository.save(interview);
            log.info("save  preparation Interview");
        }
        return "Preparation Interview successfully created";
    }

    @Override
    public String addEvent(InterviewEventDTO eventDTO) {
        log.info("Started search talent Email ");
        Talent talent = talentService.findTalentByEmail(eventDTO.getTalentEmail());

        log.info("Started search talent Preparation interview  ");
        Interview interview = talent.getInterviews().stream()
                .filter(s -> s.getInterviewStatus() == InterviewStatus.PREPARED)
                .findFirst().orElseThrow(() -> new RuntimeException("Not found prepared interview for this talent"));

        if (eventDTO.getEventType() == EventType.STARTED) {
            interview.setStartDate(eventDTO.getStartDate());
            interview.setEndDate(eventDTO.getEndDate());
            interview.setInterviewStatus(InterviewStatus.IN_PROGRESS);
            log.info("doing sets ");
        } else {

            String subject = "Interview invitation with Talent_Journey company";
            String text = "Dear %s, \n The Talent hasn't selected any time \n Sincerely Interview Flow";
            log.info("Started send email Users ");
            interview.getUsers().stream()
                    .forEach(a -> {
                        MailDTO mailDTO = new MailDTO(a.getEmail(), subject, String.format(text, a.getFirstName()), interview.getUrl());
                        mailClient.sendEmail(mailDTO);
                    });
            log.info("finished send email Users ");
            interview.setInterviewStatus(InterviewStatus.PREPARED);
        }
        interviewRepository.save(interview);
        log.info("save  addEvent");

        return "Interview date successfully added";
    }


    @Override
    public List<Interview> findALl() {
        return null;
    }

    @Override
    public List<Interview> findTalentAllInterviews(Long id) {
        log.info("started search talent interviews");
        if (talentService.findById(id) == null) {
            log.warn("Exception occurred during find Talent  => field: {}, value {}", "id", id);
            throw new TalentNotFoundException("No talent found by this id", id);
        }
        return interviewRepository.findAllByTalent_Id(id);

    }

}
