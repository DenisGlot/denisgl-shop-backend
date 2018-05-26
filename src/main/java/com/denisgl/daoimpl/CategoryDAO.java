package com.denisgl.daoimpl;

import com.denisgl.dao.ICategoryDAO;
import com.denisgl.dto.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("categoryDAO")
public class CategoryDAO implements ICategoryDAO {

    private static List<Category> list = new ArrayList<Category>();

    static {
        Category category = new Category();
        category.setId(1);
        category.setName("Laptop");
        category.setDescription("Lenovo v19221");
        category.setImageURL("https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c05509300.png");
        category.setActive(true);
        list.add(category);

        Category category2 = new Category();
        category2.setId(2);
        category2.setName("Mobile");
        category2.setDescription("Lenovo v19221");
        category2.setImageURL("https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c05509300.png");
        category2.setActive(true);
        list.add(category2);

        Category category3 = new Category();
        category3.setId(3);
        category3.setName("Computer");
        category3.setDescription("Lenovo v19221");
        category3.setImageURL("https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c05509300.png");
        category3.setActive(true);
        list.add(category3);
    }

    @Override
    public List<Category> getCategories() {
        return list;
    }

    @Override
    public Category getCategory(int id) {
        return list.stream().filter((i) -> i.getId() == id).findFirst().get();
    }
}
