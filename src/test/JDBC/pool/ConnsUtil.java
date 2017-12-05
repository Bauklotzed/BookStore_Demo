package test.JDBC.pool;

import test.JDBC.ConnFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Created by Zed on 2017/11/23.
 * Connection池
 * http://blog.csdn.net/qq_26525215/article/details/52173862
 */

public class ConnsUtil {

    //声明一个池
    private static List<Connection> pool = new ArrayList<Connection>();

    //声明池中的Connection对象个数
    private static final int NUM = 3;

    static{
        try{
            Properties p = new Properties();
            p.load(ConnFactory.class.getResourceAsStream("jdbc.properties"));
            String driver = p.getProperty("driver");
            String url = p.getProperty("url");
            String user = p.getProperty("username");
            String password = p.getProperty("password");

            Class.forName(driver);
            for(int i=0; i<NUM; i++){
                Connection conn = new MyConnection(DriverManager.getConnection(url, user, password));
                //加入池中
                pool.add(conn);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static synchronized Connection getConnection() throws InterruptedException{
        if(pool.size() <= 0){
            Thread.sleep(100);
            return getConnection();
        }
        return pool.remove(0);
    }

    public static void back(Connection conn){
        System.out.println("还回来一个Connection连接...");
        pool.add(conn);
    }

    //装饰模式
    static class MyConnection implements Connection{

        //封装一个Connection
        private Connection conn;

        public MyConnection(Connection conn){
            this.conn = conn;
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return conn.unwrap(iface);
        }

        @Override
        public void rollback() throws SQLException {
            conn.rollback();
        }

        @Override
        public Statement createStatement() throws SQLException {
            return conn.createStatement();
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            conn.setAutoCommit(autoCommit);
        }

        @Override
        public void commit() throws SQLException {
            conn.commit();
        }

        @Override
        public void close() throws SQLException {
            pool.add(this);
        }

        //------------------------------------
        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return false;
        }

        @Override
        public void abort(Executor executor) throws SQLException {

        }

        @Override
        public void clearWarnings() throws SQLException {

        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return null;
        }

        @Override
        public Blob createBlob() throws SQLException {
            return null;
        }

        @Override
        public Clob createClob() throws SQLException {
            return null;
        }

        @Override
        public NClob createNClob() throws SQLException {
            return null;
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return null;
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return null;
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return null;
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return null;
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return false;
        }

        @Override
        public String getCatalog() throws SQLException {
            return null;
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return null;
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return null;
        }

        @Override
        public int getHoldability() throws SQLException {
            return 0;
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return null;
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return 0;
        }

        @Override
        public String getSchema() throws SQLException {
            return null;
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return 0;
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return null;
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return null;
        }

        @Override
        public boolean isClosed() throws SQLException {
            return false;
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return false;
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return false;
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return null;
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return null;
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return null;
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return null;
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {

        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {

        }

        @Override
        public void setCatalog(String catalog) throws SQLException {

        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {

        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {

        }

        @Override
        public void setHoldability(int holdability) throws SQLException {

        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {

        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return null;
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return null;
        }

        @Override
        public void setSchema(String schema) throws SQLException {

        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {

        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {

        }
    }


}
























