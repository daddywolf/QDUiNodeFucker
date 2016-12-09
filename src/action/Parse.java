package action;

/**
 * Created by jiangzhipeng on 2016/12/8.
 */
public class Parse {
    protected String parseGetHeartbeatCyc(String result) {
        String heart = "beatCyc\" value=\"";
        System.out.println("Index Of HeartBeatCyc : " + result.indexOf(heart));
        System.out.println("SubString of HeartBeatCyc" + result.substring(result.indexOf(heart), result.indexOf(heart) + 22));
        String subString1 = result.substring(result.indexOf(heart), result.indexOf(heart) + 22);
        return subString1.substring(subString1.length() - 6, subString1.length());

    }

    protected String parseGetSerialNo(String result) {
        String serialNo = "serialNo";
        System.out.println("Index Of serialNo : " + result.indexOf(serialNo));
        System.out.println("SubString of serialNo" + result.substring(result.indexOf(serialNo), result.indexOf(serialNo) + 23));
        String subString2 = result.substring(result.indexOf(serialNo), result.indexOf(serialNo) + 23);
        return subString2.substring(subString2.length() - 6, subString2.length()).replace("\"", "").replace(" ", "");
    }
}
