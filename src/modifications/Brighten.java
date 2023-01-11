package modifications;

import interactions.ImageUtil;
import model.Image;
import model.ImageStorage;
import model.Pixel;
import model.Storage;

/**
 * a function object to brighten a saved image.
 */
public class Brighten implements PPMModification {

  private int increment;
  private String imgName;
  private String destName;
  private Storage imgStorage;

  /**
   * Constructor to create a birghten class that can brighten an image given the params.
   *
   * @param imgStorage to get and save an image
   * @param increment  amount of brightness
   * @param imgName    name of the image to modify
   * @param destName   saving as destname after modifying
   */
  public Brighten(Storage imgStorage, int increment, String imgName, String destName) {
    this.increment = increment;
    this.imgStorage = imgStorage;
    this.imgName = imgName;
    this.destName = destName;
  }


  /**
   * This is specifically for testing.
   */
  public Brighten() {
    //this is to test the functionality of brighten method.
  }


  @Override
  public void runFunction() throws Exception {
    Image image = ModificationUtils.getImage(imgStorage, this.imgName);
    this.imgStorage.addImage(destName, brightenImage(image));
  }

  /**
   * brightens an image.
   *
   * @param image the image to be modified
   * @return the image with each individual pixelk modified based on the provided argument
   */
  private Image brightenImage(Image image) {
    int height = image.getHeight();
    int width = image.getWidth();
    Pixel[][] pixels = image.getPixels();

    Pixel[][] newPixels = new Pixel[height][width];

    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        int newR = ImageUtil.clamp(pixels[r][c].getR() + increment);
        int newG = ImageUtil.clamp(pixels[r][c].getG() + increment);
        int newB = ImageUtil.clamp(pixels[r][c].getB() + increment);

        newPixels[r][c] = new Pixel(newR, newG, newB);
      }
    }
    return new Image(height, width, newPixels);
  }


  /**
   * This applies the brighten function. Method is for testing.
   *
   * @param arg   takes in increment as string
   * @param image takes in an image to brighten
   * @return
   */
  public Image modifyImage(String arg, Image image) {

    this.increment = Integer.parseInt(arg);
    return brightenImage(image);
  }


}
