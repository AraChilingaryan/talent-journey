package com.disqo.interview_flow_service.service.serviceImpl;

import com.disqo.interview_flow_service.converter.FeedbackConverter;
import com.disqo.interview_flow_service.persistance.entity.interview.Interview;
import com.disqo.interview_flow_service.persistance.entity.interview.InterviewFeedback;
import com.disqo.interview_flow_service.persistance.entity.talent.Talent;
import com.disqo.interview_flow_service.persistance.enums.InterviewStatus;
import com.disqo.interview_flow_service.persistance.enums.InterviewType;
import com.disqo.interview_flow_service.persistance.repositories.FeedbackRepository;
import com.disqo.interview_flow_service.persistance.repositories.InterviewRepository;
import com.disqo.interview_flow_service.service.FeedbackService;
import com.disqo.interview_flow_service.service.InterviewService;
import com.disqo.interview_flow_service.service.TalentService;
import com.disqo.interview_flow_service.service.dto.InterviewFeedbackRequestDTO;
import com.disqo.interview_flow_service.service.dto.InterviewFeedbackResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    private final TalentService talentService;
    private final InterviewService interviewService;
    private final FeedbackRepository feedbackRepository;
    private final FeedbackConverter feedbackConverter;
    private final InterviewRepository interviewRepository;


    public FeedbackServiceImpl(TalentService talentService,
                               InterviewService interviewService,
                               FeedbackRepository feedbackRepository,
                               FeedbackConverter feedbackConverter, InterviewRepository interviewRepository) {
        this.talentService = talentService;
        this.interviewService = interviewService;
        this.feedbackRepository = feedbackRepository;
        this.feedbackConverter = feedbackConverter;
        this.interviewRepository = interviewRepository;
    }

    @Override
    public InterviewFeedbackResponseDTO create(InterviewFeedbackRequestDTO feedbackRequestDTO, InterviewType interviewType, Long talent_id) {

        log.info("Started search In_progress interview ");
        Interview interview = interviewRepository.findAllByTalent_IdAndInterviewStatus(talent_id, InterviewStatus.IN_PROGRESS);
        if (interview == null) {
            log.warn("Exception during find in_progress interview for this talent => field: {}, value {}", "id", talent_id);
            throw new RuntimeException("Not found in_progress interview for this talent");
        }
        InterviewFeedback entity = feedbackConverter.convertToEntity(feedbackRequestDTO);
        Talent talent = interview.getTalent();
        log.info("chose interview type");

        switch (interviewType) {
            case HR:
                if (feedbackRequestDTO.getScore() >= 50) {
                    interview.setInterviewStatus(InterviewStatus.WAITING_STAGE);

                } else {
                    interview.setInterviewStatus(InterviewStatus.CLOSED);

                }
                entity.setInterview(interview);
                break;

            case TECHNICAL:
                if (feedbackRequestDTO.getScore() >= 50) {
                    interview.setInterviewStatus(InterviewStatus.WAITING_STAGE);
                    interview.setInterviewFeedback(entity);
                    entity.setInterview(interview);
                    feedbackRepository.save(entity);
                    interviewRepository.save(interview);

                    log.info("calculate average score");
                    double averageScore = calculateAVG(interview.getTalent().getInterviews());

                    if (averageScore >= 50) {
                        talent.setOverallScore((int) averageScore);
                        log.info("change HR && TECH interview statuses  SUCCEED");

                        interview.getTalent().getInterviews().stream()
                                .filter(s -> s.getInterviewStatus() == InterviewStatus.WAITING_STAGE)
                                .map(s -> {
                                    s.setInterviewStatus(InterviewStatus.SUCCEED);
                                    return s;
                                }).collect(Collectors.toList());
                    }


                } else {
                    log.info("change HR interview status FINISHED && TECH interview status  CLOSED");
                    interview.setInterviewFeedback(entity);
                    talent.getInterviews().stream()
                            .filter(s -> s.getInterviewStatus() == InterviewStatus.WAITING_STAGE)
                            .map(s -> {
                                s.setInterviewStatus(InterviewStatus.FINISHED);
                                return s;
                            }).collect(Collectors.toList());
                    interview.setInterviewStatus(InterviewStatus.CLOSED);
                    entity.setInterview(interview);

                }
        }
        feedbackRepository.save(entity);
        interviewRepository.save(interview);
        log.info("save Feedback");

        return feedbackConverter.convertToDTO(entity);
    }

    private double calculateAVG(List<Interview> interviews) {
        return interviews
                .stream().filter(s -> s.getInterviewStatus() == InterviewStatus.WAITING_STAGE)
                .map(Interview::getInterviewFeedback)
                .mapToInt(s -> s.getScore()).average().getAsDouble();
    }

}
