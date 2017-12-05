package test.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Zed on 2017/11/26.
 */
public class C3p0Demo {

    @Test
    public void C3p0Demo() throws SQLException {
        ComboPooledDataSource pool = new ComboPooledDataSource();
        pool.setUser("root");
        pool.setPassword("666666");
        pool.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/user?useUnicode=true&characterEncoding=utf-8");

        for(int i=0;i<20;i++){
            Connection con = pool.getConnection();
            System.out.println(i+":"+con);
            if(i%2 ==0){
                con.close();
            }
        }

    }

    @Test
    public void C3p0PropertyDemo() throws SQLException {
        ComboPooledDataSource pool = new ComboPooledDataSource();//空参，自动到classpath目录下面加载“c3p0-config.xml”配置文件---配置文件的存储位置和名称必须是这样，且使用“默认配置”
        //ComboPooledDataSource pool = new ComboPooledDataSource("demo");//加载“c3p0-config.xml”文件中定义的“demo”这个配置元素
        for(int i=0;i<25;i++){
            Connection con = pool.getConnection();
            System.out.println(i+":"+con.hashCode());
        }
    }

}
