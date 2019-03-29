package main.java.com.higgs.obj.actor;

import main.java.com.higgs.obj.world.World;
import main.java.com.higgs.utils.GameImage;
import main.java.com.higgs.utils.Vector;

public abstract class Actor {
    protected Vector pos; //position vector from origin (0, 0, 0)
    protected Vector vel; //velocity vector (i, j, k)
    protected double rotation;
    protected double omega; //rotational velocity about the center of mass
    protected GameImage image;
    private World currentWorld;

    public abstract void act();


    public void rotate(double rotation) {
        setRotation(getAngle() + rotation);
    }

    public void move(Vector u) {
        setPosition(new Vector(pos.x + u.x, pos.y + u.y, pos.z + u.z));
    }

    public void setPosition(Vector pos) {
        this.pos = pos;
    }

    public void setVelocity(Vector vel) {
        this.vel = vel;
    }

    public void setOmega(double omega) {
        this.omega = omega;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void setImage(GameImage image) {
        this.image = image;
    }

    public void setWorld(World world) {
        this.currentWorld = world;
    }

    public double getX() {
        return pos.x;
    }

    public double getY() {
        return pos.y;
    }

    public Vector getPos() {
        return pos;
    }

    public double getXVel() {
        return vel.x;
    }

    public double getYVel() {
        return vel.y;
    }

    public Vector getVel() {
        return vel;
    }

    public double getSpeed() {
        return vel.getLength();
    }

    public double getAngle() {
        return rotation;
    }

    public double getOmega() {
        return omega;
    }

    public GameImage getImage() {
        return image;
    }

    public World getWorld() {
        return this.currentWorld;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return s.split("@")[1];
    }
}
