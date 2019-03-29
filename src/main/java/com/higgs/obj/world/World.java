package main.java.com.higgs.obj.world;

import main.java.com.higgs.Simulator;
import main.java.com.higgs.obj.actor.Actor;
import main.java.com.higgs.utils.GameImage;
import main.java.com.higgs.utils.Utils;
import main.java.com.higgs.utils.Vector;

import java.awt.*;
import java.util.ArrayList;

public abstract class World {
    private int width;
    private int height;

    private GameImage image;

    private ArrayList<Actor> actors = new ArrayList<>();

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        Simulator.window.setSize(new Dimension(getWidth(), getHeight()));
        setImage(new GameImage(width, height));
    }


    public abstract void act();


    public void addObject(Actor actor, Vector pos) {
        actor.setPosition(pos);
        actors.add(actor);
        actor.setWorld(this);
    }

    public void removeObject(Actor actor) {
        if(actor != null) {
            if(actors != null) {
                if(!actors.isEmpty()) {
                    if(actors.contains(actor)) {
                        actors.remove(actor);
                    }
                }
            }
        }
    }

    public void setImage(GameImage image) {
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GameImage getImage() {
        return image;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public ArrayList<Actor> getActorsAt(double x, double y) {
        ArrayList<Actor> result = new ArrayList<>();
        for(Actor actor : actors) {
            if(actor.getX() == x && actor.getY() == y) {
                result.add(actor);
            }
        }
        return result;
    }

    public ArrayList<Actor> getActorsInRange(Vector pos, double range) {
        ArrayList<Actor> result = new ArrayList<>();
        for(Actor actor : actors) {
            if(actor != null) {
                if(Utils.dist(pos, actor.getPos()) <= range) {
                    result.add(actor);
                }
            }
        }
        return result;
    }

    public ArrayList<Actor> getActorsInRange(Actor posA, double range) {
        ArrayList<Actor> result = new ArrayList<>();
        for(Actor actor : actors) {
            if(actor != null) {
                if(actor != posA) {
                    if(Utils.dist(posA.getPos(), actor.getPos()) <= range) {
                        result.add(actor);
                    }
                }
            }
        }
        return result;
    }
}
