import java.awt.*;

public class PlusPolygon extends Polygon {
    public PlusPolygon(int x0, int y0, int radius) {
        this.addPoint(x0 + 3 * radius / 2, y0 - radius / 2);
        this.addPoint(x0 + 3 * radius / 2, y0 + radius / 2);
        this.addPoint(x0 + radius / 2, y0 + radius / 2);
        this.addPoint(x0 + radius / 2, y0 + 3 * radius / 2);
        this.addPoint(x0 - radius / 2, y0 + 3 * radius / 2);
        this.addPoint(x0 - radius / 2, y0 + radius / 2);
        this.addPoint(x0 - 3 * radius / 2, y0 + radius / 2);
        this.addPoint(x0 - 3 * radius / 2, y0 - radius / 2);
        this.addPoint(x0 - radius / 2, y0 - radius / 2);
        this.addPoint(x0 - radius / 2, y0 - 3 * radius / 2);
        this.addPoint(x0 + radius / 2, y0 - 3 * radius / 2);
        this.addPoint(x0 + radius / 2, y0 - radius / 2);
    }
}

