import java.awt.*;

public class StarPolygon extends Polygon {
    public StarPolygon(int x0, int y0, int radius) {
        double alpha = 2 * Math.PI / 10;
        double x, y;
        int razaMica = radius / 2;
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                x = x0 + radius * Math.cos(alpha * i);
                y = y0 + radius * Math.sin(alpha * i);
            } else {
                x = x0 + razaMica * Math.cos(alpha * i);
                y = y0 + razaMica * Math.sin(alpha * i);
            }
            this.addPoint((int) x, (int) y);
        }
    }
}

