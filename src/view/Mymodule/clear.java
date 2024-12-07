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
            verify.dispose();//！！！！这是各个对象的资源释放
        }
        //是否存在重复多余
        verifys.clear();//！！！！对列表元素的清除，因此和上面的dispose并不冲突
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

    //winaction部分需要调用，因此需要返回值，进过修改后或许不需要，但是省事
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
