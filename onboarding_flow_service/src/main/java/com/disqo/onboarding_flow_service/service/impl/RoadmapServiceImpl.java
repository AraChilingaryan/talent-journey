package com.disqo.onboarding_flow_service.service.impl;

import com.disqo.onboarding_flow_service.converter.RoadmapConverter;
import com.disqo.onboarding_flow_service.exception.RoadmapNotFoundException;
import com.disqo.onboarding_flow_service.persistance.RoadmapRepository;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.persistance.enums.RoadmapStatus;
import com.disqo.onboarding_flow_service.service.RoadmapService;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class RoadmapServiceImpl implements RoadmapService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoadmapServiceImpl.class);

    private final RoadmapRepository roadmapRepository;
    private final RoadmapConverter roadmapConverter;


    public RoadmapServiceImpl(RoadmapRepository roadmapRepository, RoadmapConverter roadmapConverter) {
        this.roadmapRepository = roadmapRepository;
        this.roadmapConverter = roadmapConverter;
    }

    @Override
    public List<Roadmap> findALl() {
        LOGGER.info("In findAll Roadmap requested to get all roadmaps");
        return this.roadmapRepository.findAll();
    }

    @Override
    public Roadmap findById(Long id) {
        LOGGER.info("In findById Roadmap requested to get the roadmap with id {}", id);
        return this.roadmapRepository.findById(id)
                .orElseThrow(() -> new RoadmapNotFoundException("No roadmap found by this id", id));
    }

    @Override
    public Roadmap create(RoadmapDTO roadmapDTO) {
        LOGGER.info("Requested to create a roadmap");
        final Roadmap roadmap = roadmapConverter.convertToEntity(roadmapDTO);
        roadmap.setStatus(RoadmapStatus.STARTED);
        LOGGER.info("In create Roadmap roadmap successfully created");
        return roadmapRepository.save(roadmap);
    }

    //TODO add logic for update
    @Override
    public Roadmap update(Long id, RoadmapDTO roadmapDTO){
//        LOGGER.info("Requested to update a talent with id {}", id);
//        final Roadmap roadmap = this.roadmapRepository.findById(id)
//                .orElseThrow(() -> new RoadmapNotFoundException("No roadmap found by this id", id));
//        roadmap.setName(talentDTO.getName());
//        roadmap.setSurname(talentDTO.getSurname());
//        roadmap.setEmail(talentDTO.getEmail());
//        roadmap.setPhoneNumber(talentDTO.getPhoneNumber());
//        roadmap.setSpecialization(specializationService.findById(talentDTO.getSpecializationId()));
//        LOGGER.info("In update Roadmap roadmap with id {} successfully updated", id);
//        return roadmapRepository.save(roadmap);
        return null;
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        LOGGER.info("Requested to delete a roadmap with id {}", id);
        if (!roadmapRepository.existsById(id)) {
            throw new RoadmapNotFoundException("No roadmap found by this id", id);
        }
        roadmapRepository.deleteById(id);
        LOGGER.info("In deleteById Roadmap roadmap with id {} successfully deleted", id);
        return true;
    }

    @Override
    public Roadmap updateStatus(Long id, String status) {
        LOGGER.info("Requested to update status to {} of a roadmap with id {}", status, id);
        final Roadmap roadmap = this.roadmapRepository.findById(id)
                .orElseThrow(() -> new RoadmapNotFoundException("No roadmap found by this id", id));
        roadmap.setStatus(RoadmapStatus.valueOf(status.toUpperCase(Locale.ROOT)));
        LOGGER.info("In updateStatus Roadmap the status of roadmap with id {} successfully updated to {}", id, status);
        return roadmapRepository.save(roadmap);
    }
}
