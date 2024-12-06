package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/* import java.util.ArrayList;
import java.util.List; */

import view.Mymodule.clear;

public class WindowAction {
    //private static List<UserSearchFrame> userSearchFrames = new ArrayList<>();//后面也有地方需要，是否可以写成一个类

    // 创建列表，将每次新建子界面前判断该界面表内有无数据（代表有无已打开的子界面），有则清除
    // 用户查询Menu绑定的方法，用于返回用户查询界面，底下的几个方法是固定写法
    public static ActionListener userserchFrame(int selection) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transit.setSelection(selection);// 传参判断表
               /*  for (UserSearchFrame frame : userSearchFrames) {
                    frame.dispose();
                }
                userSearchFrames.clear(); */
                // 创建新的子界面
                UserSearchFrame userSearchFrame = clear.userSearchFrame();
                //userSearchFrames.add(userSearchFrame);
                // 将主界面的窗体内容替换为用户查询子界面
                WindowFrame.getWindowFrame().getContentPane().add(userSearchFrame);
                userSearchFrame.setVisible(true);
            }
        };
    }
}
