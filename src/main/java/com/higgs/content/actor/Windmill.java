package main.java.com.higgs.content.actor;

import main.java.com.higgs.content.world.Simulation;
import main.java.com.higgs.obj.actor.Actor;
import main.java.com.higgs.utils.GameImage;

import javax.swing.*;

public class Windmill extends Actor {
    private double limit = 10.0;
    private double hardlimit = 17.0;
    private int acts = 0;
    private boolean braking = false;

    public Windmill() {
        setImage(new GameImage("windmill.png"));
    }

    @Override
    public void act() {
        if(acts % 1 == 0) {
            rotate(Math.toRadians(omega));
            applyWind();
            applyFriction();
            applyBrakes();
        }
        if(omega < 0) omega = 0;
        if(omega > hardlimit) alert();
        acts++;
    }

    private void alert() {
        JOptionPane.showMessageDialog(null, "Windmill has broken!", "Failure", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private void applyFriction() {
        omega -= omega * 0.0165;
    }

    private void applyBrakes() {
        double r = omega;
        double b = limit;
        double e = r - b;
        if(e > 0) {
            braking = true;
            applyFriction();
        } else {
            braking = false;
        }
    }

    private void applyWind() {
        omega += ((Simulation)(getWorld())).getWindSpeed();
    }

    public boolean isBraking() {
        return this.braking;
    }
}
