package com.disqo.onboarding_flow_service.persistance;

import com.disqo.onboarding_flow_service.persistance.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
}
