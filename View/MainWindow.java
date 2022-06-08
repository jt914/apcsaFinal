package View;

import java.io.File;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import Controller.*;
import Model.*;

public class MainWindow extends JPanel implements ActionListener {

    private int rightOfWay;

    private boolean isOccupied;

    public MainWindow() {

        setSize(new Dimension(Constants.SW, Constants.SH));

        new Timer(5, this).start();
    }

    public void actionPerformed(ActionEvent e) {

        updateCars();

        repaint();

    }

    public void updateOccupied() {

        for (Car c : Constants.NorthCars) {
            if (c.isStarted() && !c.isFinished()) {
                isOccupied = true;
                return;
            }
        }

        for (Car c : Constants.EastCars) {
            if (c.isStarted() && !c.isFinished()) {
                isOccupied = true;
                return;
            }
        }

        for (Car c : Constants.SouthCars) {
            if (c.isStarted() && !c.isFinished()) {
                isOccupied = true;
                return;
            }
        }

        for (Car c : Constants.WestCars) {
            if (c.isStarted() && !c.isFinished()) {
                isOccupied = true;
                return;
            }
        }
        isOccupied = false;
        return;
    }

    public void cleanCars() {
        for (int i = 0; i < Constants.NorthCars.size(); i++) {
            if (Constants.NorthCars.get(i).getY() >= Constants.SH && Constants.NorthCars.get(i).isFinished()) {
                Constants.NorthCars.remove(i);
            }
        }
        for (int i = 0; i < Constants.EastCars.size(); i++) {
            if (Constants.EastCars.get(i).getX() <= 0 && Constants.EastCars.get(i).isFinished()) {
                Constants.EastCars.remove(i);
            }
        }
        for (int i = 0; i < Constants.SouthCars.size(); i++) {
            if (Constants.SouthCars.get(i).getY() <= 0 && Constants.SouthCars.get(i).isFinished()) {
                Constants.SouthCars.remove(i);
            }
        }
        for (int i = 0; i < Constants.WestCars.size(); i++) {
            if (Constants.WestCars.get(i).getX() >= Constants.SW && Constants.WestCars.get(i).isFinished()) {
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
            if (!Constants.NorthCars.get(i).isMoving() && !Constants.NorthCars.get(i).isStarted()
                    && Constants.NorthCars.get(i).getStopTime() > northTime) {
                northTime = Constants.NorthCars.get(i).getStopTime();
                break;
            }
        }
        for (int i = 0; i < Constants.EastCars.size(); i++) {
            if (!Constants.EastCars.get(i).isMoving() && !Constants.EastCars.get(i).isStarted()
                    && Constants.EastCars.get(i).getStopTime() > eastTime) {
                eastTime = Constants.EastCars.get(i).getStopTime();
                break;
            }
        }
        for (int i = 0; i < Constants.SouthCars.size(); i++) {
            if (!Constants.SouthCars.get(i).isMoving() && !Constants.SouthCars.get(i).isStarted()
                    && Constants.SouthCars.get(i).getStopTime() > southTime) {
                southTime = Constants.SouthCars.get(i).getStopTime();
                break;
            }
        }
        for (int i = 0; i < Constants.WestCars.size(); i++) {
            if (!Constants.WestCars.get(i).isMoving() && !Constants.WestCars.get(i).isStarted()
                    && Constants.WestCars.get(i).getStopTime() > westTime) {
                westTime = Constants.WestCars.get(i).getStopTime();
                break;
            }

        }

        if (northTime > eastTime && northTime > southTime && northTime > westTime) {
            return 1;
        } else if (eastTime > northTime && eastTime > southTime && eastTime > westTime) {
            return 2;
        } else if (southTime > northTime && southTime > eastTime && southTime > westTime) {
            return 3;
        } else if (westTime > northTime && westTime > southTime && westTime > eastTime) {
            return 4;
        }
        return 0;

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

        if (rightOfWay == 0) {
            rightOfWay = rightOfWay();
        } else {
            if (rightOfWay == rightOfWay()) {
                rightOfWay = rightOfWay();
            } else {
                rightOfWay = 0;
            }
        }

        if (Constants.NorthCars.size() > 0) {

            for (int i = 0; i < Constants.NorthCars.size(); i++) {

                if (!Constants.NorthCars.get(i).isFinished()) {

                    if (!Constants.NorthCars.get(i).isStarted()) {
                        if (!isOccupied) {

                            if (Constants.NorthCars.get(i).isMoving()) {

                                if (isFirstCarNotStarted(Constants.NorthCars.get(i), Constants.NorthCars)) {

                                    if (Constants.NorthCars.get(i).getY() >= (Constants.topLeftIntersectionY
                                            - Constants.NorthCars.get(i).imageHeight())
                                            && Constants.NorthCars.get(i).getStopTime() < 51) {
                                        Constants.NorthCars.get(i).stopMoving();
                                        Constants.NorthCars.get(i).updateStopTime();

                                    }
                                } else if (i != 0
                                        && Constants.NorthCars.get(i - 1).getY()
                                                - Constants.NorthCars.get(i).getY() < 10
                                                        + Constants.NorthCars.get(i).imageHeight()
                                        && !Constants.NorthCars.get(i).isFinished()) {

                                    Constants.NorthCars.get(i).stopMoving();

                                }
                            } else {

                                if (isFirstCarNotStarted(Constants.NorthCars.get(i), Constants.NorthCars)) {

                                    if (Constants.NorthCars.get(i).getStopTime() < 51) {
                                        Constants.NorthCars.get(i).updateStopTime();

                                    } else {
                                        if (rightOfWay == 1) {
                                            Constants.NorthCars.get(i).startMoving();
                                            // System.out.println(" working");


                                            Constants.NorthCars.get(i).startAction();

                                            Constants.NorthCars.get(i).doAction();

                                        } else {
                                            Constants.NorthCars.get(i).updateStopTime();
                                        }
                                    }
                                } else {
                                    if (i != 0 && Constants.NorthCars.get(i - 1).getY()
                                            - Constants.NorthCars.get(i).getY() > 10
                                                    + Constants.NorthCars.get(i).imageHeight()) {
                                        Constants.NorthCars.get(i).startMoving();

                                    }
                                }
                            }
                        } else

                        {

                            if (!Constants.NorthCars.get(i).isStarted()) {

                                if (Constants.NorthCars.get(i).isMoving()) {

                                    if (isFirstCarNotStarted(Constants.NorthCars.get(i), Constants.NorthCars)) {

                                        if (Constants.NorthCars.get(i).getY() >= (Constants.topLeftIntersectionY
                                                - Constants.NorthCars.get(i).imageHeight())) {
                                            Constants.NorthCars.get(i).stopMoving();
                                            Constants.NorthCars.get(i).updateStopTime();

                                        }
                                    } else if (i != 0
                                            && Constants.NorthCars.get(i - 1).getY()
                                                    - Constants.NorthCars.get(i).getY() < 10
                                                            + Constants.NorthCars.get(i).imageHeight()
                                            && !Constants.NorthCars.get(i).isFinished()) {

                                        Constants.NorthCars.get(i).stopMoving();

                                    }

                                } else {

                                    if (isFirstCarNotStarted(Constants.NorthCars.get(i), Constants.NorthCars)) {
                                        if (Constants.NorthCars.get(i).getY() < (Constants.topLeftIntersectionY
                                                - Constants.NorthCars.get(i).imageHeight())) {
                                            Constants.NorthCars.get(i).startMoving();
                                        }

                                        else {
                                            Constants.NorthCars.get(i).updateStopTime();

                                        }
                                    } else {
                                        if (i != 0 && Constants.NorthCars.get(i - 1).getY() -
                                                Constants.NorthCars.get(i).getY() > 10
                                                        + Constants.NorthCars.get(i).imageHeight()) {
                                            Constants.NorthCars.get(i).startMoving();

                                        }
                                    }
                                }
                            }
                        }
                    } else if (Constants.NorthCars.get(i).isStarted()) {

                        Constants.NorthCars.get(i).doAction();

                    }

                }
            }
        }

        if (Constants.EastCars.size() > 0) {
            for (int i = 0; i < Constants.EastCars.size(); i++) {

                if (!Constants.EastCars.get(i).isFinished()) {

                    if (!Constants.EastCars.get(i).isStarted()) {
                        if (!isOccupied) {

                            if (Constants.EastCars.get(i).isMoving()) {

                                if (isFirstCarNotStarted(Constants.EastCars.get(i), Constants.EastCars)) {

                                    if (Constants.EastCars.get(i).getX() <= (Constants.botRightIntersectionX) + 4
                                            && Constants.EastCars.get(i).getStopTime() < 51) {

                                        Constants.EastCars.get(i).stopMoving();
                                        Constants.EastCars.get(i).updateStopTime();

                                    }
                                } else if (i != 0
                                        && Constants.EastCars.get(i).getX() - Constants.EastCars.get(i - 1).getX() < (10
                                                + Constants.EastCars.get(i).imageWidth())
                                        && !Constants.EastCars.get(i).isFinished()) {

                                    Constants.EastCars.get(i).stopMoving();

                                }
                            } else {

                                if (isFirstCarNotStarted(Constants.EastCars.get(i), Constants.EastCars)) {

                                    if (isFirstCarNotStarted(Constants.EastCars.get(i), Constants.EastCars)) {

                                        if (Constants.EastCars.get(i).getStopTime() < 51) {
                                            Constants.EastCars.get(i).updateStopTime();

                                        } else {
                                            if (rightOfWay == 2) {
                                                Constants.EastCars.get(i).startMoving();

                                                Constants.EastCars.get(i).startAction();

                                                Constants.EastCars.get(i).doAction();

                                            } else {
                                                Constants.EastCars.get(i).updateStopTime();
                                            }
                                        }
                                    }
                                } else {
                                    if (i != 0
                                            && Constants.EastCars.get(i).getX()
                                                    - Constants.EastCars.get(i - 1).getX() >= (10
                                                            + Constants.EastCars.get(i).imageWidth())
                                            && !Constants.EastCars.get(i).isFinished()) {
                                        Constants.EastCars.get(i).startMoving();

                                    }
                                }
                            }
                        } else

                        {

                            if (!Constants.EastCars.get(i).isStarted()) {

                                if (Constants.EastCars.get(i).isMoving()) {

                                    if (isFirstCarNotStarted(Constants.EastCars.get(i), Constants.EastCars)) {

                                        if (Constants.EastCars.get(i).getX() <= (Constants.botRightIntersectionX)) {
                                            Constants.EastCars.get(i).stopMoving();
                                            Constants.EastCars.get(i).updateStopTime();

                                        }

                                    } else if (i != 0
                                            && Constants.EastCars.get(i).getX()
                                                    - Constants.EastCars.get(i - 1).getX() < (10
                                                            + Constants.EastCars.get(i).imageWidth())
                                            && !Constants.EastCars.get(i).isFinished()) {

                                        Constants.EastCars.get(i).stopMoving();

                                    }

                                } else {

                                    if (isFirstCarNotStarted(Constants.EastCars.get(i), Constants.EastCars)) {
                                        if (Constants.EastCars.get(i).getX() >= (Constants.botRightIntersectionX)) {
                                            Constants.EastCars.get(i).startMoving();
                                        } else {

                                            Constants.EastCars.get(i).updateStopTime();

                                        }
                                    } else {
                                        if (i != 0
                                                && Constants.EastCars.get(i).getX()
                                                        - Constants.EastCars.get(i - 1).getX() >= (10
                                                                + Constants.EastCars.get(i).imageWidth())
                                                && !Constants.EastCars.get(i).isFinished()) {
                                            Constants.EastCars.get(i).startMoving();

                                        }
                                    }
                                }
                            }
                        }
                    } else if (Constants.EastCars.get(i).isStarted()) {
                        Constants.EastCars.get(i).doAction();

                    }

                }
            }
        }
        if (Constants.SouthCars.size() > 0) {
            for (int i = 0; i < Constants.SouthCars.size(); i++) {

                if (!Constants.SouthCars.get(i).isFinished()) {

                    if (!Constants.SouthCars.get(i).isStarted()) {

                        if (!isOccupied) {

                            if (Constants.SouthCars.get(i).isMoving()) {

                                if (isFirstCarNotStarted(Constants.SouthCars.get(i), Constants.SouthCars)) {

                                    if (Constants.SouthCars.get(i).getY() <= (Constants.botRightIntersectionY) + 4
                                            && Constants.SouthCars.get(i).getStopTime() < 51) {

                                        Constants.SouthCars.get(i).stopMoving();
                                        Constants.SouthCars.get(i).updateStopTime();

                                    }
                                } else if (i != 0
                                        && Constants.SouthCars.get(i).getY()
                                                - Constants.SouthCars.get(i - 1).getY() < (10
                                                        + Constants.SouthCars.get(i).imageHeight())
                                        && !Constants.SouthCars.get(i).isFinished()) {

                                    Constants.SouthCars.get(i).stopMoving();

                                }
                            } else {

                                if (isFirstCarNotStarted(Constants.SouthCars.get(i), Constants.SouthCars)) {

                                    if (Constants.SouthCars.get(i).getStopTime() < 51) {
                                        Constants.SouthCars.get(i).updateStopTime();

                                    } else {
                                        if (rightOfWay == 3) {
                                            Constants.SouthCars.get(i).startMoving();

                                            Constants.SouthCars.get(i).startAction();

                                            Constants.SouthCars.get(i).doAction();

                                        } else {
                                            Constants.SouthCars.get(i).updateStopTime();
                                        }
                                    }

                                } else {
                                    if (i != 0
                                            && Constants.SouthCars.get(i).getY()
                                                    - Constants.SouthCars.get(i - 1).getY() >= (10
                                                            + Constants.SouthCars.get(i).imageHeight())
                                            && !Constants.SouthCars.get(i).isFinished()) {
                                        Constants.SouthCars.get(i).startMoving();

                                    }
                                }
                            }
                        } else

                        {

                            if (!Constants.SouthCars.get(i).isStarted()) {

                                if (Constants.SouthCars.get(i).isMoving()) {

                                    if (isFirstCarNotStarted(Constants.SouthCars.get(i), Constants.SouthCars)) {

                                        if (Constants.SouthCars.get(i)
                                                .getY() <= (Constants.botRightIntersectionY + 10)) {
                                            Constants.SouthCars.get(i).stopMoving();
                                            Constants.SouthCars.get(i).updateStopTime();

                                        }
                                    } else if (i != 0
                                            && Constants.SouthCars.get(i).getY()
                                                    - Constants.SouthCars.get(i - 1).getY() < (10
                                                            + Constants.SouthCars.get(i).imageHeight())
                                            && !Constants.SouthCars.get(i).isFinished()) {

                                        Constants.SouthCars.get(i).stopMoving();

                                    }

                                } else {

                                    if (isFirstCarNotStarted(Constants.SouthCars.get(i), Constants.SouthCars)) {
                                        if (Constants.SouthCars.get(i).getY() >= (Constants.botRightIntersectionY)) {
                                            Constants.SouthCars.get(i).startMoving();
                                        }

                                        else {

                                            Constants.SouthCars.get(i).updateStopTime();

                                        }
                                    } else {
                                        if (i != 0
                                                && Constants.SouthCars.get(i).getY()
                                                        - Constants.SouthCars.get(i - 1).getY() >= (10
                                                                + Constants.SouthCars.get(i).imageHeight())
                                                && !Constants.SouthCars.get(i).isFinished()) {
                                            Constants.SouthCars.get(i).startMoving();

                                        }
                                    }
                                }
                            }
                        }
                    } else if (Constants.SouthCars.get(i).isStarted()) {
                        Constants.SouthCars.get(i).doAction();

                    }

                }
            }

        }
        if (Constants.WestCars.size() > 0) {
            for (int i = 0; i < Constants.WestCars.size(); i++) {

                if (!Constants.WestCars.get(i).isFinished()) {

                    if (!Constants.WestCars.get(i).isStarted()) {
                        if (!isOccupied) {

                            if (Constants.WestCars.get(i).isMoving()) {

                                if (isFirstCarNotStarted(Constants.WestCars.get(i), Constants.WestCars)) {

                                    if (Constants.WestCars.get(i)
                                            .getX() >= (Constants.topLeftIntersectionX
                                                    - Constants.WestCars.get(i).imageWidth()) - 4
                                            && Constants.WestCars.get(i).getStopTime() < 51) {

                                        Constants.WestCars.get(i).stopMoving();
                                        Constants.WestCars.get(i).updateStopTime();

                                    }
                                } else if (i != 0
                                        && Constants.WestCars.get(i - 1).getX() - Constants.WestCars.get(i).getX() < (10
                                                + Constants.WestCars.get(i).imageWidth())
                                        && !Constants.WestCars.get(i).isFinished()) {

                                    Constants.WestCars.get(i).stopMoving();

                                }
                            } else {

                                if (isFirstCarNotStarted(Constants.WestCars.get(i), Constants.WestCars)) {

                                    if (isFirstCarNotStarted(Constants.WestCars.get(i), Constants.WestCars)) {

                                        if (Constants.WestCars.get(i).getStopTime() < 51) {
                                            Constants.WestCars.get(i).updateStopTime();

                                        } else {
                                            if (rightOfWay == 4) {

                                                Constants.WestCars.get(i).startMoving();

                                                Constants.WestCars.get(i).startAction();

                                                Constants.WestCars.get(i).doAction();

                                            } else {
                                                Constants.WestCars.get(i).updateStopTime();
                                            }
                                        }
                                    }
                                } else {
                                    if (i != 0
                                            && Constants.WestCars.get(i - 1).getX()
                                                    - Constants.WestCars.get(i).getX() >= (10
                                                            + Constants.WestCars.get(i).imageWidth())
                                            && !Constants.WestCars.get(i).isFinished()) {
                                        Constants.WestCars.get(i).startMoving();

                                    }
                                }
                            }
                        } else

                        {

                            if (!Constants.WestCars.get(i).isStarted()) {

                                if (Constants.WestCars.get(i).isMoving()) {

                                    if (isFirstCarNotStarted(Constants.WestCars.get(i), Constants.WestCars)) {

                                        if (Constants.WestCars.get(i).getX() >= (Constants.topLeftIntersectionX
                                                - Constants.WestCars.get(i).imageWidth())) {
                                            Constants.WestCars.get(i).stopMoving();
                                            Constants.WestCars.get(i).updateStopTime();

                                        }
                                    } else if (i != 0
                                            && Constants.WestCars.get(i - 1).getX()
                                                    - Constants.WestCars.get(i).getX() < (10
                                                            + Constants.WestCars.get(i).imageWidth())
                                            && !Constants.WestCars.get(i).isFinished()) {

                                        Constants.WestCars.get(i).stopMoving();

                                    }

                                } else {

                                    if (isFirstCarNotStarted(Constants.WestCars.get(i), Constants.WestCars)) {
                                        if (Constants.WestCars.get(i).getX() <= (Constants.topLeftIntersectionX
                                                - Constants.WestCars.get(i).imageWidth())) {
                                            Constants.WestCars.get(i).startMoving();
                                        } else {

                                            Constants.WestCars.get(i).updateStopTime();

                                        }
                                    } else {
                                        if (i != 0
                                                && Constants.WestCars.get(i - 1).getX()
                                                        - Constants.WestCars.get(i).getX() >= (10
                                                                + Constants.WestCars.get(i).imageWidth())
                                                && !Constants.WestCars.get(i).isFinished()) {
                                            Constants.WestCars.get(i).startMoving();

                                        }
                                    }
                                }
                            }
                        }
                    } else if (Constants.WestCars.get(i).isStarted()) {
                        Constants.WestCars.get(i).doAction();

                    }

                }
            }
        }

        moveCars();

        moveFinishedCars();

    }

    public void moveFinishedCars() {
        for (Car c : Constants.NorthCars) {
            if (c.isFinished()) {
                c.translateAdd(0, Constants.carSpeed);
            }
        }
        for (Car c : Constants.EastCars) {
            if (c.isFinished()) {
                c.translateAdd(-Constants.carSpeed, 0);

            }
        }
        for (Car c : Constants.SouthCars) {
            if (c.isFinished()) {
                c.translateAdd(0, -Constants.carSpeed);
            }
        }
        for (Car c : Constants.WestCars) {
            if (c.isFinished()) {
                c.translateAdd(Constants.carSpeed, 0);
            }
        }
    }

    public void moveCars() {
        for (Car c : Constants.NorthCars) {
            if (c.isMoving() && !c.isStarted()) {
                c.translateAdd(0, Constants.carSpeed);


            }
        }
        for (Car c : Constants.EastCars) {
            if (c.isMoving() && !c.isStarted())
                c.translateAdd(-Constants.carSpeed, 0);

        }
        for (Car c : Constants.SouthCars) {
            if (c.isMoving() && !c.isStarted())
                c.translateAdd(0, -Constants.carSpeed);
        }
        for (Car c : Constants.WestCars) {
            if (c.isMoving() && !c.isStarted())
                c.translateAdd(Constants.carSpeed, 0);
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        try {

            g2d.drawImage(ImageIO.read(new File(Constants.paths.get(3))), 0, 0, Constants.SW, Constants.SH, null);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

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