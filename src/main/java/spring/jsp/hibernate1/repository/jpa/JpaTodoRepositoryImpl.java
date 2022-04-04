package spring.jsp.hibernate1.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.springframework.stereotype.Repository;
import spring.jsp.hibernate1.model.Todo;
import spring.jsp.hibernate1.repository.TodoRepository;

@Repository
public class JpaTodoRepositoryImpl implements TodoRepository {

    @PersistenceContext
    private EntityManager em;


    /**
     * Important: in the current version of this method, we load Owners with all their Pets and Visits while
     * we do not need Visits at all and we only need one property from the Pet objects (the 'name' property).
     * There are some ways to improve it such as:
     * - creating a Ligtweight class (example here: https://community.jboss.org/wiki/LightweightClass)
     */
    @SuppressWarnings("unchecked")
    public Collection<Todo> findByUserName(String userName) {
        Query query = this.em.createQuery("SELECT DISTINCT owner FROM Todo owner WHERE owner.userName LIKE :userName");
        query.setParameter("userName", userName + "%");
        return query.getResultList();
    }

    @Override
    public Todo findById(Long id) {

        Query query = this.em.createQuery("SELECT owner FROM Todo owner  WHERE owner.id =:id");
        query.setParameter("id", id);
        return (Todo) query.getSingleResult();
    }


    @Override
    public void save(Todo owner) {
        if (owner.getId() == null) {
            this.em.persist(owner);
        } else {
            this.em.merge(owner);
        }

    }

    @Override
    public void delete(Todo todo) {
        this.em.remove(todo);

    }

}