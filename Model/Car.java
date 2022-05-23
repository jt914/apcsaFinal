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
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import Controller.*;

public class Car{

    //buffered image is basically an image that has stored data
    private File image;

    //stores the car's location, starts out at the start location
    int x = Constants.carStartX;
    int y = Constants.carStartY;
    public Car(Object o){
        image = (BufferedImage)o;
        
        //adds current car to list of cars
        Constants.cars.add(this);
    }


    //simple setter methods
    public void translateTo(int x, int y){
        this.x = x;
        this.y= y;
    }

    public void translateAdd(int x, int y){
        this.x += x;
        this.y += y;
        }


        //draw method is called automatically somewhere in the jframe logic stuff
    public void draw(Graphics g) throws IOException{
        Graphics2D g2d = (Graphics2D)g;

        //CHANGE WIDTH AND HEIGHT OF CAR PICTURE ACCORDINGLY
        //Figure out how to rotate car

        //draws image, imageio read is basically like reading the image, x y are top left coords. Observer is some weird stuff, most people just use null unless youre doing complex stuf
        g2d.drawImage(ImageIO.read(image), x, y, 109, 150, null);

    }

    //THIS IS IN PROGRESS. 

    
    // public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
    //     double rads = Math.toRadians(angle);
    //     double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
    //     int w = img.getWidth();
    //     int h = img.getHeight();
    //     int newWidth = (int) Math.floor(w * cos + h * sin);
    //     int newHeight = (int) Math.floor(h * cos + w * sin);
    
    //     BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
    //     Graphics2D g2d = rotated.createGraphics();
    //     AffineTransform at = new AffineTransform();
    //     at.translate((newWidth - w) / 2, (newHeight - h) / 2);
    
    //     int x = w / 2;
    //     int y = h / 2;
    
    //     at.rotate(rads, x, y);
    //     g2d.setTransform(at);
    //     g2d.drawImage(img, 0, 0, this);
    //     g2d.setColor(Color.RED);
    //     g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
    //     g2d.dispose();
    
    //     return rotated;
    // }

}