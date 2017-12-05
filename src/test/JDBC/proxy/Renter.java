package test.JDBC.proxy;

/**
 * Created by Zed on 2017/11/24.
 */
public class Renter implements IRenter {

    @Override
    public void rent(int i) {
        System.out.println("给你"+i+"个房间，需要500元");
    }

    @Override
    public String say() {
        System.out.println("Renter:你好，我是房东,房子实际只要300元");
        return "房东的返回结果...";
    }
}
