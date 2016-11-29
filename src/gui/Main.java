package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Main {

    private JFrame frame;
    private JTextField server_address_text_field;
    private JTextField account_input_text_field;
    private JTextField password_input_text_field;
    private String TEST_SERVER_CONNECTION = "测试服务器联通...\n";


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    private Main() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        String DEFAULT_SERVER_ADDRESS = "172.20.1.1";
        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("iNode Fucker Ver1.0");
        frame.setBounds(100, 100, 487, 433);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel console_pane = new JPanel();
        frame.getContentPane().add(console_pane, BorderLayout.SOUTH);
        console_pane.setLayout(new BorderLayout(5, 5));

        JTextArea console_content_text_area = new JTextArea();
        console_content_text_area.setEditable(false);
        console_content_text_area.setText(TEST_SERVER_CONNECTION);
        boolean is_connected = action.Network.isReachIp(DEFAULT_SERVER_ADDRESS);
        if (is_connected) {
            console_content_text_area.append("iNode服务器(" + DEFAULT_SERVER_ADDRESS + ")连接正常!\n");
        } else {
            console_content_text_area.append("iNode服务器(" + DEFAULT_SERVER_ADDRESS + ")连接失败!\n");
        }
        console_content_text_area.setRows(10);
        console_pane.add(console_content_text_area, BorderLayout.NORTH);

        JPanel server_ip_pane = new JPanel();
        frame.getContentPane().add(server_ip_pane, BorderLayout.NORTH);
        FlowLayout fl_server_ip_pane = new FlowLayout(FlowLayout.CENTER, 5, 10);
        fl_server_ip_pane.setAlignOnBaseline(true);
        server_ip_pane.setLayout(fl_server_ip_pane);

        JLabel inode_label_1 = new JLabel("iNode 认证服务器地址");
        server_ip_pane.add(inode_label_1);

        server_address_text_field = new JTextField();
        server_address_text_field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String serverIpAddress = server_address_text_field.getText();
                boolean is_connected = action.Network.isReachIp(serverIpAddress);
                console_content_text_area.setText(TEST_SERVER_CONNECTION);
                if (is_connected) {
                    console_content_text_area.append("iNode服务器(" + serverIpAddress + ")连接正常!\n");
                } else {
                    console_content_text_area.append("iNode服务器(" + serverIpAddress + ")连接失败!\n");
                }
            }
        });
        server_address_text_field.setText("172.20.1.1");
        server_ip_pane.add(server_address_text_field);
        server_address_text_field.setColumns(10);

        JLabel inode_label_2 = new JLabel("（青岛大学默认为"+DEFAULT_SERVER_ADDRESS+"）");
        inode_label_2.setHorizontalAlignment(SwingConstants.TRAILING);
        inode_label_2.setVerticalAlignment(SwingConstants.TOP);
        server_ip_pane.add(inode_label_2);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JLayeredPane has_account_tabbed_pane = new JLayeredPane();
        has_account_tabbed_pane.setToolTipText("");
        tabbedPane.addTab("已有账户登陆", null, has_account_tabbed_pane, null);
        has_account_tabbed_pane.setLayout(null);

        JLabel account_label = new JLabel("账号：");
        account_label.setBounds(41, 29, 61, 16);
        has_account_tabbed_pane.add(account_label);

        JLabel password_label = new JLabel("密码：");
        password_label.setBounds(41, 57, 61, 16);
        has_account_tabbed_pane.add(password_label);

        account_input_text_field = new JTextField();
        account_input_text_field.setBounds(82, 24, 361, 26);
        has_account_tabbed_pane.add(account_input_text_field);
        account_input_text_field.setColumns(10);

        password_input_text_field = new JPasswordField();
        password_input_text_field.setColumns(10);
        password_input_text_field.setBounds(82, 52, 361, 26);
        has_account_tabbed_pane.add(password_input_text_field);

        JButton has_account_login_button = new JButton("上线");
        has_account_login_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String account = account_input_text_field.getText();
                String password = password_input_text_field.getText();
                action.MainAction.login(account, password);

                console_content_text_area.append("正在尝试通过 " + account + " 登陆...\n");
            }
        });
        has_account_login_button.setBounds(68, 90, 117, 29);
        has_account_tabbed_pane.add(has_account_login_button);

        JButton has_account_logout_button = new JButton("下线");
        has_account_logout_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        has_account_logout_button.setBounds(197, 90, 117, 29);
        has_account_tabbed_pane.add(has_account_logout_button);

        JButton has_account_reset_password_button = new JButton("前往修改密码");
        has_account_reset_password_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        has_account_reset_password_button.setBounds(326, 90, 117, 29);
        has_account_tabbed_pane.add(has_account_reset_password_button);

        JLabel tips_label = new JLabel("忘记密码请联系网络中心");
        tips_label.setBounds(307, 131, 153, 16);
        has_account_tabbed_pane.add(tips_label);

        JLayeredPane no_account_tabbed_pane = new JLayeredPane();
        tabbedPane.addTab("尚无账户登陆", null, no_account_tabbed_pane, null);

        JLabel label = new JLabel("自动生成账户:");
        label.setBounds(6, 6, 102, 16);
        no_account_tabbed_pane.add(label);

        JButton no_account_login_button = new JButton("上线");
        no_account_login_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        no_account_login_button.setBounds(68, 90, 117, 29);
        no_account_tabbed_pane.add(no_account_login_button);

        JButton no_account_logout_button = new JButton("下线");
        no_account_logout_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        no_account_logout_button.setBounds(197, 90, 117, 29);
        no_account_tabbed_pane.add(no_account_logout_button);

        JButton no_account_change_account_button = new JButton("换一个账户");
        no_account_change_account_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        no_account_change_account_button.setBounds(326, 90, 117, 29);
        no_account_tabbed_pane.add(no_account_change_account_button);

        JLabel label_1 = new JLabel("当前账户：");
        label_1.setBounds(149, 48, 72, 16);
        no_account_tabbed_pane.add(label_1);

        JLabel label_2 = new JLabel("201440703000");
        label_2.setBounds(223, 48, 102, 16);
        no_account_tabbed_pane.add(label_2);


    }
}
