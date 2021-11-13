package com.disqo.talent_service.persistance;


import com.disqo.talent_service.persistance.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TalentRepository extends JpaRepository<Talent,Long>, TalentCustomRepository {

    Optional<Talent> findByEmail(String email);
}
