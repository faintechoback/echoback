package view.Mymodule;

import javax.swing.Box;
import javax.swing.JMenuBar;

import view.WindowAction;

public class Myjmenubar extends JMenuBar{
    public Myjmenubar(){
         Mybutton button = new Mybutton("入库记录");
        Mybutton button2 = new Mybutton("采购记录");
        Mybutton button3 = new Mybutton("售出记录");
        Mybutton button4 = new Mybutton("客户信息");
        Mybutton button5 = new Mybutton("采购商信息");
        //menuBar.add(Box.createHorizontalGlue());//水平胶水，来适应布局
        this.add(button);
        this.add(Box.createHorizontalGlue());
        this.add(button2);
        this.add(Box.createHorizontalGlue());
        this.add(button3);
        this.add(Box.createHorizontalGlue());
        this.add(button4);
        this.add(Box.createHorizontalGlue());
        this.add(button5);
        this.add(Box.createHorizontalGlue());
        button.addActionListener(WindowAction.userserchFrame(1));// 考虑监听后直接赋值，而不是根据参数
        button2.addActionListener(WindowAction.userserchFrame(2));// 可能是在初次建立后会直接赋值，而不是只有当监听器执行/按钮点击后后才会赋值
        button3.addActionListener(WindowAction.userserchFrame(3));
        button4.addActionListener(WindowAction.userserchFrame(4));
        button5.addActionListener(WindowAction.userserchFrame(5));
    }
}
