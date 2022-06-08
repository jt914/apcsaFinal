package Controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import Model.*;

public class Constants {

    public final static int SW = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public final static int SH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.9);

    public static int carStartXNorth = (int) (SW * 0.4018991097922849);
    public static int carStartYNorth = -200;

    public static int carStartXEast = SW + 100;
    public static int carStartYEast = (int) (SH * 0.3518991097922849);

    public static int carStartXSouth = (int) (SW * 0.5318991097922849);
    public static int carStartYSouth = SH + 50;

    public static int carStartXWest = -400;
    public static int carStartYWest = (int) (SH * 0.5318991097922849);

    public static ArrayList<Car> NorthCars = new ArrayList<Car>();
    public static ArrayList<Car> EastCars = new ArrayList<Car>();
    public static ArrayList<Car> SouthCars = new ArrayList<Car>();
    public static ArrayList<Car> WestCars = new ArrayList<Car>();

    public static List<String> paths = new ArrayList<String>();

    public static int topLeftIntersectionX = (int) (SW * 0.3983679525222552);
    public static int topLeftIntersectionY = (int) (SH * 0.3345959595959596);

    public static int botRightIntersectionX = (int) (SW * 0.5964391691394659);
    public static int botRightIntersectionY = (int) (SH * 0.6742424242424242);

    public static int carSpeed = 2;

}
