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

        // moves each car up 1
        // cars.get(i).translateAdd(0, -3);

        // if((((double)cars.get(i).getY())/Constants.SH < 0.55 && (rotate <= 90))){
        // rotateCar(cars.get(i));
        // repaint();

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

    public void updateCarsDone(ArrayList<Car> cars) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).isFinished()) {
                switch (cars.get(i).getDirection()) {
                    case (1): {
                        cars.get(i).translateAdd(0, 1);

                        break;
                    }
                    case (2): {
                        cars.get(i).translateAdd(-1, 0);

                        break;

                    }
                    case (3): {
                        cars.get(i).translateAdd(0, -1);

                        break;
                    }

                    case (4): {
                        cars.get(i).translateAdd(1, 0);
                        break;
                    }

                }
            }

        }

    }

    public void updateOccupied() {
        for (Car c : Constants.NorthCars) {
            if (c.getX() > Constants.topLeftIntersectionX && c.getX() < Constants.botRightIntersectionX
                    && c.getY() > Constants.topLeftIntersectionY && c.getY() < Constants.botRightIntersectionY) {
                isOccupied = true;
                return;
            }
        }
        for (Car c : Constants.EastCars) {
            if (c.getX() > Constants.topLeftIntersectionX && c.getX() < Constants.botRightIntersectionX
                    && c.getY() > Constants.topLeftIntersectionY && c.getY() < Constants.botRightIntersectionY) {
                isOccupied = true;
                return;
            }
        }
        for (Car c : Constants.SouthCars) {
            if (c.getX() > Constants.topLeftIntersectionX && c.getX() < Constants.botRightIntersectionX
                    && c.getY() > Constants.topLeftIntersectionY && c.getY() < Constants.botRightIntersectionY) {
                isOccupied = true;
                return;
            }
        }
        for (Car c : Constants.WestCars) {
            if (c.getX() > Constants.topLeftIntersectionX && c.getX() < Constants.botRightIntersectionX
                    && c.getY() > Constants.topLeftIntersectionY && c.getY() < Constants.botRightIntersectionY) {
                isOccupied = true;
                return;
            }
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
        for (int i = 0; i < Constants.NorthCars.size(); i++) {
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
        int northTime, eastTime, southTime, westTime;

    }

    public void updateCars() {
        cleanCars();
        updateOccupied();
        if (isOccupied) {
            for (int i = 0; i < Constants.NorthCars.size(); i++) {

            }
        }

    }

    // public void updateCars(ArrayList<Car> cars) {
    // if (cars.size() != 0) {
    // Car frontCar = cars.get(0);
    // System.out.println(frontCar.getY());
    // System.out.println(Constants.SH * 0.15);

    // switch (frontCar.getDirection()) {
    // case (1): {
    // if (frontCar.getY() > (Constants.SH * 0.4)) {
    // System.out.println("working");

    // if (!executedNorth) {
    // executedNorth = true;
    // tempTimeNorth = System.currentTimeMillis();
    // System.out.println(tempTimeNorth + "north");

    // }
    // // if(System.currentTimeMillis() > )

    // // check if all other first cars are in a stop sign or the elapsed time is
    // // enough and then continue

    // cars.get(0).stopMoving();
    // // if the current time exceeds however long ago the start time was ago, reset
    // // the current time and continue
    // // continue stops the current car and moves it up to the done grid then set
    // // moving to true

    // // check if the distance to the ahead car is (placeholder) distance, and if
    // it
    // // less then stop moving. If it is greater, set moving to true
    // }
    // break;
    // }
    // case (2): {
    // if (frontCar.getX() < Constants.SW * 0.7) {
    // if (!executedEast) {
    // executedEast = true;
    // tempTimeEast = System.currentTimeMillis();
    // System.out.println(tempTimeWest + "east");

    // }
    // // if(System.currentTimeMillis() > )

    // // check if all other first cars are in a stop sign or the elapsed time is
    // // enough and then continue

    // cars.get(0).stopMoving();
    // // if the current time exceeds however long ago the start time was ago, reset
    // // the current time and continue
    // // continue stops the current car and moves it up to the done grid then set
    // // moving to true

    // // check if the distance to the ahead car is (placeholder) distance, and if
    // it
    // // less then stop moving. If it is greater, set moving to true
    // }
    // break;
    // }

    // case (3): {
    // if (frontCar.getY() < Constants.SH * 0.7) {
    // if (!executedSouth) {
    // executedSouth = true;
    // tempTimeSouth = System.currentTimeMillis();
    // System.out.println(tempTimeWest + "south");

    // }
    // // if(System.currentTimeMillis() > )

    // // check if all other first cars are in a stop sign or the elapsed time is
    // // enough and then continue

    // cars.get(0).stopMoving();
    // // if the current time exceeds however long ago the start time was ago, reset
    // // the current time and continue
    // // continue stops the current car and moves it up to the done grid then set
    // // moving to true

    // // check if the distance to the ahead car is (placeholder) distance, and if
    // it
    // // less then stop moving. If it is greater, set moving to true
    // }
    // break;
    // }

    // case (4): {
    // if (frontCar.getX() > Constants.SW * 0.35) {
    // if (!executedWest) {
    // executedWest = true;
    // tempTimeWest = System.currentTimeMillis();
    // System.out.println(tempTimeWest + "west");

    // }
    // // if(System.currentTimeMillis() > )

    // // check if all other first cars are in a stop sign or the elapsed time is
    // // enough and then continue

    // cars.get(0).stopMoving();
    // // if the current time exceeds however long ago the start time was ago, reset
    // // the current time and continue
    // // continue stops the current car and moves it up to the done grid then set
    // // moving to true

    // // check if the distance to the ahead car is (placeholder) distance, and if
    // it
    // // less then stop moving. If it is greater, set moving to true
    // }

    // break;
    // }

    // }

    // for (int i = 0; i < cars.size(); i++) {

    // switch (cars.get(i).getDirection()) {
    // case (1): {
    // if (cars.get(i).isMoving()) {
    // cars.get(i).translateAdd(0, 1);

    // // if else if here to determine if they are behind another car

    // }
    // if (System.currentTimeMillis() > (tempTimeNorth + 1000)) {
    // System.out.println(System.currentTimeMillis() + " north");

    // Constants.NorthCarsDone.add(cars.get(i));
    // Constants.NorthCars.remove(cars.get(i));

    // }
    // break;

    // }
    // case (2): {
    // if (cars.get(i).isMoving()) {
    // cars.get(i).translateAdd(-1, 0);
    // if (System.currentTimeMillis() > tempTimeEast + 1000) {
    // System.out.println(System.currentTimeMillis() + " east");

    // Constants.EastCarsDone.add(cars.get(i));
    // Constants.EastCars.remove(cars.get(i));
    // }
    // }
    // break;

    // }
    // case (3): {
    // if (cars.get(i).isMoving()) {
    // cars.get(i).translateAdd(0, -1);
    // if (cars.get(i).getY() < Constants.SH * 0.7
    // && System.currentTimeMillis() > tempTimeSouth + 1000) {
    // System.out.println(System.currentTimeMillis() + " south");

    // Constants.SouthCarsDone.add(cars.get(i));
    // Constants.SouthCars.remove(cars.get(i));

    // }
    // }
    // break;
    // }
    // case (4): {
    // if (cars.get(i).isMoving()) {
    // cars.get(i).translateAdd(1, 0);
    // if (cars.get(i).getX() > Constants.SW * 0.35
    // && System.currentTimeMillis() > tempTimeWest + 1000) {
    // System.out.println(System.currentTimeMillis() + " west");

    // Constants.WestCarsDone.add(cars.get(i));
    // Constants.WestCars.remove(cars.get(i));

    // }
    // break;
    // }

    // }

    // }

    // }

    // }

    // }

    // public void rotateCar(Car cars.get(i)) {
    // cars.get(i).rotateImageByDegrees(rotate);
    // rotate++;

    // }
    // remove cars out of boundary/screeen
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

        // for (Car c : Constants.NorthCarsDone) {

        // try {
        // c.draw(g2d);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }

        // for (Car c : Constants.EastCarsDone) {
        // try {
        // c.draw(g2d);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }

        // for (Car c : Constants.WestCarsDone) {
        // try {
        // c.draw(g2d);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }

        // for (Car c : Constants.SouthCarsDone) {
        // try {
        // c.draw(g2d);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }

    }

}