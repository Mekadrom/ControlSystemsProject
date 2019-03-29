package main.java.com.higgs;

import main.java.com.higgs.content.world.Simulation;
import main.java.com.higgs.graphics.Viewport;
import main.java.com.higgs.obj.actor.Actor;
import main.java.com.higgs.obj.world.World;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Simulator {
    public static Viewport window;
    private static Simulator instance;

    private World world;

    private double displayFPS = 0;

    public static void main(String args[]) {
        new Simulator().init();
    }

    private void init() {
        instance = this;
        window = new Viewport();
        setWorld(new Simulation());
        loop();
    }

    private void loop() {
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        while(true) {
            long start = System.nanoTime(), end, elapsed, delta;

            logic();
            render();

            end = System.nanoTime();
            elapsed = end - start;
            delta = OPTIMAL_TIME - elapsed;

            if(delta >= 0) {
                displayFPS = TARGET_FPS;
                try {
                    Thread.sleep(delta / 1000000);
                } catch(InterruptedException ignored) { }
            } else {
                DecimalFormat df = new DecimalFormat(".##");
                displayFPS = Double.parseDouble(df.format(1 / ((double)elapsed / 1000000000)));
                try {
                    Thread.sleep(0);
                } catch(InterruptedException ignored) { }
            }
        }
    }

    private void logic() {
        if(world != null) world.act();

        Map<Actor, Integer> myMap = new ConcurrentHashMap<>();
        for(int i = 0; i < getWorld().getActors().size(); i++) {
            myMap.put(getWorld().getActors().get(i), i);
        }

        int index = 0;
        Iterator<Actor> iterator = myMap.keySet().iterator();
        while(iterator.hasNext()) {
            if(index < getWorld().getActors().size()) {
                if(getWorld().getActors().get(index) != null) {
                    iterator.next().act();
                }
            } else {
                break;
            }
            index++;
        }
    }

    private void render() {
        window.render();
    }

    public static Simulator getInstance() {
        return instance;
    }

    public double getDisplayFPS() {
        return displayFPS;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }


}
