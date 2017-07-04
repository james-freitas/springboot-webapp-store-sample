package com.codeonblue.service;

import com.codeonblue.model.ProductImage;
import com.codeonblue.validator.PictureValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void init() {
        try {
            if(!Files.exists(rootLocation)) {
                Files.createDirectory(rootLocation);
            }
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public void storeProductImage(ProductImage productImage) {
        MultipartFile imageFile = productImage.getImageFile();
        isValidExtension(imageFile);


        String targetFileLocation = productImage.getProductImageId() + ".jpg";

        if(imageFile != null && !imageFile.isEmpty()){
            try {
                Files.copy(imageFile.getInputStream(), this.rootLocation.resolve(targetFileLocation), REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Product image saving failed", e);
            }
        }
    }

    private boolean isValidExtension(MultipartFile imageFile) {
        String originalFilename = imageFile.getOriginalFilename();
        String[] filename = originalFilename.split(".");
        return false;
    }
}
