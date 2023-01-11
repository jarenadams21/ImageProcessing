package model;

/**
 * Class Image represents an image.
 * It has width, height, comment and pixels for a given image.
 */
public class Image implements IInterface {
  private final int width;
  private final int height;
  private final String comment;
  private final Pixel[][] pixels;

  /**
   * Constructor for the Image class. Creates an image from given params.
   *
   * @param height  height of the image.
   * @param width   width of the image.
   * @param comment comments in the image.
   * @param pixels  grid representing the image.
   */
  public Image(int height, int width, String comment, Pixel[][] pixels) {
    this.width = width;
    this.height = height;
    this.comment = comment;
    this.pixels = pixels;
  }

  /**
   * Constructor for the Image class. Creates an image from given params.
   *
   * @param height height of the image.
   * @param width  width of the image.
   * @param pixels grid representing the image.
   */
  public Image(int height, int width, Pixel[][] pixels) {
    this.width = width;
    this.height = height;
    this.comment = null;
    this.pixels = pixels;
  }

  /**
   * Takes an image to create a duplicate of the image.
   *
   * @param image an Image.
   */
  public Image(Image image) {
    this.height = image.height;
    this.width = image.width;
    this.comment = null;
    this.pixels = image.pixels;
  }


  /**
   * converts image to a string. More specifically to a PPM format.
   *
   * @return a string representing ppm image.
   */
  public String toString() {
    StringBuilder pixelsString = new StringBuilder();
    for (Pixel[] rowOfPixels : pixels) {
      for (Pixel pixel : rowOfPixels) {
        pixelsString.append(pixel.toString());
        pixelsString.append("\n");
      }
    }
    return "P3\n" + this.width + " " + this.height + "\n" + "255\n" + pixelsString;
  }

  /**
   * provides the grid of pixels for an image.
   *
   * @return an array of pixels.
   */
  public Pixel[][] getPixels() {
    return this.pixels.clone();
  }

  /**
   * public method to get an height of the image.
   *
   * @return height of the image.
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * public method to get an width of the image.
   *
   * @return width of the image.
   */
  public int getWidth() {
    return this.width;
  }


}
