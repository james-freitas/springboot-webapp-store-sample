package com.codeonblue.service;

import com.codeonblue.model.ProductImage;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void storeProductImage(ProductImage productImage);

    void removeImageFile(Long id);
}
