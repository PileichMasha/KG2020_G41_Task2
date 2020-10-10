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
        int dx = sign(x2 - x1);  //приращения
        int dy = sign(y2 - y1);
        int lengthX = Math.abs(x2 - x1);  //длины отрезков
        int lengthY = Math.abs(y2 - y1);

        int length = Math.max(lengthX, lengthY);
        int e = 0;       //ошибка
        int x = x1; int y = y1;
        if (lengthX >= lengthY) {
            e -= lengthX;
            while (length > 0) {
                pd.setPixel(x, y, Color.BLACK);
                x += dx;
                e += 2 * lengthY;
                if (e > 0) {
                    e -= 2 * lengthX;
                    y += dy;
                }
                length--;
            }
        } else {   //если растёт по у
            e = -lengthY;
            while (length > 0) {
                pd.setPixel(x, y, Color.BLACK);
                y += dy;
                e += 2 * lengthX;
                if (e > 0) {
                    e -= 2 * lengthY;
                    x += dx;
                }
                length--;
            }
        }
    }

    private int sign(int x) {
        if (x >= 0)
            return 1;
        else
            return -1;
    }

    /*for (int i = 0; i <= dx; i++) {
            pd.setPixel(x1, y1, Color.BLACK);
            if (e >= 0) {
                y1++;
                e = e - 2 * dx + 2 * dy;
            } else {
                e = e + 2 * dy;
            }
            x1++;
        }*/
}
