package com.disqo.authentication_service.repository;

import com.disqo.authentication_service.persistance.model.User;
import com.disqo.authentication_service.rest.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);

}
