
import javax.swing.*;
import view.LoginFrame;
public class experimentalmain {
    public static void main(String[] args) {
        // 使用当前风格化外观
        JFrame.setDefaultLookAndFeelDecorated(true);
        // 打开登录窗体
        LoginFrame loginFrame=new LoginFrame();
        loginFrame.setVisible(true); // Make the login frame visible
    }
}
