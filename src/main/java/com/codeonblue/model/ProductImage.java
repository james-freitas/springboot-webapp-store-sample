package com.codeonblue.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductImage {

    private Long productImageId;
    private MultipartFile imageFile;

    public ProductImage() {}


    public static ProductImage newInstance(Long productId, MultipartFile imageFile) {

        ProductImage productImage = new ProductImage();
        productImage.setImageFile(imageFile);
        productImage.setProductImageId(productId);
        return productImage;
    }


    public Long getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(Long productImageId) {
        this.productImageId = productImageId;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFileName(){
        return this.productImageId + "." + this.imageFile.getOriginalFilename().split("\\.")[1];
    }
}
