package MainPackage;

import javax.swing.*;

public class MainWindow extends JFrame {
    private DrawPanel dp;

    public MainWindow() {
        DrawPanel dp = new DrawPanel();
        this.add(dp);
    }
}
