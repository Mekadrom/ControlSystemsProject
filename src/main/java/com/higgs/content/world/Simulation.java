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

    private int acts = 0;

    @Override
    public void act() {
        if(acts % 5 == 0) { //num of act loops modulo 5; runs the code in the if statement every 5 acts or 12 times/sec
            generateWind();
        }
        acts++;
    }

    private void generateWind() {
        if(random) { //state variable; determines whether or not to randomize the wind or to allow manual control
            double vary = Math.random() * variance; //randomly generated variance
            windSpeed = windSpeed + (vary - (0.5 * variance)); //randomly changes the wind from its current value
        } else {
            windSpeed = Double.parseDouble(new DecimalFormat("#.##").format(windSpeed));
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
