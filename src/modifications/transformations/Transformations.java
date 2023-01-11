package modifications.transformations;

import interactions.ImageUtil;
import model.Image;
import model.ImageStorage;
import model.Storage;
import model.kernalmodels.Kernel;
import model.Pixel;
import modifications.AbstractTransform;
import modifications.ModificationUtils;

/**
 * Represents a Transformation on an image using matrices,
 * modifying each color channel of a pixel independently of surrounding pixels.
 */
public class Transformations extends AbstractTransform {

  /**
   * Constructor for Transformations.
   *
   * @param imageStorage - Image storage where images are currently being stored and retrieved.
   * @param imageName    - Original image name.
   * @param kernel       - The kernel for this transformation, represented as a 2D array of doubles
   * @param destName     - The resulting image name after this transformation has been applied.
   */
  public Transformations(Storage imageStorage, String imageName,
                         Kernel kernel, String destName) {
    super(imageStorage, imageName, kernel, destName);
  }

  @Override
  public void runFunction() throws Exception {
    Image image = ModificationUtils.getImage(this.imgStore, this.imgName);
    this.imgStore.addImage(this.destName, applyTransformation(image));
  }

  /**
   * Modifies each pixel in the original image using the current kernel associated
   * with this transformation.
   * @param img - The original image.
   * @return - A transformed image with each pixel modified.
   */
  @Override
  protected Image applyTransformation(Image img) {
    int width = img.getWidth();
    int height = img.getHeight();
    Pixel[][] pixels = img.getPixels();
    Pixel[][] newImg = new Pixel[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        newImg[row][col] = getNewPixel(row, col, pixels);
      }
    }

    return new Image(height, width, newImg);
  }

  /**
   * Creates a new pixel using the kernel associated with this transformation
   * and applies matrix multiplication for the red, green, and blue channels.
   * @param row    - Row of the pixel to modify.
   * @param col    - Col of the pixel to modify.
   * @param pixels - 2D array of pixels from the original image.
   * @return - Returns a new pixel in which each channel has been modified based on the
   *        kernel for this transformation.
   */
  private Pixel getNewPixel(int row, int col, Pixel[][] pixels) {
    int ogR = pixels[row][col].getR();
    int ogG = pixels[row][col].getG();
    int ogB = pixels[row][col].getB();

    int[] ogRGB = new int[]{ogR, ogG, ogB};

    return new Pixel(multiplyMatrices(0, ogRGB),
            multiplyMatrices(1, ogRGB),
            multiplyMatrices(2, ogRGB));

  }

  /**
   * Returns the value after applying matrix multiplication to the
   * provided channel of a pixel using the kernel for this transformation.
   *
   * @param startRow - Can be one of {0,1,2} representing the 3 different rows/channels of a pixel.
   * @param rgb - The rgb values of the pixel we are currently modifying represented as
   *        an array of 3 integers for each value.
   * @return - Returns the value after matrix multiplying the appropriate channel by the
   *        kernel for this transformation.
   */
  private int multiplyMatrices(int startRow, int[] rgb) {
    double newVal = 0;
    for (int col = 0; col <= 2; col++) {
      newVal += this.kernel.getKernel()[startRow][col] * rgb[col];
    }
    return ImageUtil.clamp((int) newVal);
  }
}
