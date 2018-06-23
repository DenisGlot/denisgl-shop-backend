package com.denisgl.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;

public interface IProduct {

    int getId();

    String getName();

    String getBrand();

    String getDescription();

    double getUnitPrice();

    int getQuantity();

    ICategory getCategory();

    int getPurchases();

    int getViews();

    String getCode();

    boolean getActive();

    @Transient
    MultipartFile getFile();
}
