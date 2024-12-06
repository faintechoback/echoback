package view.Mymodule;

import java.awt.Font;

import javax.swing.JPasswordField;

public class Myjpassword extends JPasswordField{
    public Myjpassword(){
        this.setFont(new Font("行楷", Font.PLAIN, 24));
        

       /*  this.setBorder(null);
         Border bottomBorder = new LineBorder(Color.BLACK, 5);

        // 设置JPasswordField的边框样式
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(), bottomBorder)); */

                //this.setOpaque(false);
    }
}
