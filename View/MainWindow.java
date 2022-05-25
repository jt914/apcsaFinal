package View;

import java.io.File;
import java.awt.Color;
import java.awt.Dimension;
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
import Controller.*;
import Model.*;

public class MainWindow extends JPanel implements ActionListener {

    // affine transform is weird stuff im not using this rn
    private AffineTransform trans;
    private int rotate = 0;

    public MainWindow() {

        // sets size of window
        setSize(new Dimension(Constants.SW, Constants.SH));

        // basically this timer will trigger the action performed method every 10
        // milliseconds
        new Timer(10, this).start();
    }

    public void actionPerformed(ActionEvent e) {

        // moves each car up 1
        // c.translateAdd(0, -3);

        // if((((double)c.getY())/Constants.SH < 0.55 && (rotate <= 90))){
        // rotateCar(c);
        // repaint();

        // }
        // updateCars(Constants.NorthCars);
        // updateCars(Constants.EastCars);
        // updateCars(Constants.SouthCars);
        // updateCars(Constants.WestCars);

        // pretty sure this just calls the paint component thingy
        repaint();

    }

    // 1 = North
    // 2 = East
    // 3 = South
    // 4 = West
    public void updateCars(ArrayList<Car> cars) {
        for (Car c : cars) {
            switch (c.getDirection()) {
                case (1): {

                    c.translateAdd(c.getX(), c.getY() + 1);
                }
                case (2): {

                    c.translateAdd(c.getX(), c.getY() + 1);
                }
                case (3): {

                    c.translateAdd(c.getX(), c.getY() + 1);
                }
                case (4): {

                    c.translateAdd(c.getX(), c.getY() + 1);
                }
            }
        }

    }

    public void rotateCar(Car c) {
        c.rotateImageByDegrees(rotate);
        rotate++;

    }

    public void paintComponent(Graphics g) {

        // these next two lines you just need for some reason idrk why
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        try {

            // because reading an image might result in failure, you need to surround it in
            // a try catch. You can read up how draw image works, but its pretty simple
            g2d.drawImage(ImageIO.read(new File(Constants.paths.get(3))), 0, 0, Constants.SW, Constants.SH, null);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // draws all the cars
        for (Car c : Constants.NorthCars)
            try {
                c.draw(g2d);
            } catch (IOException e) {
                e.printStackTrace();
            }

        for (Car c : Constants.EastCars)
            try {
                c.draw(g2d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        for (Car c : Constants.WestCars)
            try {
                c.draw(g2d);
            } catch (IOException e) {
                e.printStackTrace();
            }

        for (Car c : Constants.SouthCars)
            try {
                c.draw(g2d);
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

}