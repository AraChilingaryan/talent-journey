package com.disqo.talent_service.repository;

import com.disqo.talent_service.persistance.TalentRepository;
import com.disqo.talent_service.persistance.entity.Talent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TalentRepositoryTest {

    @Autowired
    private TalentRepository talentRepository;

    @Test
    void saveTalent() {
        Talent talent = new Talent();
        talent.setName("test");

        long countBeforeSave = talentRepository.count();

        talentRepository.save(talent);

        assertThat(talentRepository.count()).isEqualTo(countBeforeSave + 1);

    }

    @Test
    @Transactional
    void getTalent() {
        Talent talent = new Talent();
        talent.setName("test");

        talentRepository.save(talent);

        assertThat(talentRepository.count()).isGreaterThan(0);

    }
}
