package AWT;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class RandomPainterDemo extends Frame implements MouseListener {
    final ColorRandom colorRandom;
    final PolygonRandom polygonRandom;
    RandomPainterDemo() {
        addWindowListener(new MyWindowAdapter());
        addMouseListener(this);
        colorRandom = new ColorRandom();
        polygonRandom = new PolygonRandom(30,3,9);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            g.setColor(colorRandom.nextColor());
            int size = random.nextInt(5, 31);
            g.fillArc(random.nextInt(0, 301), random.nextInt(0, 301), size, size, 90, -360);
            g.setColor(colorRandom.nextColor());
            g.fillPolygon(polygonRandom.nextPolygon(new Point(0,0),new Point(300,300)));
        }
    }

    public static void main(String[] args) {
        RandomPainterDemo arcPaintDemo = new RandomPainterDemo();
        arcPaintDemo.setSize(new Dimension(300, 300));
        arcPaintDemo.setTitle("Random painter!");
        arcPaintDemo.setVisible(true);
    }
}
class PolygonRandom {
    private final int range;
    private final int minAngles;
    private final int maxAngles;
    private final Random random;
    // Range is max offset from last generated Point in polygon
    PolygonRandom(Random random, int range, int minAngles, int maxAngles) {
        this.random = random;
        this.range = range;
        this.minAngles = minAngles;
        this.maxAngles = maxAngles;
    }
    PolygonRandom(int range, int minAngles, int maxAngles) {
        this.random = new Random();
        this.range = range;
        this.minAngles = minAngles;
        this.maxAngles = maxAngles;
    }
    public Polygon nextPolygon(Point start, Point end) {
        int n = random.nextInt(minAngles,maxAngles);
        int[] x = new int[n];
        int[] y = new int[n];
        x[0] = random.nextInt(start.x, end.x);
        y[0] = random.nextInt(start.y,end.y);
        for(int j = 1; j < n; j++) {
            x[j] = random.nextInt(x[j-1] - range, x[j-1] + range);
            y[j] = random.nextInt(y[j-1] - range, y[j-1] + range);
        }
        return new Polygon(x,y,n);
    }
}
class ColorRandom {
    private final Random random;
    ColorRandom(Random random) {
        this.random = random;
    }
    ColorRandom() {
        random = new Random();
    }
    public Color nextColor() {
        return new Color(random.nextInt(0,256),random.nextInt(0,256), random.nextInt(0,256));
    }
}
