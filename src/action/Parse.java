package action;

/**
 * Created by jiangzhipeng on 2016/12/8.
 */
public class Parse {
    public String parseGetHeartbeatCyc(String result) {
        String heart = "beatCyc\" value=\"";
        System.out.println("------" + result.indexOf(heart));
        System.out.println("------" + result.substring(result.indexOf(heart), result.indexOf(heart) + 22));
        String subString1 = result.substring(result.indexOf(heart), result.indexOf(heart) + 22);

        return subString1.substring(subString1.length() - 6, subString1.length());

    }

    public String parseGetSerialNo(String result) {
        String serialNo = "serialNo";
        System.out.println("------" + result.indexOf(serialNo));
        System.out.println("------" + result.substring(result.indexOf(serialNo), result.indexOf(serialNo) + 22));
        String subString2 = result.substring(result.indexOf(serialNo), result.indexOf(serialNo) + 22);
        return subString2.substring(subString2.length() - 6, subString2.length()).replace("\"", "").replace(" ", "");
    }
}
