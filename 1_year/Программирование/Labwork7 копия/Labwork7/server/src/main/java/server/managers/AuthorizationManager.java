package server.managers;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import server.Main;
import server.dao.UserDAO;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class AuthorizationManager {
    public final SessionFactory sessionFactory;
    private final int LENGTH =10;
    private final String string;
    private final Logger logger = Main.logger;
    public AuthorizationManager(SessionFactory sessionFactory, String string){
        this.sessionFactory = sessionFactory;
        this.string = string;
    }
    public int userReg(String login, String password) throws SQLException{
        logger.info("Создание нового пользователя"+login);
        var salt = generateSalt();
        var passwordHash = generatePasswordHash(password, salt);
        var dao = new UserDAO();
        dao.setName(login);
        dao.setPasswordDigest(passwordHash);
        dao.setSalt(salt);
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(dao);
        session.getTransaction().commit();
        session.close();
        var newId = dao.getId();
        logger.info("Пользователь успешно создан");
        return newId;
    }

    public int userAuth(String login, String password) throws  SQLException{
        logger.info("Аутенфикация пользователя"+login);
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var query = session.createQuery("SELECT u FROM users u WHERE u.name= :name");
        query.setParameter("name",login);
        List<UserDAO> result = (List<UserDAO>) query.list();
        if(result.isEmpty()){
            logger.warn("Неправильный пароль для пользователя"+login);
            return 0;
        }
        var user = result.get(0);
        session.getTransaction().commit();
        session.close();
        var id = user.getId();
        var salt = user.getSalt();
        var expectedHashedPassowrd = user.getPasswordDigest();
        var actualHashedPassword = generatePasswordHash(password, salt);
        if(expectedHashedPassowrd.equals(actualHashedPassword)){;
            logger.info("Пользователь "+login+" аутенфикация с id#"+id);
            return id;
    }
logger.warn(
        "Неправильный пароль для пользователя "+login+
                ". Ожидалось "+expectedHashedPassowrd+"', получено'"+actualHashedPassword
);
        return 0;
}
private String generateSalt(){
    return RandomStringUtils.randomAlphanumeric(LENGTH);}
private String generatePasswordHash(String password, String salt){
    return Hashing.sha384().
            hashString(string+password+salt, StandardCharsets.UTF_8)
            .toString();
}}


