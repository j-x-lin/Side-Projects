package playanalyzer;

import playanalyzer.Pixel;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class CreatePicture {
    private BufferedImage picture;
    private Pixel[][] pixels;

    public CreatePicture(Pixel[][] pixels) throws IOException {
        this.pixels = pixels;

        picture = new BufferedImage(pixels[0].length, pixels.length, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < pixels.length; y++) {
            for (int x = 0; x < pixels[0].length; x++) {
                Pixel p = pixels[y][x];

                picture.setRGB(x, y, 65536 * p.getR() + 256 * p.getG() + p.getB());
            }
        }

        ImageIO.write(picture, "png", new File("src/images/pic.png"));
    }
}
