package com.disqo.authentication_service.rest;

import com.disqo.authentication_service.persistance.model.User;
import com.disqo.authentication_service.repository.UserRepository;
import com.disqo.authentication_service.rest.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.http.RequestEntity.get;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user", roles = {"ADMIN"})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;


    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @Transactional
    void createUser() throws Exception {
        long countBeforeCreate = userRepository.count();

        UserDTO userDTO = createUserDTO();
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

        assertThat(userRepository.count()).isEqualTo(countBeforeCreate + 1);
    }


    public static UserDTO createUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("test");
        userDTO.setLastName("lastTest");
        userDTO.setPassword("1234");

        return userDTO;
    }


}
