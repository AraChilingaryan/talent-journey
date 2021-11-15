package com.disqo.interview_flow_service.controller;

import com.disqo.interview_flow_service.persistance.repositories.FeedbackRepository;
import com.disqo.interview_flow_service.service.dto.InterviewFeedbackRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FeedbackRepository feedbackRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Transactional
    void createPreparationInterview() throws Exception {
        long countBeforeCreate = feedbackRepository.count();

        InterviewFeedbackRequestDTO feedbackRequestDTO = createFeedbackDTO();
        mockMvc.perform(MockMvcRequestBuilders.post("/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(feedbackRequestDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").exists());

        assertThat(feedbackRepository.count()).isEqualTo(countBeforeCreate + 1);
    }

    public static InterviewFeedbackRequestDTO createFeedbackDTO() {
        InterviewFeedbackRequestDTO feedbackRequestDTO = new InterviewFeedbackRequestDTO();
        feedbackRequestDTO.setTopic("Java");
        feedbackRequestDTO.setFeedback("good OOP knowledge");
        feedbackRequestDTO.setScore(78);
        return feedbackRequestDTO;
    }
}



