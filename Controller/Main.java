package Controller;

import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Model.*;
import View.MainWindow;

public class Main {

    public static void main(String[] args) throws IOException {

        Constants.paths.add("resources\\car1.png");
        Constants.paths.add("resources\\car2.png");
        Constants.paths.add("resources\\car3.png");
        Constants.paths.add("resources\\background.png");

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(Constants.SW, Constants.SH);

        MainWindow main = new MainWindow();

        main.setLocation(100, 100);
        frame.add(main);

        frame.setVisible(true);

        long startTimeInMillis = System.currentTimeMillis();

        int counter = 0;

        new Car(ImageIO.read(new File(Constants.paths.get(0))));

        while (true) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - startTimeInMillis >= 100) {
                startTimeInMillis = currentTimeMillis;
                new Car(ImageIO.read(new File(Constants.paths.get(0))));
                ++counter;

                if (counter >= 20) {
                    break;
                }
            }
        }

    }
}
