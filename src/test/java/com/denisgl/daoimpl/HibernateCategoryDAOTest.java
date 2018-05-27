package com.denisgl.daoimpl;

import com.denisgl.dao.ICategoryDAO;
import com.denisgl.dto.ICategory;
import com.denisgl.dtoimpl.HibernateCategory;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HibernateCategoryDAOTest {

    private static AnnotationConfigApplicationContext context;

    private static ICategoryDAO categoryDAO;

    private HibernateCategory hibernateCategory;

    @BeforeClass
    public static void init() {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.denisgl.config");
        context.refresh();
        String[] beanNamesForType = context.getBeanNamesForType(Object.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }
        categoryDAO = (ICategoryDAO) context.getBean("categoryDAO");
    }


    @Test
    public void getCategories() {
        hibernateCategory = new HibernateCategory();
        hibernateCategory.setId(1);
        hibernateCategory.setName("Laptop");
        hibernateCategory.setDescription("Lenovo v19221");
        hibernateCategory.setImageURL("https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c05509300.png");
        hibernateCategory.setActive(true);

        List<HibernateCategory> hibernateCategories = Arrays.asList(hibernateCategory);
        assertEquals(hibernateCategories, categoryDAO.getCategories());
    }

    @Test
    public void getCategory() {
        hibernateCategory = new HibernateCategory();
        hibernateCategory.setId(1);
        hibernateCategory.setName("Laptop");
        hibernateCategory.setDescription("Lenovo v19221");
        hibernateCategory.setImageURL("https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c05509300.png");
        hibernateCategory.setActive(true);

        ICategory category = categoryDAO.getCategory(1);
        assertEquals(hibernateCategory, category);
    }

    @Test
    @Ignore
    public void merge() {
        hibernateCategory = new HibernateCategory();
        hibernateCategory.setName("Laptop");
        hibernateCategory.setDescription("Lenovo v19221");
        hibernateCategory.setImageURL("https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c05509300.png");
        hibernateCategory.setActive(true);

        assertNotNull(categoryDAO.merge(hibernateCategory));
    }

    @Test
    @Ignore
    public void remove() {
        hibernateCategory = new HibernateCategory();
        hibernateCategory.setId(1);
        hibernateCategory.setName("Laptop");
        hibernateCategory.setDescription("Lenovo v19221");
        hibernateCategory.setImageURL("https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c05509300.png");
        hibernateCategory.setActive(true);

        categoryDAO.remove(hibernateCategory);
    }
}
