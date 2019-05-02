package cspy.online.util;


import net.coobird.thumbnailator.Thumbnails;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.util.UUID;

/**
 * @author CSpy
 * @date 2019/4/24 9:38
 */
public class FileTool {

    public static BufferedImage getFrame(File video) {
        try {
            FFmpegFrameGrabber grabber =  FFmpegFrameGrabber.createDefault(video);

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

            BufferedImage bufferedImage = converter.getBufferedImage(frame);
            grabber.stop();

            return bufferedImage;

        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File getSmallImage(BufferedImage image) {
        File output = new File(UUID.randomUUID().toString().replace("-","") + ".jpg");
        try {
            Thumbnails.of(image).scale(0.3f).outputQuality(0.4f).toFile(output);
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] fileToByte(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static File byteToFile(byte[] fileBytes) throws IOException {
        File tempFile = new File(UUID.randomUUID().toString().replace("-", ""));
        if (!tempFile.exists()) {
            boolean newFile = tempFile.createNewFile();
            if (!newFile) {
                throw  new FileNotFoundException();
            }
        }
        Files.write(tempFile.toPath(), fileBytes);
        return tempFile;
    }
}
