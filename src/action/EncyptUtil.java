package action;

/**
 * Created by jiangzhipeng on 2016/12/1.
 */
public class EncyptUtil {
    protected static String getBase64(String in) {
        return (new sun.misc.BASE64Encoder().encode(in.getBytes()));
    }
}
