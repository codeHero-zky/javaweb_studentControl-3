package test.dao;

import test.pojo.student;
import test.pojo.user;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class userimp extends BaseDAO implements userInterfence{
    Scanner sc = new Scanner(System.in);
    @Override
    public List<user> selectAll() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql = "=select user_id userId,user_pwd userPwd from user";
        List<user> users = executeQuery(user.class, sql, null);
        return users;
    }

    @Override
    public  user selectByuserId(String userId) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql = "select user_id userId,user_pwd userPwd from user where user_id=?";
        user User = executeQueryBean(user.class, sql, userId);
        return User;
    }

    @Override
    public int insert(user User) throws SQLException {
        String sql="insert into user(user_id,user_pwd) values(?,?)";
        String user_id=User.getUserId();
        String user_pwd=User.getUserPwd();
        int i = executeUpdate(sql, user_id, user_pwd);
        return i;
    }

    @Override
    public int update(user User) throws SQLException {
        String sql = "update user set user_pwd=? where user_id=?";
        System.out.println("new newpwd:");
        String newpwd = sc.next();
        int i = executeUpdate(sql,newpwd,User.getUserId());
        return i;
    }

    @Override
    public int delete(user User) throws SQLException {
        String sql = "delete from user where user_id=?";
        String userId = User.getUserId();
        int i = executeUpdate(sql,userId);
        return i;
    }
}
