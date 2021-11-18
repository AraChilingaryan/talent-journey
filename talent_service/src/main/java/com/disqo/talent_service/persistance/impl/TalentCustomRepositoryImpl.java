package com.disqo.talent_service.persistance.impl;

import com.disqo.talent_service.persistance.TalentCustomRepository;
import com.disqo.talent_service.persistance.entity.Talent;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class TalentCustomRepositoryImpl implements TalentCustomRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder cb;

    public TalentCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.cb = entityManager.getCriteriaBuilder();
    }

    @Override
    public List<Talent> findBySpecializationId(Long specializationId) {

        CriteriaQuery<Talent> cq = cb.createQuery(Talent.class);

        Root<Talent> talent = cq.from(Talent.class);

        Predicate specializationPredicate = cb.equal(talent.get("specialization"), specializationId);

        cq.where(specializationPredicate);

        TypedQuery<Talent> query = entityManager.createQuery(cq);

        return query.getResultList();
    }
}
