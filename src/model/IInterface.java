package model;

public interface IInterface {

  /**
   * converts image to a string. More specifically to a PPM format.
   *
   * @return a string representing ppm image.
   */
  public String toString();

  /**
   * provides the grid of pixels for an image.
   *
   * @return an array of pixels.
   */
  public Pixel[][] getPixels();

  /**
   * public method to get an height of the image.
   *
   * @return height of the image.
   */
  public int getHeight();

  /**
   * public method to get an width of the image.
   *
   * @return width of the image.
   */
  public int getWidth();
}
