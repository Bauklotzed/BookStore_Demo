package test.JDBC.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Zed on 2017/11/24.
 */
public class Client {

    public static void main(String[] args){

        //被代理的对象
        final IRenter r = new Renter();

        //这个obj是代理后的对象
        Object obj = Proxy.newProxyInstance(Client.class.getClassLoader(),
                new Class[]{IRenter.class},
                new InvocationHandler() {
                    @Override
                    //proxy是代理后的对象(等价于返回的obj),method就是类反射中的方法对象,args是执行method方法所需的参数
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return null;
                    }
                }
        );

    }

}
