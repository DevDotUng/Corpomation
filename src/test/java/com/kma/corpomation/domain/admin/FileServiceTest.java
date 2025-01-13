package com.kma.corpomation.domain.admin;

import com.kma.corpomation.ApiTest;
import com.kma.corpomation.domain.admin.dto.FileResponse;
import com.kma.corpomation.domain.admin.dto.UploadRequest;
import com.kma.corpomation.domain.admin.entity.BusinessFile;
import com.kma.corpomation.domain.admin.service.FileService;
import com.kma.corpomation.util.StubData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FileServiceTest extends ApiTest {

    private BusinessFile businessFile;

    @Autowired
    private FileService fileService;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @BeforeEach
    void beforeEach() throws IOException {
        MockMultipartFile file = StubData.createMockMultipartFile("testFile");
        businessFile = fileService.uploadFile(new UploadRequest("manager", "business", file));
    }

    @Test
    void uploadFile() throws IOException {
        assertThat(businessFile.getId()).isEqualTo(1);
        assertThat(businessFile.getManager()).isEqualTo("manager");
        assertThat(businessFile.getBusiness()).isEqualTo("business");
        assertThat(businessFile.getFileUrl()).isEqualTo("https://" + bucket + ".s3." + region + ".amazonaws.com/testFile.pdf");
        assertThat(businessFile.getCreateAt()).isNotNull();

        MockMultipartFile fileEqualsManagerAndBusiness = StubData.createMockMultipartFile("testFile2");
        BusinessFile businessFileEqualsManagerAndBusiness = fileService.uploadFile(new UploadRequest("manager", "business", fileEqualsManagerAndBusiness));

        assertThat(businessFileEqualsManagerAndBusiness.getId()).isEqualTo(1);
        assertThat(businessFileEqualsManagerAndBusiness.getManager()).isEqualTo("manager");
        assertThat(businessFileEqualsManagerAndBusiness.getBusiness()).isEqualTo("business");
        assertThat(businessFileEqualsManagerAndBusiness.getFileUrl()).isEqualTo("https://" + bucket + ".s3." + region + ".amazonaws.com/testFile2.pdf");
        assertThat(businessFileEqualsManagerAndBusiness.getCreateAt()).isNotNull();
    }

    @Test
    void deleteFile() {
        fileService.deleteFile(businessFile.getId());
    }

    @Test
    void getFiles() throws IOException {
        MockMultipartFile file = StubData.createMockMultipartFile("testFile");
        fileService.uploadFile(new UploadRequest("manager", "business1", file));
        fileService.uploadFile(new UploadRequest("manager", "business2", file));

        List<FileResponse> fileResponseList = fileService.getFiles();

        assertThat(fileResponseList.size()).isEqualTo(3);
    }
}
