package main.java.com.higgs.content.actor;

import main.java.com.higgs.content.world.Simulation;
import main.java.com.higgs.obj.actor.Actor;
import main.java.com.higgs.utils.GameImage;
import main.java.com.higgs.utils.Vector;

import java.awt.*;
import java.text.DecimalFormat;

public class Stats extends Actor {
    private int width = 200;
    private int height = 100;
    private int acts = 0;

    @Override
    public void act() {
        if(acts == 0) setPosition(new Vector(width / 2, height / 2, 0));

        GameImage image = new GameImage(width, height);
        image.setColor(Color.WHITE);
        image.fill();
        image.setColor(Color.BLACK);
        image.drawString("windspeed: " + ((Simulation)getWorld()).getWindSpeed() /* (50/0.72)*/, 0, 20);

        try {
            Windmill windmill = (Windmill) getWorld().getActorsAt(getWorld().getWidth() / 2, getWorld().getHeight() / 2).get(0);
            image.drawString("velocity: " + Double.parseDouble(new DecimalFormat("#.#####").format(windmill.getOmega())), 0, 35);
            if(windmill.isBraking()) {
                image.setColor(Color.RED);
                image.drawString("braking", 0, 50);
            }
        } catch(ClassCastException ignore) { }

        setImage(image);
        acts++;
    }
}
