package com.disqo.onboarding_flow_service.persistance;

import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadmapRepository extends JpaRepository<Roadmap,Long> {
}
