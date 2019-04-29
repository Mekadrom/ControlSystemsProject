package main.java.com.higgs.content.actor;

import main.java.com.higgs.content.world.Simulation;
import main.java.com.higgs.obj.actor.Actor;
import main.java.com.higgs.utils.GameImage;

import javax.swing.*;

public class Windmill extends Actor {
    private double brakeTurnOn = 10.0;
    private double brakeTurnOff = 7.0;
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
            applyDefaultFriction();
            applyBrakes();
        }
        if(omega < 0) omega = 0; //prevent from going backwards
        if(omega > hardlimit) alert();
        acts++;
    }

    private void applyDefaultFriction() {
        applyFriction(1.0);
    }

    private void alert() {
        JOptionPane.showMessageDialog(null, "Windmill has broken!", "Failure", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private void applyFriction(double scale) {
        this.omega -= this.omega * 0.0167 * scale;
    }

    private void applyBrakes() {
        double r = getOmega(); //current angular velocity
        double b = brakeTurnOn; //brake limit
        double e = r - b; //error between current and expected angular velocity

        //difference in wind speed between current and expected
        double windspeedDiff = ((Simulation)(getWorld())).getWindSpeed() - 0.17;
        //limits this constant to within brakes' abilities (can't negatively brake and maximal braking is at 15 diff)
        if(windspeedDiff < 0) windspeedDiff = 0;
        if(windspeedDiff >= 15) windspeedDiff = 15;

        //activate brakes when e is greater than zero
        if(e >= 0) {
            braking = true;
        }

        //if currently braking, apply braking friction
        if(braking) {
            applyFriction(0.5 + Math.pow(windspeedDiff, 0.125)); //0.5 + windspeedDiff^(1/8) scales with speed
        }

        //if e <= -3 and brakes are being applied, turn off brakes
        if(e <= brakeTurnOff - brakeTurnOn && braking) {
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
