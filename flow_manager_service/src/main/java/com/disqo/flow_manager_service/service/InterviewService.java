package com.disqo.flow_manager_service.service;

import com.disqo.flow_manager_service.client.InterviewClient;
import com.disqo.flow_manager_service.rest.dto.InterviewRequest;
import com.disqo.flow_manager_service.rest.dto.TalentDTO;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {
    private final InterviewClient interviewClient;

    public InterviewService(InterviewClient interviewClient) {
        this.interviewClient = interviewClient;
    }

    public TalentDTO getTalent(String email) {
        return interviewClient.getTalent(email);
    }

    public void sendTalentToInterview(InterviewRequest interviewRequest){
       interviewClient.sendTalent(interviewRequest);

    }

}
