package com.disqo.authentication_service.converter;


import com.disqo.authentication_service.persistance.model.Position;
import com.disqo.authentication_service.rest.dto.PositionDTO;

import java.util.List;

public interface PositionConverter {

    List<Position> convertToEntity(List<PositionDTO> positionDTO);

    List<PositionDTO> convertToDTO(List<Position> position);
}

