package ir.comprehensive.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;


public abstract class BaseDao<T extends Serializable> {

    private Class<T> clazz;

    @PersistenceContext
    EntityManager entityManager;

    public final void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public final Query<T> getQuery(String q) {
        SessionFactory sessionFactory = entityManager.unwrap(SessionFactory.class);
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createQuery(q, clazz);
    }

    public final Session getSession() {
        SessionFactory sessionFactory = entityManager.unwrap(SessionFactory.class);
        return sessionFactory.getCurrentSession();
    }
}
