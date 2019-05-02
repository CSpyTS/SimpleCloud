package cspy.online;

import net.coobird.thumbnailator.Thumbnails;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author CSpy
 * @date 2019/4/23 23:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class TestJavaCV {


    @Test
    public void extractImage() {
        try {
            FFmpegFrameGrabber grabber =  FFmpegFrameGrabber.createDefault(new File("E:\\迅雷下载\\7500航班.mp4"));


            grabber.start();
            int length = grabber.getLengthInFrames();
            System.out.println("length = " + length);
            grabber.setFrameNumber(new Random().nextInt(length));


            Java2DFrameConverter converter = new Java2DFrameConverter();
            Frame frame = grabber.grabImage();
            if (frame == null) {
                System.out.println("frame = null");
                System.exit(0);
            }
            BufferedImage image = converter.getBufferedImage(frame);
            File file = new File("D:/Temp/frame.jpg");
            ImageIO.write(image, "jpg", file);

            grabber.stop();

            File smallImage = new File("D:/Temp/frame-low.jpg");
            Thumbnails.of(file).scale(0.3f).outputQuality(0.4f).toFile(smallImage);

        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
