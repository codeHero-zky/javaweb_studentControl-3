package test.service;

import test.pojo.user;

import java.sql.SQLException;

public interface serviceUser {
    int regist(user u) throws SQLException;
    user findByuserId(String userId) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
