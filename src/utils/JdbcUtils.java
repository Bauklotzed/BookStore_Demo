package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Zed on 2017/11/23.
 */
public class JdbcUtils {

    private static DataSource ds = null;

    static{
        ds = new ComboPooledDataSource();
    }

    public static DataSource getDataSource(){
        return ds;
    }

    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }

    public static void main(String[] args){
        try {
            Connection con = JdbcUtils.getConnection();
            System.out.println("ok");
            System.out.println(con);
        } catch (SQLException e) {
            System.out.println("错误");
        }
    }


}
