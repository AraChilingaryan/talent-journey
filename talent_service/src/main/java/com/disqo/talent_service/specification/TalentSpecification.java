package com.disqo.talent_service.specification;

import com.disqo.talent_service.persistance.entity.Talent;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TalentSpecification {

//    public static Specification<Talent> hasSpecialization(String firstname){
//        return ((root, criteriaQuery, criteriaBuilder) -> {
//            return criteriaBuilder.equal(root.get(Talent),firstname);
//        });
//    }
}
