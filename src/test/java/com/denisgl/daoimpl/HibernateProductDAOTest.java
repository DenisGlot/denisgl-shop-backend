package com.denisgl.daoimpl;

import com.denisgl.dto.ICategory;
import com.denisgl.dto.IProduct;
import com.denisgl.dtoimpl.HibernateProduct;
import com.denisgl.filter.ProductFilter;
import com.denisgl.service.ICatalogService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class HibernateProductDAOTest {

    private static AnnotationConfigApplicationContext context;

    private static ICatalogService catalogService;

    @BeforeClass
    public static void init() {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.denisgl.config");
        context.refresh();
        //it has to be an interface
        catalogService = (ICatalogService) context.getBean("catalogService");
    }

    @Test
    public void getProductsSize() {
        List<IProduct> products = catalogService.getProducts(new ProductFilter());
        assertTrue(products.size() > 0);
    }

    @Test
    public void getProductByFilterMaxResult() {
        ProductFilter filter = new ProductFilter();
        filter.setPageSize(1);
        List<IProduct> products = catalogService.getProducts(filter);
        assertEquals(1, products.size());
    }

    /**
     * In order to work this method set product.getCategory() from Lazy to Eager
     */
    @Test
    public void getProductByFilterCategory() {
        ProductFilter filter = new ProductFilter();
        ICategory category = catalogService.getCategory(1);
        filter.setCategory(category);
        List<IProduct> products = catalogService.getProducts(filter);
        products.forEach(product -> assertEquals(category, product.getCategory()));
    }

    @Test
    public void getProductByFilterActive() {
        ProductFilter filter = new ProductFilter();
        filter.setActive(Boolean.TRUE);
        List<IProduct> products = catalogService.getProducts(filter);
        products.forEach(product -> assertTrue(product.getActive()));
    }

    @Test
    public void getProduct() {
        assertEquals(1, catalogService.getProduct(1).getId());
    }

    @Test
    public void createAndDelete() {

        HibernateProduct hibernateProduct = new HibernateProduct();
        hibernateProduct.setName("mock");
        hibernateProduct.setBrand("mockBrand");
        hibernateProduct.setDescription("mock v19221");
        hibernateProduct.setActive(false);

        hibernateProduct = (HibernateProduct) catalogService.saveProduct(hibernateProduct);
        assertNotNull(hibernateProduct);

        catalogService.removeProduct(hibernateProduct);

        assertNull(catalogService.getProduct(hibernateProduct.getId()));

    }
}
