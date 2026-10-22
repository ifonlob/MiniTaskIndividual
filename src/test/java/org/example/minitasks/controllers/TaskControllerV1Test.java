package org.example.minitasks.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerV1Test {

    @Autowired
    private MockMvc mvc;

    @Test
    void getsExistingTaskById() throws Exception {
        String createJson = """
            {
                "title": "Comprar leche"
            }
            """;

        mvc.perform(post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson))
                .andExpect(status().isCreated());

        mvc.perform(get("/v1/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Comprar leche"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    void missingTaskReturns404() throws Exception{

        mvc.perform(get("/v1/tasks/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));

    }
}