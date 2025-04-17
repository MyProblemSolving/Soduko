package org.problemSolving.services;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConvertImageToBlackAndWhite {

    public static void convert(String inputFile, String outputFile, String formatName) throws IOException {

        try {

            File input = new File(inputFile);
            BufferedImage image = ImageIO.read(input);

            BufferedImage result = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_BYTE_GRAY);

            Graphics2D graphic = result.createGraphics();
            graphic.drawImage(image, 0, 0,null);
            graphic.dispose();

            File output = new File(outputFile);
            ImageIO.write(result, formatName, output);

        }  catch (IOException e) {
            e.printStackTrace();
        }


    }

}
