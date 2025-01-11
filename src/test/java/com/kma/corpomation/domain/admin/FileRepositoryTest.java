package com.kma.corpomation.domain.admin;

import com.kma.corpomation.ApiTest;
import com.kma.corpomation.domain.admin.entity.BusinessFile;
import com.kma.corpomation.domain.admin.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FileRepositoryTest extends ApiTest {

    @Autowired
    private FileRepository fileRepository;

    @Test
    void delete() {
        fileRepository.save(new BusinessFile("manager", "business", "fileUrl"));
        fileRepository.deleteById(1L);
    }
}
