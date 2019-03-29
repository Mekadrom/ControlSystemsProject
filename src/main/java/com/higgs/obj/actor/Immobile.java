package main.java.com.higgs.obj.actor;

import main.java.com.higgs.utils.GameImage;
import main.java.com.higgs.utils.Vector;

import java.awt.*;

public class Immobile extends Actor {
    private int acts = 0;
    private double x = 0;
    private double y = 0;

    private Rectangle bounds;

    public Immobile(GameImage image) {
        bounds = new Rectangle((int)(getX() - (image.getWidth() / 2)), (int)(getY() - (image.getHeight() / 2)), (int)(getX() + (image.getWidth() / 2)), (int)(getY() + (image.getHeight() / 2)));
    }

    @Override
    public void act() {
        if(acts == 0) {
            x = getX();
            y = getY();
        }

        setPosition(new Vector(x, y, 0));
        acts++;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
