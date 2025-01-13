package com.kma.corpomation.domain.admin.service;

import com.kma.corpomation.domain.admin.dto.FileResponse;
import com.kma.corpomation.domain.admin.dto.UploadRequest;
import com.kma.corpomation.domain.admin.entity.BusinessFile;
import com.kma.corpomation.domain.admin.repository.FileRepository;
import com.kma.corpomation.domain.s3.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private S3Service s3Service;

    public BusinessFile uploadFile(UploadRequest uploadRequest) {
        String fileUrl = s3Service.upload(uploadRequest.getFile());

        BusinessFile file = new BusinessFile(uploadRequest.getManager(), uploadRequest.getBusiness(), fileUrl);
        BusinessFile businessFile = fileRepository.save(file);

        return businessFile;
    }

    public void deleteFile(Long id) {
        BusinessFile businessFile = fileRepository.findById(id).get();
        String[] splitFileUrl = businessFile.getFileUrl().split("/");
        String fileName = splitFileUrl[splitFileUrl.length - 1];

        s3Service.delete(fileName);
        fileRepository.deleteById(id);
    }

    public List<FileResponse> getFiles() {
        List<BusinessFile> businessFileList = fileRepository.findAll();
        List<FileResponse> fileResponseList = new ArrayList<>();

        for (BusinessFile businessFile: businessFileList)
            fileResponseList.add(new FileResponse(businessFile.getId(), businessFile.getManager(), businessFile.getBusiness(), businessFile.getFileUrl(), businessFile.getCreateAt().toString()));

        return fileResponseList;
    }
}
