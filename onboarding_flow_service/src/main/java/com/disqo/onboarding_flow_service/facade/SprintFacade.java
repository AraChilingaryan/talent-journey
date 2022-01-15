package com.disqo.onboarding_flow_service.facade;


import com.disqo.onboarding_flow_service.service.dto.SprintDto;

public interface SprintFacade {

    SprintDto create(SprintDto sprint);

    //void delete(Long id);
}
