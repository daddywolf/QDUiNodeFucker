package action;

import bean.UserInfo;

import javax.swing.*;

public class MainAction {

    public static void login(JTextArea console_content_text_area, String account, String password) {
        console_content_text_area.append("正在尝试通过 " + account + " 登陆...\n");
        console_content_text_area.setCaretPosition(console_content_text_area.getText().length());
        UserInfo.setUsername(account);
        System.out.println(account + ":" + password);
    }

    public static void logout(JTextArea console_content_text_area) {
        String account = null;
        account = UserInfo.getUsername();
        if (account == null) {
            console_content_text_area.append("您貌似还没有登录，请登陆后继续操作！\n");
        } else {
            console_content_text_area.append("正在尝试通过 " + account + " 下线...\n");
            System.out.println(account + "下线");
            UserInfo.setUsername(null);
            console_content_text_area.append(account + "下线成功！\n");
        }
        console_content_text_area.setCaretPosition(console_content_text_area.getText().length());
    }

}
