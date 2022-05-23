package Controller;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import Model.*;


public class Constants {

    //These variables control screen height and width using java's screen size get thingy (0.9 because it counts the task bar for windows as part of the screen)
    public final static int SW = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public final static int SH = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.9);
    //these variables are basically what percent of the screen size is the right starting position for the car, because if you do it by pixels its gonna become weird on different computers
    public static int carStartX = (int)(SW * 0.5318991097922849);
    public static int carStartY = SH-100;


    //1st list to store cars on the screen, should probably revise for 1 list for each direction, like a list for each car going north east south west
    public static List <Car> cars = new ArrayList<Car>();
    //stores all the names of the images to call later to draw onto the screen
    public static List<String> paths = new ArrayList<String>();



}
