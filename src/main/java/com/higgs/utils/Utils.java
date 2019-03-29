package main.java.com.higgs.utils;

import java.util.Random;

public class Utils {
    private static String workingDir = System.getProperty("user.dir");

    public static String getWorkingDir() {
        return workingDir;
    }

    public static int randInt(int max) {
        return (int)(Math.random() * max);
    }

    public static double randDouble(double max) {
        return Math.random() * max;
    }

    public static int randInt(int min, int max) {
        return (int)((Math.random() * (max - min)) + min);
    }

    public static double randDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }

    public static double dist(Vector u, Vector v) {
        return dist(u.x, u.y, u.z, v.x, v.y, v.z);
    }

    public static double dist(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));// + Math.pow((z2 - z1), 2));
    }
}
