package playanalyzer;

import java.awt.color.*;

public class Pixel {
    private int r;
    private int g;
    private int b;

    public Pixel(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void setR(int r) { this.r = r; }

    public void setG(int g) { this.g = g; }

    public void setB(int b) { this.b = b; }

    public int getR() { return r; }

    public int getG() { return g; }

    public int getB() { return b; }

    //"difference" in colors between this pixel and another pixel
    public double difference(Pixel other) {
        double rDiff = this.getR()-other.getR();
        double gDiff = this.getG()-other.getG();
        double bDiff = this.getB()-other.getB();

        return Math.sqrt(Math.pow(rDiff, 2) + Math.pow(gDiff, 2) + Math.pow(bDiff, 2));
    }

    public String toString() {
        return "" + (getR()+getG()+getB())/3;
    }
}
