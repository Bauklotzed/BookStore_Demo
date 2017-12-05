package test.generic;

/**
 * Created by Zed on 2017/11/23.
 * 泛型
 */
public class FanXing<T> {

    private T ob;//定义泛型成员变量

    public FanXing(T ob){
        this.ob = ob;
    }

    public T getOb(){
        return ob;
    }

    public void setOb(){
        this.ob = ob;
    }

    public void showType(){
        System.out.println("T的实际类型是:" + ob.getClass().getName());
    }

}
