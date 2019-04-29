package main.java.com.higgs.graphics;

import main.java.com.higgs.Simulator;
import main.java.com.higgs.obj.actor.Actor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Canvas extends JPanel {
    Canvas() {
        setFocusable(true);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(paint(), 0, 0, null);
    }

    private BufferedImage paint() {
        BufferedImage imageResult = new BufferedImage(Simulator.getInstance().window.getWidth(), Simulator.getInstance().window.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imageResult.createGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Simulator.getInstance().window.getWidth(), Simulator.getInstance().window.getHeight());
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        if(Simulator.getInstance().getWorld() != null) {
            g.drawImage(Simulator.getInstance().getWorld().getImage().getBufferedImage(), 0, 0, null);

            ArrayList<Actor> actors = Simulator.getInstance().getWorld().getActors();
            if(!actors.isEmpty()) {
                for(Actor actor : actors) {
                    BufferedImage imgNew = new BufferedImage(actor.getImage().getWidth(), actor.getImage().getHeight(), actor.getImage().getBufferedImage().getType());
                    Graphics2D g2d = (Graphics2D) imgNew.getGraphics();
                    g2d.rotate(actor.getAngle(), actor.getImage().getWidth() / 2, actor.getImage().getHeight() / 2);
                    g2d.drawImage(actor.getImage().getBufferedImage(), 0, 0, null);
                    g.drawImage(imgNew, (int) (actor.getX() - (actor.getImage().getWidth() / 2)), (int) (actor.getY() - (actor.getImage().getHeight() / 2)), null);
//                    g.drawImage(actor.getImage().getBufferedImage(), (int) (actor.getX() - (actor.getImage().getWidth() / 2)), (int) (actor.getY() - (actor.getImage().getHeight() / 2)), null);
                }
            }
        }

        String fps = String.valueOf(Simulator.getInstance().getDisplayFPS() + " fps");
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.BLACK);
        g.fillRect(2, 2, fm.stringWidth(fps) + 4, fm.getAscent() + 3);
        g.setColor(Color.WHITE);
        g.drawString(fps, 4, 15);
        g.dispose();

        return imageResult;
    }

    public void screenshot() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        File outputFile = new File("Screenshot-" + dateFormat.format(date) + ".png");

        try {
            ImageIO.write(paint(), "png", outputFile);
            System.out.println("Screenshot saved to: " + outputFile.getName());
        } catch(IOException e) {
            System.out.println("Could not save screenshot.");
        }
    }
}
