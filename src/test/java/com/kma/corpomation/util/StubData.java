package com.kma.corpomation.util;

import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

public class StubData {
    public static MockMultipartFile createMockMultipartFile(String fileName) throws IOException {
        final String contentType = "pdf";
        final String filePath = "src/test/resources/testFile/"+fileName+"."+contentType;
        FileInputStream fileInputStream = new FileInputStream(filePath);

        return new MockMultipartFile(
                "file",
                fileName + "." + contentType,
                contentType,
                fileInputStream
        );
    }
}
