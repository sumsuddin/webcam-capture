package com.github.sarxos.webcam;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Frame {
    private BufferedImage image;
    private byte[] encodedImage;

    public Frame(BufferedImage image, byte[] encodedImage) {
        this.image = image;
        this.encodedImage = encodedImage;
    }

    public BufferedImage getImage() {
        return image;
    }

    public byte[] getEncodedImage() {

        if (encodedImage != null) {
            return encodedImage;
        } else if (image != null) {
            return toByteArray(image);
        } else {
            return null;
        }
    }

    private byte[] toByteArray(BufferedImage image) {
        try {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(image, "jpg", baos);
                baos.flush();
                return baos.toByteArray();
            }
        } catch (IOException e) {
            return null;
        }
    }
}
