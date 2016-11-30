package action;

import javax.swing.*;

/**
 * Created by jiangzhipeng on 2016/11/29.
 */
public class Connection {
    private static final String TEST_SERVER_CONNECTION = "测试服务器联通...\n";

    public static boolean testConnection(JTextArea console_content_text_area, String IPaddress) {
        boolean flag = false;
        Thread thread = new Thread() {
            public void run() {
                console_content_text_area.setText(TEST_SERVER_CONNECTION);
                boolean is_connected = action.Network.isReachIp(IPaddress);
                if (is_connected) {
                    console_content_text_area.append("iNode服务器(" + IPaddress + ")连接正常!\n");
                } else {
                    console_content_text_area.append("iNode服务器(" + IPaddress + ")连接失败!\n");
                }
                console_content_text_area.setCaretPosition(console_content_text_area.getText().length());
            }
        };
        thread.start();
        return flag;
    }
}
