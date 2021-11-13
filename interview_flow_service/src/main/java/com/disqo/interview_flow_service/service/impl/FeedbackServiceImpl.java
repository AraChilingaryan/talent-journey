package com.disqo.interview_flow_service.service.impl;

import com.disqo.interview_flow_service.client.JourneyClient;
import com.disqo.interview_flow_service.converter.FeedbackConverter;
import com.disqo.interview_flow_service.converter.TalentConverter;
import com.disqo.interview_flow_service.persistance.entity.Talent;
import com.disqo.interview_flow_service.persistance.entity.interview.Interview;
import com.disqo.interview_flow_service.persistance.entity.interview.InterviewFeedback;
import com.disqo.interview_flow_service.persistance.enums.InterviewStatus;
import com.disqo.interview_flow_service.persistance.enums.InterviewType;
import com.disqo.interview_flow_service.persistance.enums.TalentStatus;
import com.disqo.interview_flow_service.persistance.repositories.FeedbackRepository;
import com.disqo.interview_flow_service.persistance.repositories.InterviewRepository;
import com.disqo.interview_flow_service.service.FeedbackService;
import com.disqo.interview_flow_service.service.dto.InterviewFeedbackRequestDTO;
import com.disqo.interview_flow_service.service.dto.InterviewFeedbackResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;


@Service
@Slf4j
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackConverter feedbackConverter;
    private final InterviewRepository interviewRepository;
    private final JourneyClient journeyClient;
    private final TalentConverter talentConverter;

    @Value("${interview.flow.passing.threshold}")
    private Integer passingThreshold;

    @Override
    public InterviewFeedbackResponseDTO create(InterviewFeedbackRequestDTO feedbackRequestDTO, InterviewType interviewType, Long talentId) {
        log.info("Started search IN_PROGRESS interview ");
        Interview interview = interviewRepository.findAllByTalent_IdAndInterviewStatus(talentId, InterviewStatus.IN_PROGRESS);
        if (interview == null) {
            log.warn("Exception during find IN_PROGRESS interview for this talent => field: {}, value {}", "id", talentId);
            throw new RuntimeException("Not found IN_PROGRESS interview for this talent");
        }
        InterviewFeedback entity = feedbackConverter.convertToEntity(feedbackRequestDTO);
        Talent talent = interview.getTalent();
        log.info("chose interview type");

        switch (interviewType) {
            case HR:
                if (feedbackRequestDTO.getScore() >= passingThreshold) {
                    interview.setInterviewStatus(InterviewStatus.WAITING_STAGE);

                } else {
                    interview.setInterviewStatus(InterviewStatus.CLOSED);

                }
                entity.setInterview(interview);
                break;

            case TECHNICAL:
                if (feedbackRequestDTO.getScore() >= passingThreshold) {
                    passedTechInterview(interview, entity);
                    log.info("calculate average score");
                    double averageScore = calculateAVG(interview.getTalent().getInterviews());
                    avgScoreValidation(interview, talent, averageScore);
                    journeyClient.sendFinalResult(this.talentConverter.convertToDTO(talent));
                } else {
                    log.info("change HR interview status FINISHED && TECH interview status  CLOSED");
                    techInterviewRejection(interview, entity, talent);
                }
        }
        feedbackRepository.save(entity);
        interviewRepository.save(interview);
        log.info("save Feedback");
        return feedbackConverter.convertToDTO(entity);
    }

    private void passedTechInterview(Interview interview, InterviewFeedback entity) {
        interview.setInterviewStatus(InterviewStatus.WAITING_STAGE);
        interview.setInterviewFeedback(entity);
        entity.setInterview(interview);
        feedbackRepository.save(entity);
        interviewRepository.save(interview);
    }

    private void techInterviewRejection(Interview interview, InterviewFeedback entity, Talent talent) {
        interview.setInterviewFeedback(entity);
        talent.getInterviews().stream()
                .filter(t -> t.getInterviewStatus() == InterviewStatus.WAITING_STAGE)
                .forEach(i -> i.setInterviewStatus(InterviewStatus.FINISHED));
        interview.setInterviewStatus(InterviewStatus.CLOSED);
        entity.setInterview(interview);
    }

    private void avgScoreValidation(Interview interview, Talent talent, double averageScore) {
        if (averageScore >= passingThreshold) {
            talent.setOverallScore((int) averageScore);
            talent.setTalentStatus(TalentStatus.PASSED);
            log.info("change HR && TECH interview statuses  SUCCEED");

            interview.getTalent().getInterviews().stream()
                    .filter(t -> t.getInterviewStatus() == InterviewStatus.WAITING_STAGE)
                    .forEach(i -> i.setInterviewStatus(InterviewStatus.SUCCEED));
        }
    }

    private double calculateAVG(List<Interview> interviews) {
        OptionalDouble avg = interviews
                .stream().filter(s -> s.getInterviewStatus() == InterviewStatus.WAITING_STAGE)
                .map(Interview::getInterviewFeedback)
                .mapToInt(InterviewFeedback::getScore).average();
        if (avg.isEmpty()) {
            log.warn("Exception occurred during calculate average score => field: {}, value {}", "avg", avg);
            throw new NoSuchElementException("No value present");
        }
        return avg.getAsDouble();

    }

}
