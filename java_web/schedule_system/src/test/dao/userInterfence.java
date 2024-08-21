package test.dao;

import test.pojo.student;
import test.pojo.user;

import java.sql.SQLException;
import java.util.List;

public interface userInterfence {
    List<user> selectAll() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
     user selectByuserId(String userId) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    int insert(user User) throws SQLException;
    int update(user User) throws SQLException;
    int delete(user User) throws SQLException;

}
