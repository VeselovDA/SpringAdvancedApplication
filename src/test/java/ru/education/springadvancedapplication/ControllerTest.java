package ru.education.springadvancedapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.education.springadvancedapplication.util.PersonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class ControllerTest extends BaseTest{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    void validatePersonWithError(){
        var person= PersonUtil.createInvalidPerson();
        mockMvc.perform(
                post("/validationPerson")
                .content(objectMapper.writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
    @SneakyThrows
    @Test
    void validatePerson(){
        var person= PersonUtil.createPerson();
        mockMvc.perform(
                        post("/validationPerson")
                                .content(objectMapper.writeValueAsString(person))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
