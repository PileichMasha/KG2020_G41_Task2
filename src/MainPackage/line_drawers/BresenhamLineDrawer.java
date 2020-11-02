package MainPackage.line_drawers;

import MainPackage.LineDrawer;
import MainPackage.PixelDrawer;

import java.awt.*;

public class BresenhamLineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public BresenhamLineDrawer(PixelDrawer pd) {
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

        int dx = x2 - x1;
        int dy = Math.abs(y2 - y1);
        int e = 2 * dy - dx;
        int step;
        if (y1 < y2)      // направление роста координаты по y
            step = 1;
        else step = -1;
        int y = y1;

        for (int x = x1; x <= x2; x++) {
            if(steep)
                pd.setPixel(y, x, Color.BLACK);
            else
                pd.setPixel(x, y, Color.BLACK);

            if (e >= 0) {
                y += step;
                e += -2 * dx + 2 * dy;
            } else
                e += 2 * dy;
        }

        /*for (int x = x1; x <= x2; x++) {
            pd.setPixel(steep ? y : x, steep ? x : y, Color.BLACK);
            e -= dy;
            if (e < 0)
            {
                y += ystep;
                e += dx;
            }
        }*/
    }
}
