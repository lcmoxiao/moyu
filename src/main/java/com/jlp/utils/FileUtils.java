package com.jlp.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class FileUtils {

    public static String saveWorkFile(MultipartFile file, String uploadPath) throws IOException {
        if (!file.isEmpty()) {
            String tail = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
            String fn = UUID.randomUUID().toString() + '.' + tail;
            file.transferTo(new File(uploadPath + File.separator + fn));
            return fn;
        }
        return null;
    }

}
