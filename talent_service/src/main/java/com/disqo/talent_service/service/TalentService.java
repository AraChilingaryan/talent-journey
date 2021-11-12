package com.disqo.talent_service.service;

import com.disqo.talent_service.exception.TalentNotFoundException;
import com.disqo.talent_service.persistance.entity.*;
import com.disqo.talent_service.service.dto.TalentRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public interface TalentService {

    List<Talent> findALl();

    Talent findById(Long id) throws TalentNotFoundException;

    Talent create(TalentRequestDTO talentDTO);

    Talent update(Long id, TalentRequestDTO talentDTO) throws TalentNotFoundException;

    boolean deleteById(Long id);

    Talent updateStatus(Long id, String status);

    List<Talent> findBySpecializationId(Long specializationId);
}
