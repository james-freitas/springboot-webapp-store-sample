package com.codeonblue.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PictureValidator extends FileValidator{

    private static final String ACCEPTED_PICTURE_EXTENSIONS = ".PNG.JPG";

    @Override
    public boolean isValidExtension(MultipartFile file) {
        return ACCEPTED_PICTURE_EXTENSIONS.lastIndexOf(getFileExtension(file).toUpperCase()) != -1;
    }
}
