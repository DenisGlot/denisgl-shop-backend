package com.denisgl.dao;

import com.denisgl.dto.ICategory;

import java.util.List;

public interface ICategoryDAO {

    List getCategories();

    ICategory getCategory(int id);

    ICategory merge(ICategory hibernateCategory);

    void remove(ICategory category);
}
