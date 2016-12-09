package action;

import bean.UserInfo;

import javax.swing.*;
import java.util.Random;

import static java.lang.Thread.sleep;

public class MainAction {

    public static int login(JTextArea console_content_text_area, String account, String password, String serverIP) {
        String login_account = UserInfo.getUsername();
        if (login_account != null) {
            logout(console_content_text_area, serverIP);
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (account.equals("")) {
            JOptionPane.showMessageDialog(console_content_text_area, "用户名不能为空", "请输入用户名", JOptionPane.ERROR_MESSAGE);
            return 1;
        } else if (password.equals("")) {
            JOptionPane.showMessageDialog(console_content_text_area, "密码不能为空", "请输入密码", JOptionPane.ERROR_MESSAGE);
            return 2;
        } else {
            console_content_text_area.append("正在尝试通过 " + account + " 登陆...\n");
            console_content_text_area.setCaretPosition(console_content_text_area.getText().length());
            UserInfo.setUsername(account);
            System.out.println(account + ":" + password);
            PostRequest pr = new PostRequest();
            String return_msg = pr.login(serverIP, account, password);
            console_content_text_area.append(return_msg);
            return 0;
        }
    }

    public static void logout(JTextArea console_content_text_area, String IP) {
        String account = null;
        account = UserInfo.getUsername();
        if (account == null) {
            console_content_text_area.append("您貌似还没有登录，请登陆后继续操作！\n");
        } else {
            console_content_text_area.append("正在尝试通过 " + account + " 下线...\n");
            System.out.println(account + "下线");
            UserInfo.setUsername(null);
            String return_msg = new PostRequest().logout(IP);
            console_content_text_area.append(account + return_msg);
        }
        console_content_text_area.setCaretPosition(console_content_text_area.getText().length());
    }

    public static void changeAccount(JTextArea console_content_text_area, JLabel current_account_label, String serverIP) {
        if (UserInfo.getUsername() != null) {
            action.MainAction.logout(console_content_text_area, serverIP);
        } else {
            MainAction ma = new MainAction();
            String generate = ma.generateAccount();
            current_account_label.setText(generate);
            console_content_text_area.append("当前账户更换为：" + generate + "\n");
        }
        console_content_text_area.setCaretPosition(console_content_text_area.getText().length());
    }

    public String generateAccount() {
        String generated;
        Random rand = new Random();
        generated = "2014";
        generated += "40703";
        generated += rand.nextInt(780);
        System.out.println(generated);
        return generated;
    }


}
