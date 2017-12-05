package dao.impl;

import dao.CategoryDao;
import domain.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Zed on 2017/11/23.
 */
public class CategoryDaoImpl implements CategoryDao{

    @Override
    public void add(Category category) {
        try{
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "insert into category(id,name,description) values(?,?,?)";
            Object params[] = {category.getId(), category.getName(), category.getDescription()};
            runner.update(sql, params);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public Category find(String id) {
        try{
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from category where id = ?";
            //return (Category) runner.query(sql, id, new BeanHandler(Category.class));//已弃用
            //文档:http://commons.apache.org/proper/commons-dbutils/apidocs/org/apache/commons/dbutils/QueryRunner.html#QueryRunner--
            //public <T> T query(String sql,ResultSetHandler<T> rsh,Object... params)throws SQLException
            //rsh - The handler used to create the result object from the ResultSet.
            return (Category) runner.query(sql, new BeanHandler(Category.class), id);
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> getAll() {
        try{
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from category";
            return (List<Category>) runner.query(sql, new BeanListHandler(Category.class));
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        CategoryDao categoryDao = new CategoryDaoImpl();
        Category category = categoryDao.find("1");
        System.out.println(category.getDescription());
    }

}
