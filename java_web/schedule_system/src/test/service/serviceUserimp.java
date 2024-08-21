package test.service;

import test.dao.userimp;
import test.pojo.user;

import java.sql.SQLException;

public class serviceUserimp extends userimp implements serviceUser{
    private userimp user=new userimp();
    @Override
    public int regist(user u) throws SQLException {
        return user.insert(u);
    }

    @Override
    public user findByuserId(String userId) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
         return selectByuserId(userId);
    }

}
