package action;

import javax.swing.*;

public class MainAction {


    public static void login(JTextArea console_content_text_area, String account, String password) {
        console_content_text_area.append("正在尝试通过 " + account + " 登陆...\n");
        System.out.println(account + ":" + password);
    }

}
