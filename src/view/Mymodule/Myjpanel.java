package view.Mymodule;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Myjpanel extends JPanel {
    private Point initialClick;

    public Myjpanel() {
        this.setLayout(null);//根据鼠标的移动来实现jpanel为一可拖动的窗口
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                initialClick = null;
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (initialClick != null) {
                    int thisX = getLocation().x;
                    int thisY = getLocation().y;
                    int xMoved = e.getX() - initialClick.x;
                    int yMoved = e.getY() - initialClick.y;
                    int X = thisX + xMoved;
                    int Y = thisY + yMoved;
                    setLocation(X, Y);
                }
            }
        });
        this.setOpaque(false); // 设置面板为非不透
    }

    /*
     * @Override
     * protected void paintComponent(Graphics g) {
     * Graphics2D g2d = (Graphics2D) g.create();
     * g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
     * // 设置透明度为50%
     * g2d.setBackground(Color.white);
     * g2d.fillRect(0, 0, getWidth(), getHeight());
     * super.paintComponent(g2d);
     * g2d.dispose();
     * 
     * }
     */

}