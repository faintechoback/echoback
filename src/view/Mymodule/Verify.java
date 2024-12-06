package view.Mymodule;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import view.transit;

public class Verify extends JDialog {
    private int pitch;

    public Verify() {// 对于删除部分或许也可以使用本部分，只是需要判断
        JLabel jlabel = new JLabel("请输入所操作订单的id");//暂时无用
        Myjtextfield myjtextfield = new Myjtextfield(null);
        Mybutton sure = new Mybutton("确认");

        sure.addActionListener((_) -> {
            try {
                pitch = Integer.parseInt(myjtextfield.getText());
                if (pitch != 0) {
                    if (transit.getFunction() == 1) {
                        transit.setPitch(pitch);
                        new Updated();
                        this.dispose();
                    }
                    else{
                        //对于出入的设计方面存在一定缺陷，或者说是思路方面的失误，若对基础表部分不修改的话，
                        //对于imp这样的部分应该不能存在所谓的添加和删除，因为这些都是来自于购入表，
                        //同时，对购入deliver表进行入库操作后才有了所谓的imp表，添加操作应该只是针对于供货和交易与信息
                        //对于售出与入库应该没有增加删除操作，登陆部分或许可以加个注册功能，且设置密匙，若有密匙直接视为boss权限

                        //对于绝大部分的删除操作应该只有boss权限才拥有，对于普通员工应该只有出入库权限，也就是说右侧的工具栏只对老板开放
                        //其余应该隐藏
                        //pass,这样操作的话会导致哪怕是删除部分也只能根据id来删除
                    }

                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "请正确输入", "警告", JOptionPane.WARNING_MESSAGE);
                // 弹出对话框，可多处移植，joptionpane是个单独的类
            }
        });
        jlabel.setFont(new Font("楷体", Font.PLAIN, 16));
        jlabel.setHorizontalAlignment(JTextField.CENTER);
        this.add(jlabel);
        this.add(myjtextfield);
        this.add(sure);
        this.setSize(new Dimension(300, 300));
        this.setTitle("请确认");
        setLayout(new GridLayout(3, 1));// 一个输入框一行，即一个元素占一行
        setLocationRelativeTo(null);// 屏幕居中显示
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);// 设定大小不变
        this.setVisible(true);
    }
}
