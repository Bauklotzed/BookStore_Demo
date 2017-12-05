package test.JDBC.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Zed on 2017/11/23.
 */

public class TestPool {

    public static void main(String[] args){
        Connection conn = null;
        try {
            conn = ConnsUtil.getConnection();

            //true：sql命令的提交（commit）由驱动程序负责
            //false：sql命令的提交由应用程序负责，程序必须调用commit或者rollback方法
            conn.setAutoCommit(false);

            Statement st = conn.createStatement();
            String sql = "insert into user(username, password) values('haha', 'fadfasd')";
            st.execute(sql);
            sql = "insert into user(username, password) values('gafsds', 'svenrc')";
            st.execute(sql);
            new OneThread(1).start();
            new OneThread(2).start();
            new OneThread(3).start();
            new OneThread(4).start();
            new OneThread(5).start();

            System.out.println("主线程准备提交...");
            conn.commit();
            System.out.println("主线程提交完毕...");

        } catch (Exception e) {
            try {
                conn.rollback();
                System.out.println("主线程回滚了...");
            } catch (SQLException e1) {
                throw new RuntimeException("主线程事务回滚失败!", e1);
            }
        }finally{
            try{
                if(conn!=null){
                    conn.setAutoCommit(true);
                    conn.close();//如果要把close内部的功能换成还连接，就需要我们以后的技术来实现(装饰模式)
                    //ConnsUtil.back(conn);
                }
            }catch(SQLException e){
                throw new RuntimeException("主线程连接关闭失败!", e);
            }
        }
    }

}

class OneThread extends Thread{
    private int n;
    public OneThread(int n){
        this.n = n;
    }

    @Override
    public void run() {
        Connection conn = null;
        try {
            conn = ConnsUtil.getConnection();
            conn.setAutoCommit(false);

            Statement st = conn.createStatement();
            String sql = "insert into user(username, password) values('haha"+n+"', 'fadfasd')";
            st.execute(sql);
            sql = "insert into user(username, password) values('gafsds"+n+"', 'svenrc')";
            st.execute(sql);
            System.out.println("第"+n+"个线程准备提交...");
            conn.commit();
            System.out.println("第"+n+"个线程提交完毕...");

        } catch (Exception e) {
            try {
                conn.rollback();
                System.out.println("第"+n+"个线程回滚了...");
            } catch (SQLException e1) {
                throw new RuntimeException("第"+n+"个事务回滚失败!", e1);
            }
        }finally{
            try{
                if(conn!=null){
                    conn.setAutoCommit(true);
                    conn.close();//如果要把close内部的功能换成还连接，就需要我们以后的技术来实现(装饰模式)
                    //ConnsUtil.back(conn);
                }
            }catch(SQLException e){
                throw new RuntimeException("第"+n+"个连接关闭失败!", e);
            }
        }
    }
}
