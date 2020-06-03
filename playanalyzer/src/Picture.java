package playanalyzer;

import playanalyzer.Pixel;

public interface Picture {
    /**
     * Given a set difference, returns black-and-white picture showing only places where two neighboring pixels
     * differ by at least "difference" (uses double-gradient energy function)
     * @throws IllegalArgumentException if difference < 0
     */
    Pixel[][] outline(double difference);

    /**
     * sets the pixel at coordinate (x,y) to p (basically just changes color)
     * @throws IllegalArgumentException if either x or y are less than 0 or greater than width or height, respectively
     * or
     */
    void setColor(int r, int c, Pixel p);
}
