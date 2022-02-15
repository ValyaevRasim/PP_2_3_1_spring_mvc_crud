package web.dao;

import org.springframework.stereotype.Repository;
import web.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        List<User> list = query.getResultList();
        return list;
    }

    @Transactional
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Transactional
    @Override
    public void deleteUserById(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}
