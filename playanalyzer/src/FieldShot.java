package playanalyzer;

import playanalyzer.Pixel;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

public class FieldShot implements playanalyzer.Picture {
    private Pixel[][] image;
    private int width;  //number of columns in image
    private int height; //number of rows in image

    public FieldShot(Pixel[][] pic) {
        this.image = pic;
        this.width = pic[0].length;
        this.height = pic.length;
    }

    public FieldShot(BufferedImage picture)
    {
        this.width = picture.getWidth();
        this.height = picture.getHeight();

        this.image = new Pixel[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color c = new Color(picture.getRGB(x,y));
                image[y][x] = new Pixel(c.getRed(), c.getGreen(), c.getBlue());
            }
        }
    }

    private double energy(int x, int y) {
        Pixel l = image[y][(x-1+width)%width];
        Pixel r = image[y][(x+1)%width];
        Pixel u = image[(y-1+height)%height][x];
        Pixel d = image[(y+1)%height][x];

        double lr = Math.pow(l.getB()-r.getB(), 2)
                + Math.pow(l.getG()-r.getG(), 2)
                + Math.pow(l.getR()-r.getR(), 2);
        double du = Math.pow(u.getB()-d.getB(), 2)
                + Math.pow(u.getG()-d.getG(), 2)
                + Math.pow(u.getR()-d.getR(), 2);

        return Math.sqrt(lr+du);
    }

    public Pixel[][] outline(double difference) {
        Pixel[][] outlined = new Pixel[height][width];

        eliminateNoise();
        //sharpenBoundaries();

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                outlined[r][c] = color(r, c, difference);
            }
        }

        return outlined;
    }

    //returns either a BLACK pixel or a WHITE pixel (BLACK = border pixel)
    private Pixel color(int r, int c, double diff) {
        double energy = energy(c, r);

        if (energy-minSurrEnergy(r, c) < diff) {
            return new Pixel(255, 255, 255);
        }

        return new Pixel(0, 0,0);
    }

    //calculates the minimum energy of the 8 Pixels surrounding the given Pixel
    private double minSurrEnergy(int r, int c) {
        double ul = energy((c-1+width) % width, (r-1+height) % height);
        double u = energy(c, (r-1+height) % height);
        double ur = energy((c+1) % width, (r-1+height) % height);
        double l = energy((c-1+width) % width, r);
        double right = energy((c+1) % width, r);
        double dl = energy((c-1+width) % width, (r+1) % height);
        double d = energy(c, (r+1) % height);
        double dr = energy((c+1) % width, (r+1) % height);

        return Math.min(Math.min(Math.min(ul, u), Math.min(ur, l)),
                        Math.min(Math.min(right, dl), Math.min(d, dr)));
    }

    public void setColor(int r, int c, Pixel p) {
        image[r][c] = p;
    }

    private void eliminateNoise() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (energy(x, y) < 10) {
                    average(x, y);
                }
            }
        }
    }

    private void average(int col, int row) {
        Pixel p = image[row][col];

        Pixel u = image[(row-1+height) % height][col];
        Pixel l = image[row][(col-1+width) % width];
        Pixel r = image[row][(col+1) % width];
        Pixel d = image[(row+1) % height][col];

        p.setR((u.getR()+l.getR()+r.getR()+d.getR())/4);
        p.setG((u.getG()+l.getG()+r.getG()+d.getG())/4);
        p.setB((u.getB()+l.getB()+r.getB()+d.getB())/4);
    }

    private void sharpenBoundaries() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (energy(x, y) > 25) {
                    sharpen(x, y);
                }
            }
        }
    }

    private void sharpen(int col, int row) {
        Pixel p = image[row][col];

        Pixel u = image[(row-1+height) % height][col];
        Pixel l = image[row][(col-1+width) % width];
        Pixel r = image[row][(col+1) % width];
        Pixel d = image[(row+1) % height][col];
    }
}