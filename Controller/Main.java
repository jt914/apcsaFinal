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




public class Main{

    public static void main(String[] args) throws IOException {
        Constants.paths.add("resources\\car1.png"); //Size: 840 x 1150
        Constants.paths.add("resources\\car2.png");
        Constants.paths.add("resources\\car3.png");
        Constants.paths.add("resources\\background.png");

        // Constants.SW = ImageIO.read(new File(Constants.paths.get(3))).getWidth();
        // Constants.SH = ImageIO.read(new File(Constants.paths.get(3))).getHeight();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.SW,Constants.SH);

        MainWindow main = new MainWindow();
        main.setLocation(100,100);
        frame.add(main);

        frame.setVisible(true);





        new Car(new File(Constants.paths.get(0)));
        new Car(new File(Constants.paths.get(1)));
        new Car(new File(Constants.paths.get(2)));
        //thing to do - set size of window to size of background img so never stretched
    }
}
