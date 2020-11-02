package MainPackage;

import MainPackage.line_drawers.BresenhamLineDrawer;
import MainPackage.line_drawers.DDALineDrawer;
import MainPackage.line_drawers.GraphicsLineDrawer;
import MainPackage.line_drawers.WuLineDrawer;
import MainPackage.utils.DrawUtils;
import javafx.scene.layout.BackgroundFill;
import pixel_drawers.GraphicsPixelDrawer;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel implements MouseMotionListener {
    private Point position = new Point(0, 0);

    public DrawPanel() {
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics bi_g = bi.createGraphics();
        bi_g.setColor(Color.WHITE);
        bi_g.fillRect(0, 0, getWidth(), getHeight());
        bi_g.setColor(Color.BLACK);
        /**/
        PixelDrawer pd = new GraphicsPixelDrawer(bi_g);
        //LineDrawer ld = new DDALineDrawer(pd); //сделать переключатель для выбора linedrawer'a
        //LineDrawer ld = new BresenhamLineDrawer(pd);
        LineDrawer ld = new WuLineDrawer(pd);

        drawAll(ld);
        /**/
        g.drawImage(bi, 0, 0, null);
        bi_g.dispose();
    }

    private void drawAll(LineDrawer ld) {
        DrawUtils.drawSnowFlake(ld,getWidth() / 2-100, getHeight() / 2-100, 100, 32);
        ld.drawLine(getWidth() / 2, getHeight() / 2,
                position.x, position.y);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
            position = new Point(e.getX(), e.getY());
            repaint();
    }
}

