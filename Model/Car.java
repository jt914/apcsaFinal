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
    protected File image;
    protected int x,y;

    public Car(Object o){
        image = (File)o;

        //CHANGE THESE TO STARTING COORDS
        x = 0;
        y = 0;
        Constants.cars.add(this);
    }

    public void translateTo(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void translateAdd(int x, int y){
        this.x += x;
        this.y += y;
    }

    public void draw(Graphics g) throws IOException{
        Graphics2D g2d = (Graphics2D)g;

        //CHANGE WIDTH AND HEIGHT OF CAR PICTURE ACCORDINGLY
        g2d.drawImage(ImageIO.read(image), x, y, 146, 200, null);

    }

}