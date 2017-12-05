package utils;

/**
 * Created by Zed on 2017/11/23.
 */
public class DaoFactory {

    private static final DaoFactory factory = new DaoFactory();
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return factory;
    }

    //http://blog.csdn.net/songkai320/article/details/51822497
    //http://blog.csdn.net/kaiwii/article/details/7405761
    //是否拥有泛型方法，与其所在的类是否泛型没有关系。要定义泛型方法，只需将泛型参数列表置于返回值前。

    public <T> T createDao(String className, Class<T> clazz){
        try {
            T t = (T)Class.forName(className).newInstance();
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
