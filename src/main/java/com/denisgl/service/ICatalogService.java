package com.denisgl.service;

import com.denisgl.dto.ICategory;
import com.denisgl.dto.IProduct;
import com.denisgl.filter.CategoryFilter;
import com.denisgl.filter.ProductFilter;
import com.denisgl.filter.ProductFilterSQL;

import java.util.List;

public interface ICatalogService {

    List<ICategory> getCategories();

    List<ICategory> getCategories(CategoryFilter filter);

    ICategory getCategory(int id);

    List<IProduct> getProducts();

    List<IProduct> getProducts(ProductFilter filter);

    List<IProduct> getProducts(ProductFilterSQL filter);

    IProduct getProduct(int id);

    IProduct saveProduct(IProduct product);

    void removeProduct(IProduct product);
}
