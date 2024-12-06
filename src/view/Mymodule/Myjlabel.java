package view.Mymodule;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Myjlabel extends JLabel {
    public Myjlabel(String text) {
        this.setFont(new Font("楷体", Font.PLAIN, 22));
        this.setHorizontalAlignment(JTextField.CENTER);
    }
}
