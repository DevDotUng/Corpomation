package com.kma.corpomation.domain.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UploadRequest {

    private String manager;
    private String business;
    private String fileUrl;

    public UploadRequest(String manager, String business, String fileUrl) {
        this.manager = manager;
        this.business = business;
        this.fileUrl = fileUrl;
    }
}