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


public class MainWindow extends JPanel implements ActionListener{

    //affine transform is weird stuff im not using this rn
    private AffineTransform trans;										

    
    public MainWindow(){

        //sets size of window
        setSize(new Dimension (Constants.SW, Constants.SH));

        //basically this timer will trigger the action performed method every 10 milliseconds
        new Timer(10, this).start();
    }

    public void actionPerformed(ActionEvent e) {

        //moves each car up 1
        for (Car c : Constants.cars){
        c.translateAdd(0, -1);
        }
        //pretty sure this just calls the paint component thingy
        repaint();

        
    }

    
    public void paintComponent(Graphics g) {

        //these next two lines you just need for some reason idrk why
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        try {

        //because reading an image might result in failure, you need to surround it in a try catch. You can read up how draw image works, but its pretty simple
            g.drawImage(ImageIO.read(new File(Constants.paths.get(3))), 0, 0, Constants.SW, Constants.SH, null);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        //this just redraws i dont really think i need this but it works rn so...
        for (Car c : Constants.cars)
            try {
                c.draw(g2d);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }




    
}