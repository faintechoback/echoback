package view;

import dao.UserDao;
import entity.User;
import view.Mymodule.Mybutton;
//import view.Mymodule.Myjtextfield;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class UserSearchFrame extends JInternalFrame {
    // 可以通过重载实现不同的功能.pass，由于继承，参数列表已近固定，可尝试多些一个方法来设置功能
    public UserSearchFrame() {
        super("供货记录", true, true, true, true);
        this.setBounds(0, 0, 1384, 752);
        // 将当前窗体内容替换为用户搜索组件窗格
        this.setContentPane(new UserSearchPannel());
    }
}

// 将用户搜索组件窗格
class UserSearchPannel extends JPanel {
    JTable table;
    // 存放数据的表格
    MyTableModel tableModel;
    
    JTextField textField;

    public UserSearchPannel() {
        super(new BorderLayout());
        // 搜索框构建
        JPanel pane = search();
        // 给搜索框放右边
        add(pane, BorderLayout.NORTH);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        tableModel = new MyTableModel();
        table = new JTable(tableModel);

        // 设置表格大小，设置一些属性，是否可滚动这些
        table.setPreferredScrollableViewportSize(new Dimension(screenSize.width * 2 / 3 - 60,
                screenSize.height / 3));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        // 滚动条
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

    }

    // 搜索框构建
    public JPanel search() {
        // 初始化一个组件存放的窗格
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel lable = new JLabel("请选择查询条件：");
        panel.add(lable);

        // 初一个选择组件，可选择查询子项，大致修改了原始组件框的写法
        String[] options = transit.choice();
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setSelectedIndex(0);
        panel.add(comboBox);

        // 查询输入窗格
        textField = new JTextField();
        textField.setColumns(13);
        panel.add(textField);

        // 查询按钮，并绑定查询事件监听
        Mybutton searchone = new Mybutton("查询");
        searchone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 拿到搜索的类型和搜索值
                String field = comboBox.getSelectedItem().toString();
                String value = textField.getText();
                // 判断是不是空，空了报警告
                if (value == null || value.trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "请输入搜索的值", "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    // 调取dao层的方法查询符合条件的用户并更新表
                    Vector<User> users = new UserDao().searchUser(field, value);
                    if (users.size() == 0) {
                        JOptionPane.showMessageDialog(null, "没有满足条件的记录", "警告", JOptionPane.WARNING_MESSAGE);
                    } /*
                       * 此处若采取原demo样式，会在查不到时有异常（此时会多次刷新，并持续异常），且会点不动
                       * 此时修改为，当且仅当，只有查到才刷新，这样就不会因为查不到依旧还刷新带来错误
                       * 查询全部部分同理
                       */

                    else
                        tableModel.updateData(users);
                }
            }
        });
        // 添加显示全部信息按钮，并绑定监听
        panel.add(searchone);
        Mybutton searchall = new Mybutton("显示全部信息");
        searchall.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 调取dao层的方法查询所有用户并更新表
                // getalluser是对以前的表查询，故不修改就查询后只有四个元素，因此需要再写一个
                Vector<User> allCustomer = new UserDao().getAllinfo();
                if (allCustomer.size() == 0) {
                    JOptionPane.showMessageDialog(null, "当前不存在任何客户信息", "警告", JOptionPane.WARNING_MESSAGE);
                } else
                    tableModel.updateData(allCustomer);
            }
        });
        panel.add(searchall);

        /* 写增加是不是意味着需要写多个输入窗口，并将这多个窗口的输入一次传递到目标，貌似不需要管 */
        Mybutton deletion = new Mybutton("删除");
        deletion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String field = comboBox.getSelectedItem().toString();
                String value = textField.getText();
                if (value == null || value.trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "请输入要删除的数据", "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    UserDao delete = new UserDao();
                    delete.deleteUser(field, value);
                }
            }
        });
        panel.add(deletion);

        Mybutton insert = new Mybutton("添加");
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        return panel;
    }
}
