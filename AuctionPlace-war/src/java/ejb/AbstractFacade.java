package ejb;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Daniel Losvik
 * 
 * This class is the super class of all the enterprise java beans
 * and defines the 4 crud methods plus the find all method used to
 * store and query data from the database
 */


public abstract class AbstractFacade<T> {
    
    private Class<T> entityClass;
    
    public AbstractFacade() {
        
    }

    public AbstractFacade(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
