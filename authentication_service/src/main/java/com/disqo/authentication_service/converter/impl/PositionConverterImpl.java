package com.disqo.authentication_service.converter.impl;

import com.disqo.authentication_service.converter.PositionConverter;

import com.disqo.authentication_service.persistance.model.Position;
import com.disqo.authentication_service.rest.dto.PositionDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
//
//@Component
//public class PositionConverterImpl implements PositionConverter {
//    @Override
//    public List<Position> convertToEntity(List<PositionDTO> positionDTO) {
//        List<Position> rv = new ArrayList<>();
//        for (int i = 0; i < positionDTO.size(); i++) {
//        if (positionDTO.get(i) == null){
//            return null;
//        }else {
//            Position entity = new Position();
//            entity.setId(positionDTO.get(i).getId());
//            entity.setPositionType(positionDTO.get(i).getPositionType());
//            rv.add(entity);
//        }
//    }
//
//
//        return rv;
//    }
////
//    @Override
//    public List<PositionDTO> convertToDTO(List<Position> position) {
////        List<PositionDTO> rv = new ArrayList<>();
////        for (int i = 0; i < position.size(); i++) {
////            PositionDTO dto = new PositionDTO();
////        dto.setId(position.get(i).getId());
////        dto.setPositionType(position.get(i).getPositionType());
////        dto.setUsers(position.get(i).getUsers());
////
////
////
////
////        }
////        return rv;
////    }
//
//
//
//}
