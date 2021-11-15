package com.disqo.interview_flow_service.controller;

import com.disqo.interview_flow_service.persistance.entity.Specialization;
import com.disqo.interview_flow_service.persistance.entity.Talent;
import com.disqo.interview_flow_service.persistance.entity.User;
import com.disqo.interview_flow_service.persistance.entity.interview.Interview;
import com.disqo.interview_flow_service.persistance.enums.EventType;
import com.disqo.interview_flow_service.persistance.enums.TalentStatus;
import com.disqo.interview_flow_service.persistance.repositories.InterviewRepository;
import com.disqo.interview_flow_service.service.dto.InterviewEventDTO;
import com.disqo.interview_flow_service.service.dto.InterviewRequestDTO;
import com.disqo.interview_flow_service.service.dto.TalentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class InterviewControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private InterviewRepository interviewRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Transactional
    void createPreparationInterview() throws Exception {
        long countBeforeCreate = interviewRepository.count();

        InterviewRequestDTO interviewRequestDTO = createInterviewDTO();
        mockMvc.perform(MockMvcRequestBuilders.post("/interview")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(interviewRequestDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").exists());

        assertThat(interviewRepository.count()).isEqualTo(countBeforeCreate + 1);
    }

    public static InterviewRequestDTO createInterviewDTO() {
        List<Long> userIds = (Arrays.asList(1L));
        InterviewRequestDTO interviewDTO = new InterviewRequestDTO();
        TalentDTO talentDTO = new TalentDTO();
        talentDTO.setId(3L);
        talentDTO.setEmail("azizyanasi@gmail.com");
        talentDTO.setName("yaya");
        talentDTO.setTalentStatus(TalentStatus.APPLIED);
        talentDTO.setPhoneNumber("098754754");
        interviewDTO.setTalentDTO(talentDTO);
        interviewDTO.setCalendarURI("https://calendly.com/chilingaryanara96/on-to-one");
        interviewDTO.setSpecializationId(1L);
        interviewDTO.setUserIDs(userIds);

        return interviewDTO;
    }

    @Test
    @Transactional
    void createAddEvent() throws Exception {

        InterviewEventDTO interviewEventDTO = createEventDTO();
        mockMvc.perform(MockMvcRequestBuilders.put("/interview")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(interviewEventDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").exists());

    }

    public static InterviewEventDTO createEventDTO() {

        InterviewEventDTO interviewDTO = new InterviewEventDTO();
        interviewDTO.setTalentEmail("azizyanasi@gmail.com");
        interviewDTO.setEventType(EventType.CREATED);
        interviewDTO.setStartDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        interviewDTO.setEndDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));


        return interviewDTO;
    }


    @ParameterizedTest
    @ValueSource(ints = {1})
    @Transactional
    void getItem() throws Exception {
         Interview interview = interviewRepository.saveAndFlush(createInterviewEntity());
        mockMvc.perform(MockMvcRequestBuilders.get("/interview/search/{talentId}", interview.getTalent().getId())
                        .param("talentId","3")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " ))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(interview.getId().intValue()));
    }

    public static Interview createInterviewEntity() {
        Interview interview = new Interview();
        Talent talent = new Talent();
        Specialization spec=new Specialization();
        User user=new User();
        List<User>users = (Arrays.asList(user));
        talent.setId(3L);
        talent.setEmail("azizyanasi@gmail.com");
        talent.setName("yaya");
        talent.setTalentStatus(TalentStatus.APPLIED);
        talent.setPhoneNumber("098754754");
        interview.setTalent(talent);
        user.setId(1L);
        user.setFirstName("user");
        user.setLastName("yan");
        interview.setUsers(users);
        interview.setUrl(URI.create("https://calendly.com/chilingaryanara96/on-to-one"));
        spec.setId(1L);
        spec.setSpecializationType("JAVA");
        interview.setSpecialization(spec);
        return interview;
    }


}
