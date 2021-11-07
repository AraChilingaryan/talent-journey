package com.disqo.onboarding_flow_service.persistance;

import com.disqo.onboarding_flow_service.persistance.entity.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhaseRepository extends JpaRepository<Phase, Long> {
}

