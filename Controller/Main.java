package Controller;

import java.io.File;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.io.BufferedInputStream;
import java.io.File;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import Model.*;
import View.MainWindow;

public class Main {

    public static void main(String[] args) throws IOException {

        // basically all the images to paintComponent on the screen
        Constants.paths.add("resources\\car1.png"); // Size: 840 x 1150
        Constants.paths.add("resources\\car2.png");
        Constants.paths.add("resources\\car3.png");
        Constants.paths.add("resources\\background.png");

        // Constants.SW = ImageIO.read(new File(Constants.paths.get(3))).getWidth();
        // Constants.SH = ImageIO.read(new File(Constants.paths.get(3))).getHeight();

        // Creates new frame, or window
        JFrame frame = new JFrame();

        // once you hit the little x button its gonna stop the code
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // sets the screen size to the specified lengths in constants
        frame.setSize(Constants.SW, Constants.SH);

        // main window is basically a panel, or part of the frame
        MainWindow main = new MainWindow();

        // setting the locaiton and adding it onto the frame
        main.setLocation(100, 100);
        frame.add(main);

        // makes it visible
        frame.setVisible(true);

        long startTimeInMillis = System.currentTimeMillis();

        int counter = 0;

        new Car(ImageIO.read(new File(Constants.paths.get(0))));

        while (true) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - startTimeInMillis >= 4000) {
                startTimeInMillis = currentTimeMillis;
                new Car(ImageIO.read(new File(Constants.paths.get(0))));
                ++counter;
                if (counter >= 20) {
                    break;
                }
            }
        }

        // construcotr for new car will create the car and paintComponent it using the
        // paint
        // method and stuff, the file parameter is just to tell it the image to
        // paintComponent
        // new Car(ImageIO.read(new File(Constants.paths.get(0))));
        // new Car(ImageIO.read(new File(Constants.paths.get(0))));
        // new Car(ImageIO.read(new File(Constants.paths.get(0))));

        // new Car(new File(Constants.paths.get(1)));
        // new Car(new File(Constants.paths.get(2)));
        // thing to do - set size of window to size of background img so never stretched
    }
}
