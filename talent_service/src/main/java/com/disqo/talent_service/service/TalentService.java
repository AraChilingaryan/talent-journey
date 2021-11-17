package com.disqo.talent_service.service;

import com.disqo.talent_service.exception.TalentNotFoundException;
import com.disqo.talent_service.persistance.entity.*;
import com.disqo.talent_service.service.dto.TalentRequestDTO;
import com.disqo.talent_service.service.dto.TalentResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public interface TalentService {

    List<Talent> findALl();

    Talent findById(Long id) throws TalentNotFoundException;

    Talent create(TalentRequestDTO talentDTO);

    Talent update(Long id, TalentRequestDTO talentDTO) throws TalentNotFoundException;

    boolean deleteById(Long id);

    TalentResponseDTO updateStatus(TalentRequestDTO dto);

    List<Talent> findBySpecializationId(Long specializationId);

    String addCVForTalent(Long id, MultipartFile file) throws IOException ;
}
