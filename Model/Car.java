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

    // buffered image is basically an image that has stored data
    private BufferedImage master, rotated;
    private int direction, x, y;
    private boolean moving;
    private int stopTime;
    private boolean finishedAction;
    public static int currentId;
    private int id;

    // stores the car's location, starts out at the start location

    public Car(Image o) {
        master = (BufferedImage) o;
        rotated = master;
        id = currentId;
        currentId++;

        // adds current car to list of cars

        // int dOfTravel = (int) (Math.random() * 4) + 1;
        // direction = dOfTravel;
        int dOfTravel = 1;
        direction = 1;
        System.out.println(direction);
        moving = true;

        switch (dOfTravel) {
            case (1): {
                Constants.NorthCars.add(this);
                x = Constants.carStartXNorth;
                y = Constants.carStartYNorth;
                try {
                    master = ImageIO.read(new File("resources\\car1Rotated180.png"));
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            }
        }

    }

    public int imageWidth() {
        switch (direction) {
            case (1): {
                return (int) (Constants.SW * 0.0808605341246291);
            }
            case (2): {
                return (int) (Constants.SH * 0.1893939393939394);

            }
            case (3): {
                return (int) (Constants.SW * 0.0808605341246291);

            }
            case (4): {
                return (int) (Constants.SH * 0.1893939393939394);
            }
            default:
                return 0;
        }
    }

    public int imageHeight() {
        switch (direction) {
            case (1): {
                return (int) (Constants.SH * 0.1893939393939394);

            }
            case (2): {

                return (int) (Constants.SW * 0.0808605341246291);

            }
            case (3): {
                return (int) (Constants.SH * 0.1893939393939394);

            }
            case (4): {
                return (int) (Constants.SW * 0.0808605341246291);

            }
            default:
                return 0;

        }
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

    // simple getter methods
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // simple setter methods
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

    // draw method is called automatically somewhere in the jframe logic stuff
    public void draw(Graphics g) throws IOException {
        Graphics2D g2d = (Graphics2D) g;

        // NEED THIS, USE THIS.X + X FOR ROTATION
        // int x = (getWidth() - rotated.getWidth()) / 2;
        // int y = (getHeight() - rotated.getHeight()) / 2;

        // CHANGE WIDTH AND HEIGHT OF CAR PICTURE ACCORDINGLY
        // Figure out how to rotate car

        // draws image, imageio read is basically like reading the image, x y are top
        // left coords. Observer is some weird stuff, most people just use null unless
        // youre doing complex stuf
        switch (direction) {
            case (1): {
                g2d.drawImage(master, x, y, (int) (Constants.SW * 0.0808605341246291),
                        (int) (Constants.SH * 0.1893939393939394), null);
                break;
            }
            case (2): {

                g2d.drawImage(master, x, y, (int) (Constants.SH * 0.1893939393939394),
                        (int) (Constants.SW * 0.0808605341246291), null);
                break;

            }
            case (3): {
                g2d.drawImage(master, x, y, (int) (Constants.SW * 0.0808605341246291),
                        (int) (Constants.SH * 0.1893939393939394), null);
                break;

            }
            case (4): {
                g2d.drawImage(master, x, y, (int) (Constants.SH * 0.1893939393939394),
                        (int) (Constants.SW * 0.0808605341246291), null);
                break;

            }
        }

    }

    // THIS IS IN PROGRESS.

    @Override
    public Dimension getPreferredSize() {
        return master == null
                ? new Dimension(200, 200)
                : new Dimension(master.getWidth(), master.getHeight());
    }

    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotate = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
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

    public void doAction() {
        /**
         * 
         * action case int. Switch for which case, either turning left right or straight
         * In method called do action
         * Passes in stage of action (what y variable they are at and then apply the
         * corresponding transformation)
         */
    }

}