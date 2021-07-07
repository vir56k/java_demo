package cn.zyfvir.demo.business.dao;

import cn.zyfvir.demo.business.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class HelloRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    public int getCount() {
        int rowCount = jdbcOperations.queryForObject("select count(*) from t_actor", Integer.class);
        return rowCount;
    }

    public String getLastName() {
        String lastName = this.jdbcOperations.queryForObject(
            "select last_name from t_actor where id = ?",
            String.class, 1);
        return lastName;
    }

    public Actor getActor() {
        Actor actor = jdbcOperations.queryForObject(
            "select first_name, last_name from t_actor where id = ?",
            (resultSet, rowNum) -> {
                Actor newActor = new Actor();
                newActor.setFirstName(resultSet.getString("first_name"));
                newActor.setLastName(resultSet.getString("last_name"));
                return newActor;
            },
            1);
        return actor;
    }
}
