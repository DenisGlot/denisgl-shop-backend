package com.denisgl.dao;

import com.denisgl.dto.IProduct;
import com.denisgl.filter.ProductFilter;
import com.denisgl.filter.ProductFilterSQL;

import java.util.List;

public interface IProductDAO {

    List<IProduct> getProducts(ProductFilter filter);

    List<IProduct> getProducts(ProductFilterSQL filter);

    IProduct getProduct(int id);

    IProduct merge(IProduct product);

    void remove(IProduct product);
}
