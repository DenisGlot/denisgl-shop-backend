package com.denisgl.daoimpl;

import com.denisgl.dao.IProductDAO;
import com.denisgl.dto.IProduct;
import com.denisgl.dtoimpl.HibernateProduct;
import com.denisgl.filter.ProductFilter;
import com.denisgl.filter.ProductFilterSQL;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("productDAO")
@Transactional
public class HibernateProductDAO extends HibernateDAO<HibernateProduct> implements IProductDAO {

    @Override
    public List<IProduct> getProducts(ProductFilter filter) {
        StringBuilder where = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (filter.getCategory() != null) {
            where.append("AND category = :category\n");
            params.put("category", filter.getCategory());
        }

        if (filter.getActive() != null) {
            where.append("AND active = :active\n");
            params.put("active", filter.getActive());
        }

        String sqlQuery = "SELECT hc FROM HibernateProduct hc\n" +
                "WHERE 1=1 " + where;

        Query<IProduct> query = getSession()
                .createQuery(sqlQuery, IProduct.class)
                .setProperties(params);

        setPaging(query, filter);

        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<IProduct> getProducts(ProductFilterSQL filter) {
        StringBuilder where = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (filter.getCategoryId() != null) {
            where.append("AND category_id = :categoryId\n");
            params.put("categoryId", filter.getCategoryId());
        }

        if (filter.getActive() != null) {
            where.append("AND active = :active\n");
            params.put("active", filter.getActive());
        }

        String sqlQuery = "SELECT {p.*} FROM product p\n" +
                "WHERE 1=1 " + where;

        NativeQuery nativeQuery = getSession()
                .createNativeQuery(sqlQuery)
                .addEntity("p", HibernateProduct.class)
                .setProperties(params);

        setPaging(nativeQuery, filter);

        return nativeQuery.list();
    }

    @Override
    public IProduct getProduct(int id) {
        return findById(id);
    }

    @Override
    public IProduct merge(IProduct product) {
        return (IProduct) getSession().merge(product);
    }

    @Override
    public void remove(IProduct product) {
        getSession().delete(product);
    }

}
