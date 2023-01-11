package interactions;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.Image;
import model.Pixel;


/**
 * This class contains utility methods to read a PPM image from file.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static Image readPPM(String filename) throws FileNotFoundException {
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    Pixel[][] pixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[i][j] = new Pixel(r, g, b);
      }
    }

    return new Image(height, width, pixels);
  }

  /**
   * Reads the image contents of a popular image format such as jpeg or png.
   *
   * @param filename - Name of the file to be read from.
   * @return - Returns an Image that can be interpreted by the image software.
   * @throws Exception - IF the method fails to read from the provided filename.
   */
  public static Image readNormalImage(String filename) throws Exception {
    try {
      BufferedImage image = ImageIO.read(new File(filename));
      return parseImage(image);
    } catch (IOException e) {
      throw new Exception(String.valueOf(e));
    }

  }

  /**
   * Creates a new Image by extracting the color contents of the provided buffer image.
   *
   * @param image - The buffered image from a popular file format.
   * @return - Returns an Image representation of the BufferedImage.
   */
  public static model.Image parseImage(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    Pixel[][] result = new Pixel[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int dataBuffer = image.getRGB(col, row);
        Color c = new Color(dataBuffer);
        result[row][col] = new Pixel(c.getRed(), c.getGreen(), c.getBlue());
      }
    }

    return new Image(height, width, result);
  }

  /**
   * Method designed to parse an image of our own into a buffered image.
   * @param image - Original image from within the
   *               internal definition for an image in the software.
   * @return - Returns a buffered image copy of the input image.
   */
  public static BufferedImage reverseParseImage(Image image) {
    int height = image.getHeight();
    int width = image.getWidth();

    BufferedImage bi = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);
    WritableRaster raster = bi.getRaster();
    int[] pixels = flattenPixels(image.getPixels(), (width * height));

    raster.setPixels(0, 0, width, height, pixels);
    bi.setData(raster);
    return bi;
  }

  /**
   * Flattens a 2D array of pixels into a 1D array of RGB values of each pixel
   * in order in row-column order.
   *
   * @param pixels - 2D array of pixels from an image.
   * @param size   - Number of pixels in the image (width * height).
   * @return
   */
  private static int[] flattenPixels(Pixel[][] pixels, int size) {

    int index = 0;
    int[] res = new int[size * 3];

    for (Pixel[] row : pixels) {

      for (Pixel pixel : row) {

        for (int color : pixel.toList()) {

          res[index] = color;
          index++;
        }
      }
    }

    return res;
  }


  /**
   * returns the correct value to modify the pixel RGB value by with a min and max of 0 adn 255.
   *
   * @param val the R, G, or B value to change
   * @return the modified R, G, or B value
   */
  public static int clamp(int val) {
    if (val >= 255) {
      return 255;
    }

    if (val <= 0) {
      return 0;
    }
    return val;
  }

}

