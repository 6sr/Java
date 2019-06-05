
/**
 * Write a description of GrayScaleConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    public ImageResource makeGray(ImageResource inImg) {
        ImageResource outImg = new ImageResource(inImg.getWidth(),inImg.getHeight());
        for(Pixel pixel : outImg.pixels()) {
            Pixel inPixel =inImg.getPixel(pixel.getX(),pixel.getY());
            int avg = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            pixel.setRed(avg);
            pixel.setGreen(avg);
            pixel.setBlue(avg);
        }
        return outImg;
    }
    public void testGray() {
        ImageResource inImg = new ImageResource();
        ImageResource gray = makeGray(inImg);
        gray.draw();
    }
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            ImageResource inImg = new ImageResource(f);
            ImageResource gray = makeGray(inImg);
            gray.draw();
        }
    }
}
