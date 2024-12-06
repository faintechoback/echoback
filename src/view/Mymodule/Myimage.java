package view.Mymodule;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;//pass掉或许可以保留，暂时取消，后续美化或许能够加回来
import javax.swing.JPanel;

public class Myimage extends JPanel {
    BufferedImage img;

    public Myimage() {
        try {
            File image = new File("src\\view\\imgs\\测试.png");
            this.img = ImageIO.read(image);

        } catch (IOException e) {
            System.out.println("未找到图片");
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.drawImage(this.img, 0, 0, 150, 150, null);

    }
}
