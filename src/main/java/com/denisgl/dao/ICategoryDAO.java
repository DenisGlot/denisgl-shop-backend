package com.denisgl.dao;

import com.denisgl.dto.Category;

import java.util.List;

public interface ICategoryDAO {

    List<Category> getCategories();

    Category getCategory(int id);
}
