package com.disqo.authentication_service.repository;

import com.disqo.authentication_service.persistance.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    Optional<Position> findByPositionType(String type);

}
