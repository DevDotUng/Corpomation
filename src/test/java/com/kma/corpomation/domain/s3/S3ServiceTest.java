package com.kma.corpomation.domain.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.kma.corpomation.ApiTest;
import com.kma.corpomation.domain.s3.service.S3Service;
import com.kma.corpomation.util.StubData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@AutoConfigureMockMvc
public class S3ServiceTest extends ApiTest {

    @Autowired
    private S3Service s3Service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @BeforeEach
    void beforeEach() {
        given(amazonS3Client.putObject(any(PutObjectRequest.class))).willReturn(new PutObjectResult());
        given(amazonS3Client.doesObjectExist(any(), any())).willReturn(true);
        doNothing().when(amazonS3Client).deleteObject(any(), any());
    }

    @Test
    void upload() throws Exception {
        MockMultipartFile file = StubData.createMockMultipartFile("testFile");
        String fileUrl = s3Service.upload(file);

        assertThat(fileUrl).isEqualTo("https://" + bucket + ".s3." + region + ".amazonaws.com/testFile.pdf");
    }

    @Test
    void delete() throws Exception {
        MockMultipartFile file = StubData.createMockMultipartFile("testFile");
        String fileName = file.getOriginalFilename();
        String name = s3Service.delete(fileName);

        assertThat(name).isEqualTo("testFile.pdf");
    }
}