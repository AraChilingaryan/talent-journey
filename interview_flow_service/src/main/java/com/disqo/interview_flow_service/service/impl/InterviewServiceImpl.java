package com.disqo.interview_flow_service.service.impl;

import com.disqo.interview_flow_service.client.MailSenderClient;
import com.disqo.interview_flow_service.client.MailTextGenerator;
import com.disqo.interview_flow_service.client.dto.MailDTO;
import com.disqo.interview_flow_service.converter.InterviewConverter;
import com.disqo.interview_flow_service.excaption.TalentNotFoundException;
import com.disqo.interview_flow_service.persistance.entity.interview.Interview;
import com.disqo.interview_flow_service.persistance.entity.Talent;
import com.disqo.interview_flow_service.persistance.enums.EmailTextType;
import com.disqo.interview_flow_service.persistance.enums.EventType;
import com.disqo.interview_flow_service.persistance.enums.InterviewStatus;
import com.disqo.interview_flow_service.persistance.enums.InterviewType;
import com.disqo.interview_flow_service.persistance.repositories.InterviewRepository;
import com.disqo.interview_flow_service.service.InterviewService;
import com.disqo.interview_flow_service.service.TalentService;
import com.disqo.interview_flow_service.service.dto.InterviewEventDTO;
import com.disqo.interview_flow_service.service.dto.InterviewRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final InterviewConverter interviewConverter;
    private final TalentService talentService;
    private final MailSenderClient mailSenderClient;

    @Value("${interview.flow.addEvent.selectedResponse}")
    private String selectedResponse;
    @Value("${interview.flow.addEvent.rejectedResponse}")
    private String rejectedResponse;

    @Override
    public String preparationInterview(InterviewRequestDTO interviewRequestDTO) {
        log.info("Started search prepared interview ");
        Interview interview = interviewRepository.findAllByTalent_IdAndInterviewStatus(interviewRequestDTO.getTalentDTO().getId(), InterviewStatus.PREPARED);
        if (interview == null) {
            interview = interviewConverter.convertToEntity(interviewRequestDTO);
            log.info("Creating preparation Interview");
            createPreparedInterview(interviewRequestDTO, interview);

        } else {
            log.info("again send email to Talent for selecting new times");
            String emailText = MailTextGenerator.getEmailText(interview.getTalent(), null, EmailTextType.TO_TALENT);
            sendEmail(interview.getTalent().getEmail(), MailTextGenerator.getSubject(), emailText, interview.getUrl());
            log.info("finish sending email to Talent");
            interview.setInterviewStatus(InterviewStatus.PREPARED);
            interviewRepository.save(interview);
            log.info("save  preparation Interview");
        }
        return "Preparation Interview successfully created";
    }

    private void createPreparedInterview(InterviewRequestDTO interviewRequestDTO, Interview interview) {
        log.info("Search interview count in Waiting_stage");
        Interview interviewCount = interviewRepository.findAllByTalent_IdAndInterviewStatus(interviewRequestDTO.getTalentDTO().getId(), InterviewStatus.WAITING_STAGE);

        log.info("chose interview type");
        InterviewType interviewType = interviewCount == null ? InterviewType.HR : InterviewType.TECHNICAL;

        interview.setTalent(talentService.findById(interviewRequestDTO.getTalentDTO().getId()));
        interview.setInterviewType(interviewType);
        interview.setInterviewStatus(InterviewStatus.PREPARED);
        interviewRepository.save(interview);
        log.info("save  preparation Interview");
        String emailText = MailTextGenerator.getEmailText(interview.getTalent(), null, EmailTextType.TO_TALENT_FIRST);
        log.info("get email subject and text");
        sendEmail(interview.getTalent().getEmail(), MailTextGenerator.getSubject(), emailText, interview.getUrl());
        log.info("send email to Talent");
    }

    private void sendEmail(String email, String subject, String text, URI uri) {
        MailDTO mailDTO = new MailDTO(email, subject, text, uri);
        mailSenderClient.sendEmail(mailDTO);
    }

    @Override
    public String addEvent(InterviewEventDTO eventDTO) {
        String responseString;
        log.info("Started search talent Email ");
        Talent talent = talentService.findTalentByEmail(eventDTO.getTalentEmail());

        log.info("Started search talent Preparation interview  ");
        Interview interview = talent.getInterviews().stream()
                .filter(s -> s.getInterviewStatus() == InterviewStatus.PREPARED)
                .findFirst().orElseThrow(() -> new RuntimeException("Not found prepared interview for this talent"));

        if (eventDTO.getEventType() == EventType.CREATED) {
            interview.setStartDate(eventDTO.getStartDate());
            interview.setEndDate(eventDTO.getEndDate());
            interview.setInterviewStatus(InterviewStatus.IN_PROGRESS);
            responseString = selectedResponse;
            log.info("doing sets ");
        } else {
            log.info("Started send email Users ");
            sendRejectionEmailUsers(interview);

            interview.setInterviewStatus(InterviewStatus.PREPARED);
            responseString = rejectedResponse;
        }
        interviewRepository.save(interview);
        log.info("save  addEvent");

        return responseString;
    }

    private void sendRejectionEmailUsers(Interview interview) {
        interview.getUsers().stream()
                .forEach(u -> {
                    String emailText = MailTextGenerator.getEmailText(null, u, EmailTextType.TO_USER);
                    sendEmail(u.getEmail(), MailTextGenerator.getSubject(), emailText, interview.getUrl());
                });
        log.info("finished send email Users ");
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
