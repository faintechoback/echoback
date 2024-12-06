package view.Mymodule;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import dao.UserDao;
import view.transit;

public class Increased extends JDialog { // 模态框在被手动关闭前，用户只能操作模态框内的内容
    public Increased() {
        List<Myjtextfield> textFields = new ArrayList<>();// 神之一手
        String []names = transit.judgement();// 各个列表前部分id的合集
        Object []value = new Object[names.length];// 提取值后将数据都放进enter,为了适应各种类型参数，采取object型

        // 根据names.length修改长度

        // 对于不同表下不同数量数据的添加，采取先从transit内获取文本，后依旧是较为无脑的switch，
        // 挨个创建输入框
        // 除此暂时想不到别的

        // 对于对前面的提示文本，以及其中的条数都是应当从transit中获取，应
        // 该也是个循环，直接根据数组，对第一个元素或许不需要添加，但在sql语句中需要作出对应的修改

        for (int n = 1; n < names.length; n++) {// 将n设为1，排去对数据无太多意义的序号字段，但在sql语句中需要进行一定的修改
            textFields.add(new Myjtextfield(names[n]));
            // 由于直接创建后添加的话会导致下一个覆盖上一个，因此将每次的结果的加到列表中
        }
        this.add(Box.createVerticalGlue()); // 添加垂直胶水
        for (Myjtextfield textfield : textFields) {
            this.add(textfield);
        } // 目前的问题是如何访问这些在各个匿名输入框的内存放的数据
        Mybutton submit = new Mybutton("提交");
        submit.setFont(new Font("楷体", Font.PLAIN, 32));
        submit.addActionListener((_) -> {
            int n = 0;
            for (Myjtextfield textfield : textFields) {
                value[n] = textfield.getText();
                n++;// 数组的方式存储
            }
            if (value.length == 0) {
                JOptionPane.showMessageDialog(null, "请输入要添加的数据", "警告", JOptionPane.WARNING_MESSAGE);
            } else {
                UserDao delete = new UserDao();
                    delete.increaseUser(value);//问题在于由于id没有按照输入，让她直接自增会报错
            }
        });
        this.add(submit);

        // 基本的属性设置，大小需要进行调节，或许也可以通过行数来设置不同的高度
        this.setSize(new Dimension(700, 60 * names.length));
        this.setTitle("添加信息");
        setLayout(new GridLayout(names.length + 2, 1));// 一个输入框一行，即一个元素占一行
        setLocationRelativeTo(null);// 屏幕居中显示
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);// 设定大小不变
        this.setModal(true);
        this.setVisible(true);
    }
}
