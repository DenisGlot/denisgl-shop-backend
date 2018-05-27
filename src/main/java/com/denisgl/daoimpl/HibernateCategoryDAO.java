package com.denisgl.daoimpl;

import com.denisgl.dao.ICategoryDAO;
import com.denisgl.dto.ICategory;
import com.denisgl.dtoimpl.HibernateCategory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("categoryDAO")
public class HibernateCategoryDAO implements ICategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<ICategory> getCategories() {
        return getSession()
                .createQuery("select hc from HibernateCategory hc")
                .list();
    }

    @Override
    public ICategory getCategory(int id) {
        return getSession().get(HibernateCategory.class, id);
    }

    @Override
    @Transactional
    public ICategory merge(ICategory hibernateCategory) {
        return (HibernateCategory) getSession().merge(hibernateCategory);
    }

    @Override
    public void remove(ICategory category) {
        getSession().delete(category);
    }

    private Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            return sessionFactory.openSession();
        }
    }
}




//    static {
//        HibernateCategory hibernateCategory = new HibernateCategory();
//        hibernateCategory.setId(1);
//        hibernateCategory.setName("Laptop");
//        hibernateCategory.setDescription("Lenovo v19221");
//        hibernateCategory.setImageURL("https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c05509300.png");
//        hibernateCategory.setActive(true);
//        list.add(hibernateCategory);
//
//        HibernateCategory hibernateCategory2 = new HibernateCategory();
//        hibernateCategory2.setId(2);
//        hibernateCategory2.setName("Mobile");
//        hibernateCategory2.setDescription("Lenovo v19221");
//        hibernateCategory2.setImageURL("https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c05509300.png");
//        hibernateCategory2.setActive(true);
//        list.add(hibernateCategory2);
//
//        HibernateCategory hibernateCategory3 = new HibernateCategory();
//        hibernateCategory3.setId(3);
//        hibernateCategory3.setName("Computer");
//        hibernateCategory3.setDescription("Lenovo v19221");
//        hibernateCategory3.setImageURL("https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c05509300.png");
//        hibernateCategory3.setActive(true);
//        list.add(hibernateCategory3);
//    }
