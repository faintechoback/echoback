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

public class Updated extends JDialog{
    public Updated() {
        List<Myjtextfield> textFields = new ArrayList<>();
        String []names = transit.judgement();
        Object []value = new Object[names.length];
        UserDao update=new UserDao();
        Object []original=update.getobject();//原始数据

        if(original==null){
            JOptionPane.showMessageDialog(this, "未找到该数据", "警告", JOptionPane.WARNING_MESSAGE);
            //new Verify();
            clear.verify();
            this.dispose();
        }
        for (int n = 1; n < names.length; n++) {
            textFields.add(new Myjtextfield(names[n]));
        }
        this.add(Box.createVerticalGlue()); //垂直胶水
        int m=1;
        for (Myjtextfield textfield : textFields) {
            this.add(textfield);
            textfield.setText(original[m++].toString());
        }
        Mybutton submit = new Mybutton("更新");
        submit.setFont(new Font("楷体", Font.PLAIN, 32));
        submit.addActionListener((_) -> {
            int n = 0;
            for (Myjtextfield textfield : textFields) {
                value[n] = textfield.getText();//不足依旧，对多个打开无法自行关闭，或者可以加监听，
                //对于可能出现多个打开的类或许可以直接写个工具类，不同方法新建不同类，
                //同时根据list来进行关闭，但是可能会无法识别前面待关闭的部分
                //目前最笨的方法依旧是写多个list和循环检查,对待关闭部分手动关闭


                n++;// 数组的方式存储
            }
            if (value.length == 0) {
                JOptionPane.showMessageDialog(null, "请输入要修改的数据", "警告", JOptionPane.WARNING_MESSAGE);
            } else {
                UserDao delete = new UserDao();
                    delete.updateUser(value);
            }
        });//存在问题，发现不会覆盖原来的，应该是没有写sql里面where指定id

        
        this.add(submit);
        this.setSize(new Dimension(700, 60 * names.length));
        this.setTitle("修改信息");
        setLayout(new GridLayout(names.length + 2, 1));
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
}
