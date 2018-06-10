package com.denisgl.daoimpl;

import com.denisgl.dao.ICategoryDAO;
import com.denisgl.dto.ICategory;
import com.denisgl.dto.IProduct;
import com.denisgl.dtoimpl.HibernateCategory;
import com.denisgl.filter.CategoryFilter;
import com.denisgl.filter.ProductFilter;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class HibernateCategoryDAOTest {

    private static AnnotationConfigApplicationContext context;

    private static ICategoryDAO categoryDAO;

    @BeforeClass
    public static void init() {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.denisgl.config");
        context.refresh();
        //it has to be an interface
        categoryDAO = (ICategoryDAO) context.getBean("categoryDAO");
    }


    @Test
    public void getCategoriesSize() {
        List categories = categoryDAO.getCategories(new CategoryFilter());
        assertTrue(categories.size() > 0);
    }

    @Test
    public void  getCategoriesByFilterActive() {
        CategoryFilter filter = new CategoryFilter();
        filter.setActive(Boolean.TRUE);
        List<ICategory> categories = categoryDAO.getCategories(filter);
        categories.forEach(category -> assertTrue(category.getActive()));
    }

    @Test
    public void getCategory() {
        ICategory category = categoryDAO.getCategory(1);
        assertEquals(1, category.getId());
    }

    @Test
    public void createAndDelete() {
        HibernateCategory hibernateCategory = new HibernateCategory();
        hibernateCategory.setName("Test");
        hibernateCategory.setDescription("Lenovo v19221");
        hibernateCategory.setImageURL("mock.png");
        hibernateCategory.setActive(false);

        hibernateCategory = (HibernateCategory) categoryDAO.merge(hibernateCategory);
        assertNotNull(hibernateCategory);

        categoryDAO.remove(hibernateCategory);

        assertNull(categoryDAO.getCategory(hibernateCategory.getId()));
    }
}
