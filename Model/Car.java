package Model;

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
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import Controller.*;

public class Car extends JPanel {

    private BufferedImage master, rotated;
    private int direction, x, y, actionCase, actionStep;
    private boolean moving, startedAction;
    private int stopTime;
    private boolean finishedAction;
    public static int currentId;
    private int id, imageWidth, imageHeight;

    public Car(Image o) {
        master = (BufferedImage) o;
        rotated = master;
        id = currentId;
        currentId++;
        startedAction = false;

        // direction = 1;
        // int dOfTravel = 1;
        int dOfTravel = (int) (Math.random() * 4) + 1;
        direction = dOfTravel;
        // System.out.println(direction);

        switch (direction) {
            case (1): {
                imageHeight = (int) (Constants.SH * 0.1893939393939394);
                break;

            }
            case (2): {

                imageHeight = (int) (Constants.SW * 0.0808605341246291);
                break;

            }
            case (3): {
                imageHeight = (int) (Constants.SH * 0.1893939393939394);
                break;

            }
            case (4): {
                imageHeight = (int) (Constants.SW * 0.0808605341246291);
                break;

            }
        }
        switch (direction) {
            case (1): {
                imageWidth = (int) (Constants.SW * 0.0808605341246291);
                break;
            }
            case (2): {
                imageWidth = (int) (Constants.SH * 0.1893939393939394);
                break;

            }
            case (3): {
                imageWidth = (int) (Constants.SW * 0.0808605341246291);
                break;

            }
            case (4): {
                imageWidth = (int) (Constants.SH * 0.1893939393939394);
                break;
            }
        }

        moving = true;
        actionCase = (int) (Math.random() * 2) + 1;
        // actionCase = 1;
        // System.out.println(actionCase);

        switch (dOfTravel) {
            case (1): {
                Constants.NorthCars.add(this);
                x = Constants.carStartXNorth;
                y = Constants.carStartYNorth;
                try {
                    master = ImageIO.read(new File("resources\\car1Rotated180.png"));
                    rotated = master;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            }
            case (2): {

                Constants.EastCars.add(this);
                x = Constants.carStartXEast;
                y = Constants.carStartYEast;
                try {
                    master = ImageIO.read(new File("resources\\car1Rotated270.png"));
                    rotated = master;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            }
            case (3): {
                Constants.SouthCars.add(this);
                x = Constants.carStartXSouth;
                y = Constants.carStartYSouth;
                try {
                    master = ImageIO.read(new File("resources\\car1.png"));
                    rotated = master;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            }
            case (4): {
                Constants.WestCars.add(this);
                x = Constants.carStartXWest;
                y = Constants.carStartYWest;
                try {
                    master = ImageIO.read(new File("resources\\car1Rotated90.png"));
                    rotated = master;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            }
        }

    }

    public void startAction() {
        startedAction = true;
    }

    public boolean isStarted() {
        return startedAction;
    }

    public int imageWidth() {
        return imageWidth;
    }

    public int imageHeight() {
        return imageHeight;
    }

    public int getId() {
        return id;
    }

    public void finishAction() {
        finishedAction = true;
    }

    public boolean isFinished() {
        return finishedAction;
    }

    public void startMoving() {
        moving = true;
    }

    public void stopMoving() {
        moving = false;
    }

    public void updateStopTime() {
        stopTime += 1;
    }

    public int getStopTime() {
        return stopTime;
    }

    public boolean isMoving() {
        return moving;
    }

    public int getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void translateTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void translateAdd(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void changeDirecton(int direction) {
        this.direction = direction;
    }

    public void draw(Graphics g) throws IOException {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(rotated, x, y, imageWidth,
                imageHeight, null);

    }

    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);
        imageHeight = (int) (newHeight * 1.14);
        imageWidth = (int) (newWidth * 1.14);

        BufferedImage rotate = new BufferedImage(newWidth, newHeight,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotate.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotate;
    }

    public void rotateImageByDegrees(double angle) {
        rotated = rotateImageByDegrees(master, angle);

    }

    public void setActionCase(int actionCase) {
        this.actionCase = actionCase;
    }

    public int getActionCase() {
        return actionCase;
    }

    public void doAction() {

        // System.out.println(actionCase);

        switch (actionCase) {
            case 1: {

                switch (direction) {
                    case (1): {

                        y += 2;
                        break;
                    }
                    case (2): {
                        x -= 2;
                        break;

                    }
                    case (3): {
                        y -= 2;
                        break;
                    }
                    case (4): {
                        x+=2;
                        break;
                    }
                }
                if (actionStep >= 320) {
                    finishedAction = true;
                    System.out.print("Finished ");

                }
                break;
            }
            case 2: {
                switch (direction) {
                    case (1): {
                        if (actionStep <= 100) {
                            y += 2;
                        } else if (actionStep >= 250) {
                            System.out.println("Finished North");
                            finishedAction = true;
                            Constants.NorthCars.remove(this);
                            Constants.EastCars.add(this);
                            changeDirecton(1);
                        } else if (actionStep >= 190) {
                            x -= 2;
                        } else {
                            rotateImageByDegrees((actionStep - 100));

                        }

                        break;
                    }
                    case (2): {
                        if (actionStep <= 80) {
                            x -= 2;
                        } else if (actionStep >= 200) {
                            System.out.println("Finished East");

                            finishedAction = true;
                            Constants.EastCars.remove(this);
                            Constants.SouthCars.add(this);
                            changeDirecton(2);
                        } else if (actionStep >= 170) {
                            y -= 2;
                        } else {
                            rotateImageByDegrees((actionStep - 80));

                        }

                        break;

                    }
                    case (3): {
                        if (actionStep <= 85) {
                            y -= 2;
                        } else if (actionStep >= 205) {
                            System.out.println("Finished South");

                            finishedAction = true;
                            Constants.SouthCars.remove(this);
                            Constants.WestCars.add(this);
                            changeDirecton(3);
                        } else if (actionStep >= 175) {
                            // System.out.println("working");
                            x += 2;
                        } else {
                            rotateImageByDegrees((actionStep - 85));

                        }

                        break;
                    }

                    case (4): {
                        if (actionStep <= 80) {
                            x += 2;
                        } else if (actionStep >= 200) {
                            System.out.println("Finished West");

                            finishedAction = true;
                            Constants.WestCars.remove(this);
                            Constants.NorthCars.add(this);
                            changeDirecton(2);
                        } else if (actionStep >= 170) {
                            y += 2;
                        } else {
                            rotateImageByDegrees((actionStep - 80));

                        }

                        break;
                }

            }

        }
    }
    actionStep++;


}}