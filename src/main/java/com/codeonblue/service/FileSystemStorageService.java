package com.codeonblue.service;

import com.codeonblue.model.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class FileSystemStorageService implements StorageService {

    // TODO - Implement delete the file

    private Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public FileSystemStorageService(){}

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

        if(isValidExtension(imageFile)){
            String targetFileLocation = productImage.getImageFileName();

            if(imageFile != null && !imageFile.isEmpty()){
                try {
                    Files.copy(imageFile.getInputStream(), this.rootLocation.resolve(targetFileLocation), REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Product image saving failed", e);
                }
            }
        } else {
            throw new RuntimeException("Invalid image extension.  Use png.");
        }


    }

    @Override
    public void removeImageFile(Long id) {
        String fileName = "/" + id + ".png";
        Path filePath = Paths.get(rootLocation + fileName);

        if(Files.exists(filePath)){
            try {
                Files.delete(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isValidExtension(MultipartFile imageFile) {
        String originalFilename = imageFile.getOriginalFilename();
        String[] filename = originalFilename.split("\\.");
        return filename[1].equalsIgnoreCase("png");
                // || filename[1].equalsIgnoreCase("jpg");

        // TODO - Implement option for png or jpg (Might change product and productImage modeling)
    }
}
