package com.kma.corpomation.domain.admin;

import com.kma.corpomation.ApiTest;
import com.kma.corpomation.util.StubData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
public class AdminControllerTest extends ApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void admin() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void upload() throws Exception {
        MockMultipartFile mockMultipartFile = StubData.createMockMultipartFile();

        mockMvc.perform(multipart("/upload")
                .file(mockMultipartFile)
                .param("manager", "manager")
                .param("business", "business")
                ).andExpect(status().isFound());
    }

    @Test
    void delete() throws Exception {
        MockMultipartFile mockMultipartFile = StubData.createMockMultipartFile();

        mockMvc.perform(multipart("/upload")
                .file(mockMultipartFile)
                .param("manager", "manager")
                .param("business", "business")
        );

        mockMvc.perform(MockMvcRequestBuilders.delete("/{id}", 1)
        ).andExpect(status().isFound());
    }
}
