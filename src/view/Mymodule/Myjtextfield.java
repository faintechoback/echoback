package view.Mymodule;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public class Myjtextfield extends JTextField {
    public Myjtextfield(String text) {
        this.setText(text);
        this.setFont(new Font("行楷", Font.PLAIN, 24));
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Myjtextfield.this.getText().equals(text)) {
                    Myjtextfield.this.setText("");
                    Myjtextfield.this.setForeground(Color.BLACK);
                }
            }

            //识别焦点的丢失，但在初始状态一直不点不会识别，
            @Override
            public void focusLost(FocusEvent e) {
                if (Myjtextfield.this.getText().isEmpty()) {
                    Myjtextfield.this.setText(text);
                    Myjtextfield.this.setForeground(Color.GRAY);
                }
            }
            
        });
    }
}
