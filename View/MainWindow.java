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
import java.net.NoRouteToHostException;
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
    private long tempTimeNorth;
    private long tempTimeEast;
    private long tempTimeSouth;
    private long tempTimeWest;

    // this is so that the temptime doesnt keep resetting everytime it checks at the
    // stop sign
    private boolean executedNorth = false;
    private boolean executedEast = false;
    private boolean executedSouth = false;
    private boolean executedWest = false;

    private boolean isOccupied;

    public MainWindow() {

        // sets size of window
        setSize(new Dimension(Constants.SW, Constants.SH));

        // basically this timer will trigger the action performed method every 10
        // milliseconds
        new Timer(5, this).start();
    }

    public void actionPerformed(ActionEvent e) {

        // }
        updateCars();
        // pretty sure this just calls the paint component thingy
        repaint();

    }

    // 1 = North
    // 2 = East
    // 3 = South

    // testing
    // 4 = West

    public void updateOccupied() {
        for (Car c : Constants.NorthCars) {
            if (c.getX() > Constants.topLeftIntersectionX && c.getX() < Constants.botRightIntersectionX
                    && c.getY() > Constants.topLeftIntersectionY - c.imageHeight() + 2
                    && c.getY() < Constants.botRightIntersectionY - c.imageHeight() + 2) {
                isOccupied = true;
                return;
            }
            isOccupied = false;

        }
        for (Car c : Constants.EastCars) {
            if (c.getX() > Constants.topLeftIntersectionX && c.getX() < Constants.botRightIntersectionX
                    && c.getY() > Constants.topLeftIntersectionY && c.getY() < Constants.botRightIntersectionY) {
                isOccupied = true;
                return;
            }
            isOccupied = false;

        }
        for (Car c : Constants.SouthCars) {
            if (c.getX() > Constants.topLeftIntersectionX && c.getX() < Constants.botRightIntersectionX
                    && c.getY() > Constants.topLeftIntersectionY && c.getY() < Constants.botRightIntersectionY) {
                isOccupied = true;
                return;
            }
            isOccupied = false;

        }
        for (Car c : Constants.WestCars) {
            if (c.getX() > Constants.topLeftIntersectionX && c.getX() < Constants.botRightIntersectionX
                    && c.getY() > Constants.topLeftIntersectionY && c.getY() < Constants.botRightIntersectionY) {
                isOccupied = true;
                return;
            }
            isOccupied = false;

        }
    }

    public void cleanCars() {
        for (int i = 0; i < Constants.NorthCars.size(); i++) {
            if (Constants.NorthCars.get(i).getY() >= Constants.SH) {
                Constants.NorthCars.remove(i);
            }
        }
        for (int i = 0; i < Constants.EastCars.size(); i++) {
            if (Constants.EastCars.get(i).getY() <= 0) {
                Constants.EastCars.remove(i);
            }
        }
        for (int i = 0; i < Constants.SouthCars.size(); i++) {
            if (Constants.SouthCars.get(i).getY() <= 0) {
                Constants.SouthCars.remove(i);
            }
        }
        for (int i = 0; i < Constants.WestCars.size(); i++) {
            if (Constants.WestCars.get(i).getX() >= Constants.SW) {
                Constants.WestCars.remove(i);
            }
        }
    }

    public void updateTimer() {
        for (int i = 0; i < Constants.NorthCars.size(); i++) {
            if (!Constants.NorthCars.get(i).isMoving() && !Constants.NorthCars.get(i).isFinished()) {
                Constants.NorthCars.get(i).updateStopTime();
                break;
            }
        }
        for (int i = 0; i < Constants.EastCars.size(); i++) {
            if (!Constants.EastCars.get(i).isMoving() && !Constants.EastCars.get(i).isFinished()) {
                Constants.EastCars.get(i).updateStopTime();
                break;
            }
        }
        for (int i = 0; i < Constants.SouthCars.size(); i++) {
            if (!Constants.SouthCars.get(i).isMoving() && !Constants.SouthCars.get(i).isFinished()) {
                Constants.SouthCars.get(i).updateStopTime();
                break;
            }
        }
        for (int i = 0; i < Constants.WestCars.size(); i++) {
            if (!Constants.WestCars.get(i).isMoving() && !Constants.WestCars.get(i).isFinished()) {
                Constants.WestCars.get(i).updateStopTime();
                break;
            }
        }
    }

    public int rightOfWay() {
        int northTime = 0;
        int eastTime = 0;
        int southTime = 0;
        int westTime = 0;
        for (int i = 0; i < Constants.NorthCars.size(); i++) {
            if (!Constants.NorthCars.get(i).isMoving() && !Constants.NorthCars.get(i).isFinished()) {
                northTime = Constants.NorthCars.get(i).getStopTime();
                break;
            }
        }
        for (int i = 0; i < Constants.EastCars.size(); i++) {
            if (!Constants.EastCars.get(i).isMoving() && !Constants.EastCars.get(i).isFinished()) {
                eastTime = Constants.EastCars.get(i).getStopTime();
                break;
            }
        }
        for (int i = 0; i < Constants.SouthCars.size(); i++) {
            if (!Constants.SouthCars.get(i).isMoving() && !Constants.SouthCars.get(i).isFinished()) {
                southTime = Constants.SouthCars.get(i).getStopTime();
                break;
            }
        }
        for (int i = 0; i < Constants.WestCars.size(); i++) {
            if (!Constants.WestCars.get(i).isMoving() && !Constants.WestCars.get(i).isFinished()) {
                westTime = Constants.WestCars.get(i).getStopTime();
                break;
            }
        }
        if (northTime > eastTime && northTime > southTime && northTime > westTime) {
            return 1;
        } else if (eastTime > northTime && eastTime > southTime && eastTime > westTime) {
            return 2;
        } else if (southTime > northTime && southTime > eastTime && southTime > westTime) {
            return 2;
        } else {
            return 4;
        }

    }

    public boolean isFirstCarNotStarted(Car c, ArrayList<Car> cars) {
        Car firstCar = cars.get(0);
        for (int i = 0; i < cars.size(); i++) {
            if (!cars.get(i).isStarted()) {
                firstCar = cars.get(i);
                break;
            }
        }
        return c.getId() == firstCar.getId();
    }

    public void updateCars() {
        cleanCars();
        updateOccupied();
        // System.out.println(isOccupied);

        for (int i = 0; i < Constants.NorthCars.size(); i++) {
            if(!Constants.NorthCars.get(i).isFinished()){

            if (!Constants.NorthCars.get(i).isStarted()) {
                if (!isOccupied) {

                    if (Constants.NorthCars.get(i).isMoving()) {

                        if (isFirstCarNotStarted(Constants.NorthCars.get(i), Constants.NorthCars)) {
                            // System.out.println(Constants.NorthCars.get(i).getY());
                            // System.out.println(Constants.topLeftIntersectionY
                            // - Constants.NorthCars.get(i).imageHeight());
                            if (Constants.NorthCars.get(i).getY() >= (Constants.topLeftIntersectionY
                                    - Constants.NorthCars.get(i).imageHeight())
                                    && Constants.NorthCars.get(i).getStopTime() < 101) {
                                Constants.NorthCars.get(i).stopMoving();
                                Constants.NorthCars.get(i).updateStopTime();
                                // System.out.println(i);
                                // System.out.println("first car stop moving");

                            }
                        } else if (i != 0
                                && Constants.NorthCars.get(i - 1).getY() - Constants.NorthCars.get(i).getY() < 10
                                        + Constants.NorthCars.get(i).imageHeight()
                                && !Constants.NorthCars.get(i).isFinished()) {
                            // if (Constants.NorthCars.get(i - 1).getY() - Constants.NorthCars.get(i).getY()
                            // < 10
                            // + Constants.NorthCars.get(i).imageHeight()
                            // && !Constants.NorthCars.get(i).isFinished()) {
                            Constants.NorthCars.get(i).stopMoving();
                            // System.out.println("second car stop moving");

                            // }
                        }
                    } else {

                        if (isFirstCarNotStarted(Constants.NorthCars.get(i), Constants.NorthCars)) {
                            // System.out.println(Constants.NorthCars.get(i).getStopTime());
                            if (Constants.NorthCars.get(i).getStopTime() < 100) {
                                Constants.NorthCars.get(i).updateStopTime();

                            } else {
                                Constants.NorthCars.get(i).doAction();
                                Constants.NorthCars.get(i).startMoving();
                                // System.out.println("starting to move first car");
                                Constants.NorthCars.get(i).startAction();
                                System.out.println(Constants.NorthCars.get(i).isStarted());

                            }
                        } else {
                            if (i != 0 && Constants.NorthCars.get(i - 1).getY() - Constants.NorthCars.get(i).getY() > 10
                                    + Constants.NorthCars.get(i).imageHeight()) {
                                Constants.NorthCars.get(i).startMoving();
                                // System.out.println("starting to move second car");

                            }
                        }
                    }
                } else

                {
                    int rightOfWayDirection = rightOfWay();

                    if (!Constants.NorthCars.get(i).isStarted()) {

                        if (Constants.NorthCars.get(i).isMoving()) {

                            if (isFirstCarNotStarted(Constants.NorthCars.get(i), Constants.NorthCars)) {

                                // if its the first car not started, then keep moving until it reaches the
                                // intersection
                                // if its not the first car not started, then keep moving until it reaches the
                                // car behind the intersection

                                // System.out.println(Constants.NorthCars.get(i).getY());
                                // System.out.println(Constants.topLeftIntersectionY
                                // - Constants.NorthCars.get(i).imageHeight());
                                if (Constants.NorthCars.get(i).getY() >= (Constants.topLeftIntersectionY
                                        - Constants.NorthCars.get(i).imageHeight())) {
                                    Constants.NorthCars.get(i).stopMoving();
                                    Constants.NorthCars.get(i).updateStopTime();
                                    // System.out.println(i);
                                    // System.out.println("first car stop moving");

                                }
                            } else if (i != 0
                                    && Constants.NorthCars.get(i - 1).getY() - Constants.NorthCars.get(i).getY() < 10
                                            + Constants.NorthCars.get(i).imageHeight()
                                    && !Constants.NorthCars.get(i).isFinished()) {
                                // if (Constants.NorthCars.get(i - 1).getY() - Constants.NorthCars.get(i).getY()
                                // < 10
                                // + Constants.NorthCars.get(i).imageHeight()
                                // && !Constants.NorthCars.get(i).isFinished()) {
                                Constants.NorthCars.get(i).stopMoving();
                                // System.out.println("second car stop moving");

                            }
                            // }
                        } else {

                            if (isFirstCarNotStarted(Constants.NorthCars.get(i), Constants.NorthCars)) {
                                if (Constants.NorthCars.get(i).getY() < (Constants.topLeftIntersectionY
                                        - Constants.NorthCars.get(i).imageHeight())) {
                                    Constants.NorthCars.get(i).startMoving();
                                }
                                // System.out.println(Constants.NorthCars.get(i).getStopTime());
                                if (rightOfWayDirection != Constants.NorthCars.get(i).getDirection()
                                        && (Constants.NorthCars.get(i).getY() >= (Constants.topLeftIntersectionY
                                                - Constants.NorthCars.get(i).imageHeight()))) {
                                    Constants.NorthCars.get(i).updateStopTime();

                                    // } else {
                                    // Constants.NorthCars.get(i).doAction();
                                    // Constants.NorthCars.get(i).startMoving();
                                    // System.out.println("starting to move first car");
                                    // Constants.NorthCars.get(i).startAction();
                                    // System.out.println(Constants.NorthCars.get(i).isStarted());

                                }
                            } else {
                                if (i != 0 && Constants.NorthCars.get(i - 1).getY() -
                                        Constants.NorthCars.get(i).getY() > 10
                                                + Constants.NorthCars.get(i).imageHeight()) {
                                    Constants.NorthCars.get(i).startMoving();
                                    // System.out.println("starting to move second car");
                                }
                            }
                        }
                    }
                }
            } else if (Constants.NorthCars.get(i).isStarted()) {
                Constants.NorthCars.get(i).doAction();
                Constants.NorthCars.get(i).setActionCase(2);
                // System.out.println("works");

            }

        }
    }
    else{
        //remove from 
    }

        moveCars();

    }

    public void moveCars() {
        for (Car c : Constants.NorthCars) {
            if (c.isMoving() && !c.isStarted())
                c.translateAdd(0, 2);
        }
        for (Car c : Constants.EastCars) {
            if (c.isMoving() && !c.isStarted())
                c.translateAdd(-1, 0);
        }
        for (Car c : Constants.SouthCars) {
            if (c.isMoving() && !c.isStarted())
                c.translateAdd(0, -1);
        }
        for (Car c : Constants.WestCars) {
            if (c.isMoving() && !c.isStarted())
                c.translateAdd(1, 0);
        }
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
        for (Car c : Constants.NorthCars) {
            try {
                c.draw(g2d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Car c : Constants.EastCars) {
            try {
                c.draw(g2d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Car c : Constants.WestCars) {
            try {
                c.draw(g2d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Car c : Constants.SouthCars) {
            try {
                c.draw(g2d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}