package com.kma.corpomation.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileResponse {
    private Long id;
    private String manager;
    private String business;
    private String fileUrl;
    private String createAt;
}
