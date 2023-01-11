package model;

/**
 * A class to represent a pixel with a red value, green value, and blue value.
 */
public class Pixel {
  private int r;
  private int g;
  private int b;

  /**
   * constructor takes in a red, green, and blue value between 0 and 255.
   *
   * @param r the int corresponding to the red value of the pixel
   * @param g the int corresponding to the green value of the pixel
   * @param b the int corresponding to the blue value of the pixel
   */
  public Pixel(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("values for all r, g, and b must be between 0-255");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * clone this Pixel object.
   *
   * @return a shallow copy of this Pixel
   */
  public Pixel clone() {
    return new Pixel(this.r, this.g, this.b);
  }

  /**
   * creates a pixel given r,g,b color integer values.
   *
   * @param pixel takes in pixel to copy its content
   */
  public Pixel(Pixel pixel) {
    this.r = pixel.getR();
    this.g = pixel.getG();
    this.b = pixel.getB();
  }

  /**
   * a toString method gives the three values of r, g, and b as a string.
   *
   * @return a string with r, g, and b listed with spaces between
   */
  public String toString() {
    return r + " " + g + " " + b;
  }

  /**
   * a list of the pixels returned as a list of integers.
   *
   * @return the list of fields
   */
  public int[] toList() {
    int[] pixelList = new int[3];
    pixelList[0] = r;
    pixelList[1] = g;
    pixelList[2] = b;
    return pixelList;
  }

  /**
   * sets the r value of this pixel.
   *
   * @param r the red value of this pixel
   */
  public void setR(int r) {
    this.r = r;
  }

  /**
   * sets the g value of this pixel.
   *
   * @param g the green value of this pixel
   */
  public void setG(int g) {
    this.g = g;
  }

  /**
   * sets the b value of this pixel.
   *
   * @param b the blue value of this pixel
   */
  public void setB(int b) {
    this.b = b;
  }

  /**
   * retrieves the r value of this pixel.
   *
   * @return the red value for this pixel
   */
  public int getR() {
    return this.r;
  }

  /**
   * retrieves the g value of this pixel.
   *
   * @return the green value for this pixel
   */
  public int getG() {
    return this.g;
  }

  /**
   * retrieves the b value of this pixel.
   *
   * @return the blue value for this pixel
   */
  public int getB() {
    return this.b;
  }
}
