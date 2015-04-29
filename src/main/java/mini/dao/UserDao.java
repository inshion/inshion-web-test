package mini.dao;

import mini.model.User;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;

import java.util.List;

/**
 * @author ash
 */
@DB(table = "user")
public interface UserDao {

    @SQL("insert into #table(name, money) values(:name, :money)")
    public int addUser(User user);

    @SQL("select id, name, money from #table")
    public List<User> getUsers();

}
