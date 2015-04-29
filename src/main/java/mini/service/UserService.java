package mini.service;

import mini.dao.UserDao;
import mini.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ash
 */
@Component
public class UserService {

    @Autowired
    UserDao userDao;

    public void addUser(String name, long money) {
        User user = new User();
        user.setName(name);
        user.setMoney(money);
        userDao.addUser(user);
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }


}
