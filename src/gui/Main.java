package gui;

import action.MainAction;
import config.ConfigProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {

    private JFrame frame;
    private JTextField server_address_text_field;
    private JTextField account_input_text_field;
    private JTextField password_input_text_field;
    private ConfigProperties configProperties = new ConfigProperties();
    private final String DEFAULT_SERVER_ADDRESS = configProperties.getConfigFromProperties("DEFAULT_SERVER_ADDRESS");

    /**
     * Create the application.
     */
    private Main() {
        initialize();

    }

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
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("iNode Fucker Ver1.0");
        frame.setBounds(100, 100, 487, 433);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane console_pane = new JScrollPane();
        console_pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        frame.getContentPane().add(console_pane, BorderLayout.SOUTH);

        Box verticalBox = Box.createVerticalBox();
        console_pane.setViewportView(verticalBox);

        JTextArea console_content_text_area = new JTextArea();
        console_content_text_area.setAlignmentX(Component.LEFT_ALIGNMENT);
        verticalBox.add(console_content_text_area);
        console_content_text_area.setEditable(false);

        action.Connection.testConnection(console_content_text_area, DEFAULT_SERVER_ADDRESS);

        console_content_text_area.setRows(10);

        JPanel server_ip_pane = new JPanel();
        frame.getContentPane().add(server_ip_pane, BorderLayout.NORTH);
        FlowLayout fl_server_ip_pane = new FlowLayout(FlowLayout.CENTER, 5, 10);
        fl_server_ip_pane.setAlignOnBaseline(true);
        server_ip_pane.setLayout(fl_server_ip_pane);

        JLabel inode_label_1 = new JLabel("iNode 认证服务器地址");
        server_ip_pane.add(inode_label_1);

        server_address_text_field = new JTextField();
        server_address_text_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String serverIpAddress = server_address_text_field.getText();
                    action.Connection.testConnection(console_content_text_area, serverIpAddress);
                    account_input_text_field.requestFocus();
                }
            }
        });
        server_address_text_field.setText(DEFAULT_SERVER_ADDRESS);
        server_ip_pane.add(server_address_text_field);
        server_address_text_field.setColumns(10);

        JLabel inode_label_2 = new JLabel("（青岛大学默认为" + DEFAULT_SERVER_ADDRESS + "）");
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

        JButton has_account_login_button = new JButton("上线");
        has_account_login_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String account = account_input_text_field.getText();
                String password = password_input_text_field.getText();
                action.MainAction.login(console_content_text_area, account, password);
            }
        });
        has_account_login_button.setBounds(68, 90, 117, 29);
        has_account_tabbed_pane.add(has_account_login_button);

        account_input_text_field = new JTextField();
        account_input_text_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    password_input_text_field.requestFocus();
                }
            }
        });
        account_input_text_field.setBounds(82, 24, 361, 26);
        has_account_tabbed_pane.add(account_input_text_field);
        account_input_text_field.setColumns(10);

        password_input_text_field = new JPasswordField();
        password_input_text_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String account = account_input_text_field.getText();
                    String password = password_input_text_field.getText();
                    int validAccount = action.MainAction.login(console_content_text_area, account, password);
                    if (validAccount == 1) {
                        account_input_text_field.requestFocus();
                    } else if (validAccount == 2) {
                        password_input_text_field.requestFocus();
                    } else {
                        System.out.println("success");
                    }
                }
            }
        });
        password_input_text_field.setColumns(10);
        password_input_text_field.setBounds(82, 52, 361, 26);
        has_account_tabbed_pane.add(password_input_text_field);

        JButton has_account_logout_button = new JButton("下线");
        has_account_logout_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action.MainAction.logout(console_content_text_area);
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

        JLabel auto_generate_account_label = new JLabel("自动生成账户:");
        auto_generate_account_label.setBounds(6, 6, 102, 16);
        no_account_tabbed_pane.add(auto_generate_account_label);

        JLabel current_account_label = new JLabel("201440703000");
        current_account_label.setBounds(223, 48, 102, 16);
        MainAction ma = new MainAction();
        current_account_label.setText(ma.generateAccount(console_content_text_area));
        no_account_tabbed_pane.add(current_account_label);

        JButton no_account_login_button = new JButton("上线");
        no_account_login_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String account = current_account_label.getText();
                String password = "123456";
                action.MainAction.login(console_content_text_area, account, password);
            }
        });
        no_account_login_button.setBounds(68, 90, 117, 29);
        no_account_tabbed_pane.add(no_account_login_button);

        JButton no_account_logout_button = new JButton("下线");
        no_account_logout_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action.MainAction.logout(console_content_text_area);
            }
        });
        no_account_logout_button.setBounds(197, 90, 117, 29);
        no_account_tabbed_pane.add(no_account_logout_button);

        JButton no_account_change_account_button = new JButton("换一个账户");
        no_account_change_account_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainAction.changeAccount(console_content_text_area, current_account_label);
            }
        });
        no_account_change_account_button.setBounds(326, 90, 117, 29);
        no_account_tabbed_pane.add(no_account_change_account_button);

        JLabel account_label_1 = new JLabel("当前账户：");
        account_label_1.setBounds(149, 48, 72, 16);
        no_account_tabbed_pane.add(account_label_1);

    }
}
