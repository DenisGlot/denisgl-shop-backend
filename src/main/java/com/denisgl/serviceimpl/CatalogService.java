package com.denisgl.serviceimpl;

import com.denisgl.dao.ICategoryDAO;
import com.denisgl.dao.IProductDAO;
import com.denisgl.dto.ICategory;
import com.denisgl.dto.IProduct;
import com.denisgl.filter.CategoryFilter;
import com.denisgl.filter.ProductFilter;
import com.denisgl.filter.ProductFilterSQL;
import com.denisgl.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService implements ICatalogService {

    @Autowired
    private ICategoryDAO categoryDAO;

    @Autowired
    private IProductDAO productDAO;

    @Override
    public List<ICategory> getCategories(CategoryFilter filter) {
        return categoryDAO.getCategories(filter);
    }

    @Override
    public ICategory getCategory(int id) {
        return categoryDAO.getCategory(id);
    }


    @Override
    public List<IProduct> getProducts(ProductFilter filter) {
        return productDAO.getProducts(filter);
    }

    @Override
    public List<IProduct> getProducts(ProductFilterSQL filter) {
        return productDAO.getProducts(filter);
    }

    @Override
    public IProduct getProduct(int id) {
        return productDAO.getProduct(id);
    }

    @Override
    public IProduct saveProduct(IProduct product) {
        return productDAO.merge(product);
    }

    @Override
    public void removeProduct(IProduct product) {
        productDAO.remove(product);
    }

}
