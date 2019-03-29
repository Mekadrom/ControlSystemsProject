package main.java.com.higgs.content.world;

import main.java.com.higgs.content.actor.Stats;
import main.java.com.higgs.content.actor.Windmill;
import main.java.com.higgs.obj.world.World;
import main.java.com.higgs.utils.GameImage;
import main.java.com.higgs.utils.Vector;

import java.text.DecimalFormat;


public class Simulation extends World {
    public double windSpeed = 0.1;
    public double variance = 0.012;

    private boolean random = true;

    public Simulation() {
        super(800, 600);

        GameImage image = new GameImage("background.png");//new GameImage(getWidth(), getHeight());
//        image.setColor(new Color(0xa9c5d1));
//        image.fill();
        setImage(image);
        addObject(new Windmill(), new Vector(getWidth() / 2, getHeight() / 2, 0));
        addObject(new Stats(), new Vector(0, 0, 0));
    }

    @Override
    public void act() {
        generateWind();
    }

    private void generateWind() {
        if(random) {
            double vary = Math.random() * variance;
            windSpeed = windSpeed + (vary - (0.499 * variance)); // + Utils.randDouble(-variance, variance);
        } else {
            DecimalFormat df = new DecimalFormat("#.##");
            windSpeed = Double.parseDouble(df.format(windSpeed));
        }
    }

    public double getWindSpeed() {
        return this.windSpeed;
    }

    public void setWindSpeed(double speed) {
        this.windSpeed = speed;
    }

    public void toggleRandom() {
        this.random = !random;
    }

    public void setRandom(boolean set) {
        this.random = set;
    }
}
