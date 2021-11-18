package com.disqo.interview_flow_service.persistance.repositories;

import com.disqo.interview_flow_service.persistance.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
}
