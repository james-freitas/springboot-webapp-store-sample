package com.codeonblue.validator;

import org.springframework.web.multipart.MultipartFile;

public abstract class FileValidator {

    public abstract boolean isValidExtension(MultipartFile file);

    public final String getFileExtension(MultipartFile file) {
        return file.getName().substring(file.getName().lastIndexOf('.'));
    }
}
