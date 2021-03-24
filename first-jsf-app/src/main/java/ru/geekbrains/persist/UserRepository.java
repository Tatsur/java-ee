package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class UserRepository {

    private  final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    public UserRepository() {
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public boolean existsById(Long id) {
        return findById(id) != null;
    }

    @TransactionAttribute
    public User saveOrUpdate(User user) {
        if (user.getId() == 0) {
            em.persist(user);
            return user;
        }
        return em.merge(user);
    }
    @TransactionAttribute
    public void deleteById(Long id) {
        logger.info("Deleting user");
        try {
            User attached = findById(id);
            if (attached != null) {
                em.remove(attached);
            }
        } catch (Exception ex) {
            logger.error("Error with entity class", ex);
            throw new IllegalStateException(ex);
        }
    }

    @TransactionAttribute
    public List<User> getAllUsers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> from = query.from(User.class);
        from.fetch("roles", JoinType.LEFT);
        query.select(from).distinct(true);
        return em.createQuery(query).getResultList();
    }

    public long getCount() {
        return em.createQuery("select count(*) from User", Long.class)
                .getSingleResult();
    }
}
