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
        int firstResult = filter.getFirstResult();
        if (firstResult > 0) {
            query.setFirstResult(firstResult);
        }

        int maxResult = filter.getMaxResult();
        if (maxResult > 0) {
            query.setMaxResults(maxResult);
        }
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
