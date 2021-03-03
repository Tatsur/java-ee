package ru.geekbrains.persists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() throws Exception {
        if(countAllCategories() == 0) {
            try {
                ut.begin();
                saveOrUpdate(new Category(null, "category  1",
                        "Description of category 1", null));
                saveOrUpdate(new Category(null, "category  2",
                        "Description of category 2", 1l));
                saveOrUpdate(new Category(null, "category  3",
                        "Description of category 3", 2l));
                ut.commit();
            } catch (Exception e) {
                logger.error(e.toString());
                ut.rollback();
            }
        }
    }
    @Transactional
    public List<Category> findAllCategories() {
        return em.createNamedQuery("findAllCategories",Category.class).getResultList();
    }

    public Category findCategoryById(Long id) {
        return em.find(Category.class, id);
    }
    @Transactional
    public Long countAllCategories(){
        return em.createNamedQuery("countAllCategories",Long.class)
                .getSingleResult();
    }
    @Transactional
    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            em.persist(category);
        }
        em.merge(category);
    }
    @Transactional
    public void deleteCategoryById(Long id) {
        em.createNamedQuery("deleteCategoryById")
                .setParameter("id",id)
                .executeUpdate();
    }
}
