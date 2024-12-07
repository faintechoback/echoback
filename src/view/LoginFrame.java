package view;

//本部分拆分，录入密码，识别权限，登陆背景绘制
import javax.swing.*;
//import javax.swing.plaf.BorderUIResource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.UserDao;
import view.Mymodule.Mybutton;
import view.Mymodule.RoundedBorder;
import view.Mymodule.landbutton;
import view.Mymodule.Myjpanel;
import view.Mymodule.Myjpassword;
import view.Mymodule.Myjtextfield;

public class LoginFrame extends JFrame implements ActionListener {
    // 提前全局定义，方便后面获取
    Myjtextfield username;
    Myjpassword password;
    Myjtextfield bossload;
    Mybutton submit;
    Mybutton enter;
    Mybutton cancel;

    String name_user = "请输入用户名";
    String key = "密匙";

    // 登录界面构造
    public LoginFrame() throws HeadlessException {
        super("进销存管理系统");
        this.setBounds(161, 157, 1384, 752);
        // 设置不可变形
        this.setResizable(false);
        // 设置右上角的x按钮为退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submit = new Mybutton("确定");
        cancel = new Mybutton("取消");
        enter = new Mybutton("登陆");

        // 确定按钮绑定底下的actionPerformed方法
        submit.addActionListener(this);
        enter.addActionListener(this);
        // 退出按钮绑定一个退出事件
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // submit.setBorder(null);

        // 执行布局的方法
        loginLayout();

        // 登录窗体显示
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 拿到输入的用户名密码
        String usernameString = username.getText();
        String passwordString = String.valueOf(this.password.getPassword());
        String bosspassword = bossload.getText();
        boolean employerError = bosspassword.trim().equals(key);
        boolean employeeError = usernameString.trim().equals(name_user) || passwordString.trim().length() == 0;
        // 判断输入为空时弹框警告
        // trim的用法：处理输入数据，去除前后的空格，换行，制表符
        if (employeeError || employerError) {
            // 虽然可以优化，但这样更为清晰
            if (employerError) {
                //需要先判断employer因为在实际中会发现，只要不动password内的内容employee都是永true，
                //可以通过修改employee判断依据，但是此处选择省事，警示一下
                //同时
                JOptionPane.showMessageDialog(null, "密匙不能为空！可尝试密码输入", "警告", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "用户名和密码不能为空！", "警告", JOptionPane.WARNING_MESSAGE);
            } // 无论如何都不会执行这几句，因为存在提示文本，，，，已解决，见日志

            //未完全解决
        } else {
            // 将用户名密码送入dao层，进行查验
            boolean loginCheck = new UserDao().loginCheck(usernameString, passwordString);
            if (bosspassword.equals(transit.getKey())) {
                loginCheck = true;
                transit.setBoss(true);

            }
            // 查到，登陆成功
            if (loginCheck) {
                transit.setEmployeename(usernameString);
                // 设置跳转的主窗体WindowFrame的默认参数userName和权限power
                WindowFrame.userName = usernameString;
                // power部分已丢失,取消对权限的设定
                // 主窗体出现
                WindowFrame.getWindowFrame().setVisible(true);
                // 登录窗体隐藏
                this.setVisible(false);// 对该登陆界面考虑做个可以自由移动的子界面
            } else {
                // 登录失败弹窗
                JOptionPane.showMessageDialog(null, "登陆失败,用户名或密码错误！", "警告", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // 登录窗体的绘制部分
    public void loginLayout() {
        // 在窗体中绘制两个组件面板，里面可以放组件
        // 整体的组件存放
        // ImageIcon imageIcon = new ImageIcon("imgs/yin.png");

        JPanel jPanel_boss = new JPanel();
        Myjpanel Alljpanel = new Myjpanel();
        JPanel jPanel_up = new JPanel();
        JPanel jPanel_down = new JPanel();
        JPanel nextjpanel = new JPanel();
        JPanel landall = new JPanel();
        bossload = new Myjtextfield(key);

        landbutton passwordland = new landbutton();
        landbutton keyland = new landbutton();

        Alljpanel.setLayout(null);
        nextjpanel.setLayout(null);
        landall.setLayout(null);

        username = new Myjtextfield(name_user);
        username.setColumns(20);
        password = new Myjpassword();
        password.setColumns(20);
        password.setEchoChar('*');

        passwordland.setText("密码登陆");
        keyland.setText("密匙登陆");

        // 用户名和密码字符和输入框加入第一个组件面板
        jPanel_up.setLayout(new GridLayout(3, 1));
        jPanel_boss.setLayout(new GridLayout(5, 1));
        // jPanel_up.add(name);
        jPanel_up.add(username);
        // jPanel_up.add(passwordLabel);
        jPanel_up.add(password);
        username.setHorizontalAlignment(JTextField.CENTER);
        password.setHorizontalAlignment(JTextField.CENTER);
        bossload.setHorizontalAlignment(JTextField.CENTER);

        // 两个按钮加入第二个组件面板
        jPanel_down.add(submit);
        jPanel_down.add(cancel);

        jPanel_boss.add(bossload);
        jPanel_boss.add(Box.createHorizontalGlue());// 垂直胶水
        jPanel_boss.add(enter);

        nextjpanel.add(jPanel_down);
        nextjpanel.add(jPanel_up);
        // imagejpanel.add(imagelabel);

        landall.add(passwordland);
        landall.add(keyland);

        Alljpanel.add(jPanel_boss);
        Alljpanel.add(nextjpanel);
        Alljpanel.add(landall);

        // 比例
        Alljpanel.setSize(400, 400);
        Alljpanel.setLocation(this.getWidth() / 2 - 200, this.getHeight() / 2 - 200);
        Alljpanel.setBorder(new RoundedBorder(50));// 保留边缘的圆角

        nextjpanel.setBounds(20, 56, 360, 330);
        jPanel_boss.setBounds(20, 56, 360, 330);

        landall.setBounds(20, 3, 360, 50);
        keyland.setBounds(13, 0, 130, 50);
        passwordland.setBounds(216, 0, 130, 50);

        bossload.setBounds(100, 175, 360, 75);
        enter.setBounds(175, 200, 100, 50);

        jPanel_down.setBounds(0, 280, nextjpanel.getWidth(), 50);
        jPanel_up.setBounds(0, 80, nextjpanel.getWidth(), 200);

        // 给窗格使用背景图片
        this.setContentPane(new JPanel() {
            public void paintComponent(Graphics g) {
                setDoubleBuffered(true);
                g.drawImage(new ImageIcon(LoginFrame.class.getResource("imgs/yin.png"))
                        .getImage(), 0, 0, null);
            }
        });

        // 设置两个组件窗格透明，true表示不透明，false表示透明
        jPanel_down.setOpaque(false);
        jPanel_up.setOpaque(false);

        // jPanel_boss.setBackground(Color.yellow);
        nextjpanel.setVisible(false);

        passwordland.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextjpanel.setVisible(true);
                username.setText(name_user);
                password.setText("");
                jPanel_boss.setVisible(false);
                bossload.setText(null);
            }
        });
        keyland.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextjpanel.setVisible(false);
                username.setText(null);
                jPanel_boss.setVisible(true);
                bossload.setText(key);
            }
        });
        this.add(Alljpanel);
        this.getContentPane().setLayout(null);

    }
}