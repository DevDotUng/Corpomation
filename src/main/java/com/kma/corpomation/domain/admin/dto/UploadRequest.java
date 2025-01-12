package com.kma.corpomation.domain.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class UploadRequest {

    private String manager;
    private String business;
    private MultipartFile file;

    public UploadRequest(String manager, String business, MultipartFile file) {
        this.manager = manager;
        this.business = business;
        this.file = file;
    }
}