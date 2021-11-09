package com.disqo.interview_flow_service.persistance.repositories;

import com.disqo.interview_flow_service.persistance.entity.interview.Interview;
import com.disqo.interview_flow_service.persistance.entity.talent.Specialization;
import com.disqo.interview_flow_service.persistance.entity.talent.Talent;
import com.disqo.interview_flow_service.persistance.enums.InterviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
}
