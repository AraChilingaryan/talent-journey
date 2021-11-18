package com.disqo.interview_flow_service.persistance.repositories;

import com.disqo.interview_flow_service.persistance.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TalentRepository extends JpaRepository<Talent, Long> {

    Optional<Talent> findTalentByEmail(String email);

}
