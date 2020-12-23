package shop.dao;

//@AllArgsConstructor
//@Repository
public class AdminDao /*implements Dao<Admin>*/{
    /*private final EntityManager entityManager;



    @Override
    public Optional<Admin> findOne(long id) {
        return Optional.ofNullable(entityManager.find(Admin.class, id));
    }

    @Override
    public List<Admin> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Admin> query = criteriaBuilder.createQuery(Admin.class);

        Root<Admin> root = query.from(Admin.class);
        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Admin create(Admin admin) {
        try {
            entityManager.getTransaction().begin();
            if (admin.getId() == null){
                entityManager.persist(admin);
            } else {
                entityManager.merge(admin);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }
        return admin;
    }

    @Override
    public void delete(Admin admin) {
        executeInsideTransaction(entityManager -> entityManager.remove(admin));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }*/
}
