package view.Mymodule;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

public class landbutton extends JButton{
    public landbutton(){
        this.setBorder(null);
        this.setFont(new Font("行楷", Font.PLAIN, 28));
        setForeground(Color.black);
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setBackground(Color.white);
        this.addMouseListener(new MouseAdapter() {//因为要同时设置鼠标移入和移出故不能使用lambda表达式
            @Override
            public void mouseEntered(MouseEvent e) {
                landbutton.this.setBackground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                landbutton.this.setBackground(Color.white);
            }
        });
    }
}
