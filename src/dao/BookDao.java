package dao;

import domain.Book;

import java.util.List;

/**
 * Created by Zed on 2017/11/26.
 */
public interface BookDao {

    public void add(Book book);

    public Book find(String id);

    public List<Book> getPageData(int startindex, int pagesize);

    public int getTotalRecord();

    public List<Book> getPageData(int startindex, int pagesize, String category_id);

    public int getTotalRecord(String category_id);

}
