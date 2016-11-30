package action;

import bean.UserInfo;

import javax.swing.*;
import java.util.Random;

public class MainAction {

    public static int login(JTextArea console_content_text_area, String account, String password) {

        String login_account = UserInfo.getUsername();
        if (login_account != null) {
            logout(console_content_text_area);
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
            return 0;
        }
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

    public static void changeAccount(JTextArea console_content_text_area, JLabel current_account_label) {
        if (UserInfo.getUsername() != null) {
            action.MainAction.logout(console_content_text_area);
        } else {
            MainAction ma = new MainAction();
            String generate = ma.generateAccount(console_content_text_area);
            current_account_label.setText(generate);
            console_content_text_area.append("当前账户更换为：" + generate + "\n");
        }
        console_content_text_area.setCaretPosition(console_content_text_area.getText().length());
    }

    public String generateAccount(JTextArea console_content_text_area) {
        String generated = null;
        Random rand = new Random();
        generated = "201" + rand.nextInt(10);
        generated += "4070";
        generated += (int) (rand.nextDouble() * 10000);
        if (generated.length() != 12) {
            generateAccount(console_content_text_area);
        }
        System.out.println(generated);
        return generated;
    }


}
