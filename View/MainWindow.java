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
    
    public MainWindow(){
        setSize(new Dimension (Constants.SW, Constants.SH));
        new Timer(50, this).start();
    }

    public void actionPerformed(ActionEvent e) {
        for (Car c : Constants.cars){
        c.translateAdd(10, 0);
        }
        
        repaint();

        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Car c : Constants.cars)
            try {
                c.draw(g2d);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }




    
}