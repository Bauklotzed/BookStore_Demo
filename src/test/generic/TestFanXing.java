package test.generic;

import test.generic.FanXing;

/**
 * Created by Zed on 2017/11/23.
 */
public class TestFanXing {

    public static void main(String[] args){
        FanXing<Integer> intob = new FanXing<Integer>(88);
        intob.showType();
        int i = intob.getOb();
        System.out.println("value=" + i);
        System.out.println("--------------------------------------");
        FanXing<String> strob = new FanXing<String>("Hello Gen!");
        strob.showType();
        String s = strob.getOb();
        System.out.println("value=" + s);
    }

}
