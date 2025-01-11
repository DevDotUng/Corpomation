package com.kma.corpomation.domain.admin;

import com.kma.corpomation.ApiTest;
import com.kma.corpomation.domain.admin.dto.FileResponse;
import com.kma.corpomation.domain.admin.dto.UploadRequest;
import com.kma.corpomation.domain.admin.entity.BusinessFile;
import com.kma.corpomation.domain.admin.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FileServiceTest extends ApiTest {

    @Autowired
    private FileService fileService;

    private BusinessFile businessFile;

    @BeforeEach
    void beforeEach() {
        businessFile = fileService.uploadFile(new UploadRequest("manager", "business", "fileUrl"));
    }

    @Test
    void uploadFile() {
        assertThat(businessFile.getId()).isEqualTo(1);
        assertThat(businessFile.getManager()).isEqualTo("manager");
        assertThat(businessFile.getBusiness()).isEqualTo("business");
        assertThat(businessFile.getFileUrl()).isEqualTo("fileUrl");
        assertThat(businessFile.getCreateAt()).isNotNull();
    }

    @Test
    void deleteFile() {
        fileService.deleteFile(businessFile.getId());
    }

    @Test
    void getFiles() {
        fileService.uploadFile(new UploadRequest("manager", "business", "fileUrl"));
        fileService.uploadFile(new UploadRequest("manager", "business", "fileUrl"));

        List<FileResponse> fileResponseList = fileService.getFiles();

        assertThat(fileResponseList.size()).isEqualTo(3);
    }
}
