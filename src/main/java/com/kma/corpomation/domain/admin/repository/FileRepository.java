package com.kma.corpomation.domain.admin.repository;

import com.kma.corpomation.domain.admin.entity.BusinessFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<BusinessFile, Long> {
    boolean existsByManagerAndBusiness(String manager, String business);
}
