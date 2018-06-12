package com.denisgl.daoimpl;

import com.denisgl.filter.PagingFilter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;

@Repository("dao")
public abstract class HibernateDAO<E> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<E> persistentClass;

    @SuppressWarnings("unchecked")
    public HibernateDAO() {
        this.persistentClass = (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected E findById(int id) {
        return getSession().get(persistentClass, id);
    }

    protected void setPaging(Query query, PagingFilter filter) {
        int pageSize = filter.getPageSize();
        int pageNumber = filter.getPageNumber();

        if (pageSize > 0) {
            query.setMaxResults(pageSize);

            if (pageNumber > 0) {
                query.setFirstResult(pageNumber * pageSize);
            }
        }
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
