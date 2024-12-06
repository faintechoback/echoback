package view;

import javax.swing.*;
import view.Mymodule.Myjmenubar;
import view.Mymodule.Myjtoolbar;

import java.awt.*;

public class WindowFrame extends JFrame {
    public static String userName;
    public static String power = "2";
    private JDesktopPane desktopPane;
    private static WindowFrame windowFrame;

    public WindowFrame() {
        super("进销存管理系统");
        this.setBounds(161, 157, 1384, 752);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        desktopPane = new JDesktopPane();
        // 设置为不透明
        desktopPane.setOpaque(true);
        // 设置当前的窗口中使用的是桌面窗格
        this.setContentPane(desktopPane);
        // 新建一个导航条，上面的文件、编辑那些
        Myjmenubar menuBar=new Myjmenubar();
        //新建工具栏，用于实现其他操作，或许也可以和查询放在一起
        Myjtoolbar jbToolBar=new Myjtoolbar();
        // 设置导航条，同时导航条不可为下拉栏
        this.setLayout(new BorderLayout());
        this.setJMenuBar(menuBar);
        this.add(jbToolBar, BorderLayout.EAST);
        //jbToolBar.setBounds(this.getWidth()/6*5, 0, this.getWidth()/6, this.getHeight());
        desktopPane.setBackground(new Color(137, 198, 239));
        // 设置拖拽模式
        desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }
    // 登录界面请求页面的时候，返回一个新的当前页
    public static WindowFrame getWindowFrame() {
        if (windowFrame == null) {
            windowFrame = new WindowFrame();
        }
        return windowFrame;
    }

}
