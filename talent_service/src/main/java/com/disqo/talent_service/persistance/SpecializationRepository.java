package com.disqo.talent_service.persistance;

import com.disqo.talent_service.persistance.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization,Long> {
}
