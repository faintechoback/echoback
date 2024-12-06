package view.Mymodule;


import javax.swing.border.AbstractBorder;

import java.awt.*;

public class RoundedBorder extends AbstractBorder {
    private int radius; // 实现对局部区域的弧形边角

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.white);

        float alpha = 0.8f; // 修改透明度
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(ac);

        g2d.fillRoundRect(x, y, width - 1, height - 1, radius, radius);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

        g2d.setColor(c.getForeground());
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius, radius, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = this.radius;
        return insets;
    }
}