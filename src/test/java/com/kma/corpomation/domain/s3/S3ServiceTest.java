package com.kma.corpomation.domain.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.kma.corpomation.ApiTest;
import com.kma.corpomation.util.StubData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class ControllerTest extends ApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AmazonS3Client amazonS3Client;

    @BeforeEach
    void beforeEach() {
        given(amazonS3Client.putObject(any(PutObjectRequest.class))).willReturn(new PutObjectResult());
    }

    @Test
    void 이미지_등록() throws Exception {
        MockMultipartFile file = StubData.createMockMultipartFile();

        mockMvc.perform(
                multipart("/upload")
                        .file(file)
        ).andExpect(status().isOk());
    }
}