package com.denisgl.dao;

import com.denisgl.dto.ICategory;
import com.denisgl.filter.CategoryFilter;

import java.util.List;

public interface ICategoryDAO {

    List<ICategory> getCategories(CategoryFilter filter);

    ICategory getCategory(int id);

    ICategory merge(ICategory hibernateCategory);

    void remove(ICategory category);
}
