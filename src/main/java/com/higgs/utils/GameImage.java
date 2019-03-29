package main.java.com.higgs.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameImage {
    private BufferedImage bufferedImage;
    private Color drawColor = new Color(255, 255, 255);

    public GameImage(BufferedImage i) {
        bufferedImage = i;
    }

    public Rectangle getBounds() {
        return new Rectangle(0, 0, getWidth(), getHeight());
    }

    public GameImage(GameImage i) {
        this(i.getBufferedImage());
    }

    public GameImage(String location) {
        this(new ResourceLocation(location));
    }

    public GameImage(ResourceLocation location) {
       this(ResourceLocation.getImage(location.getDirectory()));
    }

    public GameImage(int width, int height) {
        this(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
    }

    public void fill() {
        fillRect(0, 0, getWidth(), getHeight());
    }

    public void drawShape(Shape s) {
        Graphics2D g = (Graphics2D)bufferedImage.getGraphics();
        g.setColor(drawColor);
        g.draw(s);
        g.dispose();
    }

    public void drawImage(GameImage image, int x, int y) {
        Graphics2D g = (Graphics2D)bufferedImage.getGraphics();
        g.drawImage(image.getBufferedImage(), x, y, null);
        g.dispose();
    }

    public void drawImage(BufferedImage image, int x, int y) {
        Graphics2D g = (Graphics2D)bufferedImage.getGraphics();
        g.drawImage(image, x, y, null);
        g.dispose();
    }

    public void drawOval(int x, int y, int width, int height) {
        Graphics2D g = (Graphics2D)bufferedImage.getGraphics();
        g.setColor(drawColor);
        g.drawOval(x, y, width, height);
        g.dispose();
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D)bufferedImage.getGraphics();
        g.setColor(drawColor);
        g.drawLine(x1, y1, x2, y2);
        g.dispose();
    }

    public void drawRect(int x, int y, int width, int height) {
        Graphics2D g = (Graphics2D)bufferedImage.getGraphics();
        g.setColor(drawColor);
        g.drawRect(x, y, width, height);
        g.dispose();
    }

    public void fillRect(int x, int y, int width, int height) {
        Graphics2D g = (Graphics2D)bufferedImage.getGraphics();
        g.setColor(drawColor);
        g.fillRect(x, y, width, height);
        g.dispose();
    }

    public void drawString(String s, int x, int y) {
        Graphics2D g = (Graphics2D)bufferedImage.getGraphics();
        g.setColor(drawColor);
        int h = g.getFontMetrics().getHeight();
        g.drawString(s, x, y + h);
        g.dispose();
    }

    public void setColor(Color c) {
        drawColor = c;
    }

    public int getWidth() {
        return bufferedImage.getWidth();
    }

    public int getHeight() {
        return bufferedImage.getHeight();
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
