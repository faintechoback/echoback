package view.Mymodule;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import view.transit;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;

public class Myjtoolbar extends JToolBar {
    public Myjtoolbar() {

        Mybutton increase = new Mybutton("  添加  ");
        Mybutton exit = new Mybutton("  退出  ");
        Mybutton update = new Mybutton("  修改  ");
        Mybutton delete = new Mybutton("  删除  ");

        JLabel wellcome = new JLabel(wellcome());
        JLabel name = new JLabel("员工:" + transit.getEmployeename());// 识别boss权限后换内容
        JLabel work = new JLabel("祝您工作愉快");
        name.setBackground(Color.lightGray);
        wellcome.setBackground(Color.ORANGE);

        if (transit.isBoss()) {
            name.setText("老板");
        }
        // 入库操作或许没必要也写在此部分且
        // 添加与修改缺陷：都不能自动刷新，必须要手动重新显示
        // AlimamaDaoLiTi
        // 测试本机字体的使用delete.setFont(new Font("阿里妈妈刀隶体", Font.PLAIN, 22));
        Myjtextfield myjtextfield = new Myjtextfield("所修改字段的id");

        exit.addActionListener((_) -> {
            System.exit(0);
        });
        increase.addActionListener((_) -> {
            /*
             * Increased increased = new Increased();
             * // this.add(increased);//直接向toolbar内添加窗口会报错,因为二者本身的层级关系而不允许
             * increased.setVisible(true);
             */
            clear.increase();
        });
        update.addActionListener((_) -> {
            // new Verify();//直接匿名
            transit.setFunction(1);
            clear.verify();
            // Verify verify = new Verify();
            // verify.setVisible(true);//可以不写，但是防止因为未使用而报错
        });
        delete.addActionListener((_) -> {
            transit.setFunction(0);
        });
        // this.setLayout(null);
        // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//
        // 修改布局为一列，单个元素组件直接沾满你每一行
        // this.add(Box.createVerticalGlue()); // 添加垂直胶水，适应布局
        this.setLayout(new GridLayout(15, 1));
        if (transit.isBoss()) {
            this.add(increase);
            this.add(update);
            this.add(delete);
        } else {

        }
        // this.add(delete);
        this.add(wellcome);
        this.add(name);
        this.add(work);

        this.add(exit);

        this.add(Box.createVerticalGlue());//垂直胶水
        this.add(myjtextfield);
        this.add(Box.createVerticalGlue());
        myjtextfield.setPreferredSize(exit.getPreferredSize());
        myjtextfield.setFont(new Font("楷体", Font.PLAIN, 16));
        style(wellcome);
        style(name);
        style(work);
        // this.add(shift);
        // this.add(Box.createVerticalGlue()); // 添加垂直胶水

        /*
         * JPanel Myjpanel = new JPanel();// 用作输入栏的添加,
         * //输入方面可以通过构建一个模态框，参考b站视频，类表格布局，视觉效果较好
         * Myjpanel.setBounds(0, this.getHeight() / 3, this.getWidth(), this.getHeight()
         * / 4);
         * Myjpanel.setBackground(Color.yellow);
         * Myjpanel.setLayout(new GridLayout(4, 4));
         * this.add(Myjpanel);
         */
    }

    public String wellcome() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);// 获取当前小时
        if (hour > 6 && hour <= 12) {
            return "上午好";
        } else {
            if (hour > 12 && hour <= 18) {
                return "下午好";
            } else
                return "晚上好";
        }
    }

    public void style(JLabel jlabel) {
        jlabel.setFont(new Font("阿里妈妈刀隶体", Font.PLAIN, 22));
        jlabel.setHorizontalAlignment(Myjlabel.CENTER);
        jlabel.setOpaque(true);// 不透明，默认透明，无背景色
        jlabel.setBackground(Color.ORANGE);
    }
}