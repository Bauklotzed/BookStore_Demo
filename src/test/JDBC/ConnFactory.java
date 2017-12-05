package test.JDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Zed on 2017/11/23.
 */
public class ConnFactory {

    private static Connection conn = null;

    static{
        //读取配置文件
        Properties p = new Properties();
        try {

            //http://blog.csdn.net/lmb55/article/details/50908462
            //p.load(ConnFactory.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            p.load(ConnFactory.class.getResourceAsStream("jdbc.properties"));

            String driver = p.getProperty("driver");
            String url = p.getProperty("url");
            String user = p.getProperty("username");
            String password = p.getProperty("password");

            Class.forName(driver);

            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            throw new RuntimeException("配置文件出现异常", e);
        }catch (ClassNotFoundException e){
            throw new RuntimeException("Driver.Class文件出现异常", e);
        }catch (SQLException e){
            throw new RuntimeException("数据库访问出现异常", e);
        }
    }

    public static Connection getConnection(){
        return conn;
    }

    public static void main(String[] args){
        System.out.println(getConnection());
    }

}
