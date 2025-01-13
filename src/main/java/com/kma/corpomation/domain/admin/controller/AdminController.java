package com.kma.corpomation.domain.admin.controller;

import com.kma.corpomation.domain.admin.dto.FileResponse;
import com.kma.corpomation.domain.admin.dto.UploadRequest;
import com.kma.corpomation.domain.admin.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private FileService fileService;

    @GetMapping("/index")
    public String admin(Model model) {
        List<FileResponse> fileResponseList = fileService.getFiles();

        model.addAttribute("files", fileResponseList);

        return "index";
    }

    @PostMapping("/upload")
    public String upload(
            @RequestParam String manager,
            @RequestParam String business,
            @RequestParam("file") MultipartFile file) {
        UploadRequest uploadRequest = new UploadRequest(manager, business, file);

        fileService.uploadFile(uploadRequest);
        List<FileResponse> fileResponseList = fileService.getFiles();

        return "redirect:/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        fileService.deleteFile(id);

        return "redirect:/index";
    }
}
