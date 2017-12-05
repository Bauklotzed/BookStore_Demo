package domain;

/**
 * Created by Zed on 2017/11/28.
 */
public class CartItem {

    private Book book;
    private int quantity;
    private double price;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public double getPrice() {
        return this.book.getPrice()*this.getQuantity();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
