package test.dao;

import test.util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDAO {
    //通用的增删改方法
    public  int executeUpdate(String sql,Object...params) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if(params != null)
        {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1,params[i]);
            }
        }
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        JDBCUtil.release();
        return i;
    }
    //通用的查询方法
    public  <T> List<T> executeQuery(Class<T> clazz,String sql,Object...params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if(params != null)
        {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1,params[i]);
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<T> list = new ArrayList<T>();
        while (resultSet.next()) {
            T t=clazz.newInstance();
            for(int i=1;i<=columnCount;i++)
            {
                Object value = resultSet.getObject(i);
                if(value==null)
                {
                    value=0;
                }
                String columnName = metaData.getColumnLabel(i);
                Field declaredField = clazz.getDeclaredField(columnName);
                declaredField.setAccessible(true);
                declaredField.set(t,value);
            }
            list.add(t);
        }
        resultSet.close();
        preparedStatement.close();
        JDBCUtil.release();
        return list;
    }
    public  <T> T executeQueryBean(Class<T>clazz,String sql,Object...params) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<T> ts = this.executeQuery(clazz, sql, params);
        if(ts.size()>0)
            return ts.get(0);
        return null;
    }
}
