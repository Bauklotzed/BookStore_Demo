package utils;

import java.util.UUID;

/**
 * Created by Zed on 2017/11/28.
 */
public class WebUtils {

    public static String makeID(){
        return UUID.randomUUID().toString();
    }

}
