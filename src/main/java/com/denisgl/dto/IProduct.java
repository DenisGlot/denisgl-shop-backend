package com.denisgl.dto;

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
}
