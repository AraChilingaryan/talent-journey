package com.disqo.flow_manager_service.service;

import com.disqo.flow_manager_service.client.TalentClient;
import com.disqo.flow_manager_service.rest.dto.TalentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TalentService {

    private final TalentClient talentClient;
    public TalentService(TalentClient talentClient) {
        this.talentClient = talentClient;
    }

    public TalentDTO getTalent(String email) {
        return talentClient.getTalent(email);
    }
    public TalentDTO sendTalent( TalentDTO talentDTO ){
        return talentClient.sendTalent(talentDTO);

    }
}
