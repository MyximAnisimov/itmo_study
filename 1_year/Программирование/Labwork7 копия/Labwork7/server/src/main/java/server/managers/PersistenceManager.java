package server.managers;

import common.collections.Person;
import common.user.User;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import server.Main;
import server.dao.PersonDAO;
import server.dao.UserDAO;

import java.util.List;

public class PersistenceManager {
    private final SessionFactory sessionFactory;
    private final Logger logger = Main.logger;
    public PersistenceManager(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    public int add(User user, Person person) {
        logger.info("Добавление нового продукта " + person.getName());
        var dao = new PersonDAO(person);
        dao.setCreator(new UserDAO(user));
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(dao);
        session.getTransaction().commit();
        session.close();
        logger.info("Добавление человека успешно выполнено");
        var newId = dao.getId();
        logger.info("Новый id продукта это "+newId);
        return newId;
    }

    public void update(User user, Person person) {
        logger.info("Обновление продукта id#" + person.getID());
        var session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        var personDAO = session.get(PersonDAO.class, person.getID());
        personDAO.update(person);
        session.update(personDAO);

        session.getTransaction().commit();
        session.close();
        logger.info("Обновление продукта выполнено!");
    }

    public void clear(User user) {
        logger.info("Очищение продуктов пользователя id#" + user.getId() + " из базы данных.");

        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var query = session.createQuery("DELETE FROM person WHERE creator.id = :creator");
        query.setParameter("creator", user.getId());
        var deletedSize = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        logger.info("Удалено " + deletedSize + " продуктов.");
    }

    public int remove(User user, int id) {
        logger.info("Удаление продукта №" + id + " пользователя id#" + user.getId() + ".");

        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        var query = session.createQuery("DELETE FROM person WHERE creator.id = :creator AND id = :id");
        query.setParameter("creator", user.getId());
        query.setParameter("id", id);

        var deletedSize = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        logger.info("Удалено " + deletedSize + " продуктов.");

        return deletedSize;
    }

    public List<PersonDAO> loadPerson() {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        var cq = session.getCriteriaBuilder().createQuery(PersonDAO.class);
        var rootEntry = cq.from(PersonDAO.class);
        var all = cq.select(rootEntry);

        var result = session.createQuery(all).getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
