
/**
 * Write a description of ImageSaver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class ImageSaver {
    public void doSave() {
        DirectoryResource dr =new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            ImageResource inImg = new ImageResource(f);
            String inFname = inImg.getFileName();
            String outFname = "copy-" + inFname;
            inImg.setFileName(outFname);
            inImg.draw();
            inImg.save();
        }
    }

}
