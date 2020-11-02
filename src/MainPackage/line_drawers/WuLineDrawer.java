package MainPackage.line_drawers;

import MainPackage.LineDrawer;
import MainPackage.PixelDrawer;

import java.awt.*;

public class WuLineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public WuLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        boolean steep = Math.abs(y2 - y1) > Math.abs(x2 - x1);
        if (steep) {
            int temp = x1; x1 = y1; y1 = temp;
            int t = x2; x2 = y2; y2 = t;
        }
        if (x1 > x2) {
            int temp = x1; x1 = x2; x2 = temp;
            int t = y1; y1 = y2; y2 = t;
        }

        double dx = x2 - x1;
        double dy = y2 - y1;
        double grad = dy / dx;
        double y = y1 + grad;

        pd.setPixel(steep ? y1 : x1, steep ? x1 : y1, Color.BLACK);
        for (int x = x1 + 1; x <= x2; x++){
            if(steep) {
                pd.setPixel((int)y, x,  new Color(0,0,0,(float)(1 -(y - (int)y)))/*getColor( (y - (int)y))*/);
                pd.setPixel((int)y + 1, x,  new Color(0,0,0,(float)( (y - (int)y)))/*getColor(1 - (y - (int)y))*/);
            } else {
                pd.setPixel(x, (int)y, new Color(0,0,0,(float)(1- (y - (int)y)))/*getColor((y - (int)y))*/);
                pd.setPixel(x, (int)y + 1,  new Color(0,0,0,(float)( (y - (int)y)))/*getColor(1 - (y - (int)y))*/);
            }
            y += grad;
        }
    }

    private Color getColor(double brightness) {
        double c = brightness * 255;
        int k = intPart(c);
        return new Color(k,k,k);
    }

    private int intPart(double x) {    //целая часть
        return (int) x;
    }

}
