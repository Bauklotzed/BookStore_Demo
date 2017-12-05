package test.exception;

/**
 * Created by Zed on 2017/11/29.
 */
public class demo {

    public void tg() throws Exception{
        System.out.println(1/0);
    }

    public static void main(String[] args){
        demo d = new demo();
        try{
            d.tg();
        }catch (Exception e){
            System.out.println("no");
        }
    }

}
