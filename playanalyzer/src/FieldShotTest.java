package playanalyzer;

import playanalyzer.FieldShot;
import playanalyzer.CreatePicture;
import playanalyzer.Pixel;
import edu.princeton.cs.algs4.Picture;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.*;

public class FieldShotTest {
    public static void main(String[] args) throws IOException {
        BufferedImage picture = ImageIO.read(new File("src/images/Jordyn Brooks.PNG"));

        FieldShot testing = new FieldShot(picture);
        Pixel[][] outline = testing.outline(50);

        CreatePicture image = new CreatePicture(outline);
    }
}
