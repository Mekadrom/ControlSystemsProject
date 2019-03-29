package main.java.com.higgs.input;

import main.java.com.higgs.Simulator;
import main.java.com.higgs.content.world.Simulation;
import main.java.com.higgs.graphics.Viewport;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch(code) {
            case KeyEvent.VK_F12: {
                Viewport.getCanvas().screenshot();
                break;
            }
            case KeyEvent.VK_ESCAPE: {
                System.exit(0);
            }
            case KeyEvent.VK_SPACE: {
                Simulation sim = (Simulation)(Simulator.getInstance().getWorld());
                sim.setWindSpeed(sim.getWindSpeed() + 0.01);
                break;
            }
            case KeyEvent.VK_SHIFT: {
                Simulation sim = (Simulation)(Simulator.getInstance().getWorld());
                sim.setWindSpeed(sim.getWindSpeed() - 0.01);
                break;
            }
            case KeyEvent.VK_Q: {
                Simulation sim = (Simulation)(Simulator.getInstance().getWorld());
                sim.setRandom(false);
                break;
            }
            case KeyEvent.VK_W: {
                Simulation sim = (Simulation)(Simulator.getInstance().getWorld());
                sim.setRandom(true);
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
