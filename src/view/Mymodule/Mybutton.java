package view.Mymodule;
import java.awt.Color;
//import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//import javax.swing.BorderFactory;
import javax.swing.JButton;
//import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Mybutton extends JButton {
    public Mybutton(String text) {
        super(text);// 调用super会出现，父级构建一个边框的同时，子级构建一个边框
        this.setBackground(Color.blue);
        this.setFont(new Font("楷体", Font.PLAIN, 16));
        this.setForeground(Color.WHITE);
        this.addMouseListener(new MouseAdapter() {//因为要同时设置鼠标移入和移出故不能使用lambda表达式
            @Override
            public void mouseEntered(MouseEvent e) {
                Mybutton.this.setBackground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Mybutton.this.setBackground(Color.BLUE);
            }
        });
        /* this.setBorder(BorderFactory.createLineBorder(Color.BLACK));// 手动创建边框，但很丑 */
        this.setMargin(new Insets(0, 30, 0, 30));//参考css
        this.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
}
