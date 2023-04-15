package com.ercan.utils;

import org.apache.logging.log4j.*;

import java.io.*;
import java.util.Base64;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {

    private static final Logger logger = LogManager.getLogger(ImageUtils.class);

    private ImageUtils(){throw new IllegalAccessError("ImageUtils");}


    public static String convertImageFileToBase64String(String imagePath) throws IOException {
        String base64Image = "";
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            logger.error("Image not found ::" + e);
        } catch (IOException ioe) {
            logger.error("Exception while reading the Image ::" + ioe);
        }
        return base64Image;
    }

    public static void convertBase64ToImageFile(String base64Image, String pathFile) throws IOException {
        try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            logger.error("Image not found :: " + e);
        } catch (IOException ioe) {
            logger.error("Exception while reading the Image ::" + ioe);
        }
    }

    public static byte[] compressImage(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ex) {
            logger.error("ImageUtils.compressImage error->",ex);
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ex) {
            logger.error("ImageUtils.decompressImage error->",ex);
        }
        return outputStream.toByteArray();
    }

}
