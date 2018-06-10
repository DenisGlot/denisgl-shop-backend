package com.denisgl.daoimpl;

import com.denisgl.dao.ICategoryDAO;
import com.denisgl.dto.ICategory;
import com.denisgl.dtoimpl.HibernateCategory;
import com.denisgl.filter.CategoryFilter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("categoryDAO")
@Transactional
public class HibernateCategoryDAO extends HibernateDAO<HibernateCategory> implements ICategoryDAO {

    @Override
    public List<ICategory> getCategories(CategoryFilter filter) {
        StringBuilder where = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (filter.getActive() != null) {
            where.append("AND active = :active\n");
            params.put("active", filter.getActive());
        }

        String sqlQuery = "SELECT hc FROM HibernateCategory hc\n" +
                "WHERE 1=1 " + where;

        return getSession()
                .createQuery(sqlQuery, ICategory.class)
                .setProperties(params)
                .list();
    }

    @Override
    public ICategory getCategory(int id) {
        return findById(id);
    }

    @Override
    public ICategory merge(ICategory hibernateCategory) {
        return (HibernateCategory) getSession().merge(hibernateCategory);
    }

    @Override
    public void remove(ICategory category) {
        getSession().delete(category);
    }

}