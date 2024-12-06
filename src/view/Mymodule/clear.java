package view.Mymodule;

import java.util.ArrayList;
import java.util.List;

import view.UserSearchFrame;

public class clear {
    private static List<Verify> verifys = new ArrayList<>();//直接将创建的匿名对象都放在list内
    private static List<Increased> increases = new ArrayList<>();
    private static List<UserSearchFrame> userSearchFrames = new ArrayList<>();

    public static void verify() {
        for (Verify verify : verifys) {
            verify.dispose();//对各个对象进行去除，实际上里面应该只有上一次创建的一个对象
        }
        verifys.clear();//对列表元素的清除
        verifys.add(new Verify());//创建后添加
    }

    // 这两个暂时不需要返回
    public static void increase() {
        for (Increased increase : increases) {
            increase.dispose();
        }
        increases.clear();
        increases.add(new Increased());

    }

    public static UserSearchFrame userSearchFrame() {
        for (UserSearchFrame userSearchFrame : userSearchFrames) {
            userSearchFrame.dispose();
        }
        userSearchFrames.clear();
        UserSearchFrame userSearchFrame = new UserSearchFrame();
        userSearchFrames.add(userSearchFrame);
        return userSearchFrame;
    }
}
