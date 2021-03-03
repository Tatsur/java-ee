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
import java.math.BigDecimal;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext (unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() throws Exception {

        logger.info(countAll().toString());
        if(countAll() == 0) {
            try {
                ut.begin();
                List<Category> categories = findAllCategories();
                saveOrUpdate(new Product(null, "Product  1",
                        "Description of product 1", categories.get(0), new BigDecimal(100)));
                saveOrUpdate(new Product(null, "Product  2",
                        "Description of product 2", categories.get(1), new BigDecimal(200)));
                saveOrUpdate(new Product(null, "Product  3",
                        "Description of product 3", categories.get(2), new BigDecimal(300)));

                ut.commit();
            } catch (Exception e) {
                logger.error("",e);
                ut.rollback();
            }
        }
    }
    @Transactional
    public List<Product> findAll() {
        return em.createNamedQuery("findAll",Product.class)
                .getResultList();
    }
    @Transactional
    public List<Category> findAllCategories() {
        return em.createNamedQuery("findAllCategories",Category.class).getResultList();
    }

    public Product findById(Long id) {
        return em.find(Product.class,id);
    }
    @Transactional
    public Long countAll(){
        return em.createNamedQuery("countAll",Long.class)
                .getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
    }
    @Transactional
    public void deleteById(Long id) {
        em.createNamedQuery("deleteById")
                .setParameter("id",id)
                .executeUpdate();
    }
}
