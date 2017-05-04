package com.codeonblue.service;

import com.codeonblue.model.ProductImage;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

  /*  void store(MultipartFile file);

    void storePictureToFilename(MultipartFile file, String filename);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll(); */

    void storeProductImage(ProductImage productImage);

}
