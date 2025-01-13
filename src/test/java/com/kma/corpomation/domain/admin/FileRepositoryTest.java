package com.kma.corpomation.domain.admin;

import com.kma.corpomation.ApiTest;
import com.kma.corpomation.domain.admin.entity.BusinessFile;
import com.kma.corpomation.domain.admin.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class FileRepositoryTest extends ApiTest {

    @Autowired
    private FileRepository fileRepository;

    @Test
    void delete() {
        fileRepository.save(new BusinessFile("manager", "business", "fileUrl"));
        fileRepository.deleteById(1L);
    }

    @Test
    void existsByManagerAndBusiness() {
        fileRepository.save(new BusinessFile("manager", "business", "fileUrl"));

        boolean exists = fileRepository.existsByManagerAndBusiness("manager", "business");
        boolean notExistsManager = fileRepository.existsByManagerAndBusiness("business", "business");
        boolean notExistsBusiness = fileRepository.existsByManagerAndBusiness("manager", "manager");

        assertThat(exists).isTrue();
        assertThat(notExistsManager).isFalse();
        assertThat(notExistsBusiness).isFalse();
    }
}
