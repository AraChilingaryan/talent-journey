package com.disqo.interview_flow_service.persistance.repositories;

import com.disqo.interview_flow_service.persistance.entity.interview.InterviewFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<InterviewFeedback, Long> {

//    List<InterviewFeedback> findAllByTalentIdAndInterview_InterviewStatus_WaitingStage(@Param("talent_id") Long talent_id);

}
