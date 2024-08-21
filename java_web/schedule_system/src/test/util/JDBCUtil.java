package test.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
public class JDBCUtil {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    private  static DataSource dataSource;
    static
    {
        Properties props = new Properties();
        InputStream resourceAsStream = jdbc.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            props.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        //通过threadlocal获取链接
        Connection conn = threadLocal.get();
        //如果thread local中没有链接，则从连接池中获取一个链接，分配给threadlocal
        if (conn == null) {
            conn = dataSource.getConnection();
            threadLocal.set(conn);
        }
        return conn;

    }
    public static void release() throws SQLException {
        Connection con=threadLocal.get();
        if(con!=null)
        {
            threadLocal.remove();//移除thread local里的链接
            con.close();
        }
    }



}
