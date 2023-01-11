package modifications.filters;

import interactions.ImageUtil;
import model.Image;
import model.ImageStorage;
import model.Storage;
import model.kernalmodels.Kernel;
import model.Pixel;
import modifications.ModificationUtils;
import modifications.PPMModification;

/**
 * Represents a filter to be applied on an image, which uses the surrounding pixels
 * of a pixel to modify its rgb values.
 */
public abstract class Filter implements PPMModification {

  private Storage imgStore;

  private String destName;
  private String imgName;
  private Kernel kernel;

  /**
   * Constructor for a Filter.
   *
   * @param imageStorage - Image storage where images are currently being stored
   *                     & retrieved.
   * @param imageName    - The original image that is to be filtered.
   * @param kernel       - The desired kernel to apply to each pixel in the original image.
   * @param destName     - The name of the image after the desired kernel
   *                     has been applied to each pixel of the original image.
   */
  public Filter(Storage imageStorage, String imageName,
                Kernel kernel, String destName) {
    this.imgStore = imageStorage;
    this.imgName = imageName;
    this.kernel = kernel;
    this.destName = destName;
  }

  @Override
  public void runFunction() throws Exception {
    Image image = ModificationUtils.getImage(this.imgStore, this.imgName);
    this.imgStore.addImage(this.destName, applyFilter(image));
  }

  /**
   * Applies the kernel of this filter to each individual pixel of this image,
   * and returns a new image with the modified pixels.
   * @param img - The original image to modify.
   * @return - A new image with the kernel of this filter applied to each pixel of
   *        the supplied image.
   */
  private Image applyFilter(Image img) {
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
   * Creates a new pixel based on the current kernel for this filter, and the surrounding
   * pixels.
   *
   * @param row    - Row of the current pixel to modify.
   * @param col    - Col of the current pixel to modify.
   * @param pixels - 2D array of pixels from the original image.
   * @return - Returns a new pixel after the kernel for this filter has been applied.
   */
  private Pixel getNewPixel(int row, int col, Pixel[][] pixels) {
    int[][] rChannel = new int[this.kernel.getDimension()][this.kernel.getDimension()];
    int[][] gChannel = new int[this.kernel.getDimension()][this.kernel.getDimension()];
    int[][] bChannel = new int[this.kernel.getDimension()][this.kernel.getDimension()];
    int imgKernelRow = 0;
    int imgKernelCol = 0;

    for (int i = row - (this.kernel.getDimension() / 2);
         i <= row + (this.kernel.getDimension() / 2); i++) {

      for (int j = col - (this.kernel.getDimension() / 2);
           j <= col + (this.kernel.getDimension() / 2); j++) {

        Pixel currentPixel;

        try {
          currentPixel = pixels[i][j];
        } catch (ArrayIndexOutOfBoundsException e) {
          currentPixel = new Pixel(0, 0, 0);
        }

        rChannel[imgKernelRow][imgKernelCol] = currentPixel.getR();
        gChannel[imgKernelRow][imgKernelCol] = currentPixel.getG();
        bChannel[imgKernelRow][imgKernelCol] = currentPixel.getB();
        imgKernelCol += 1;
      }
      imgKernelRow += 1;
      imgKernelCol = 0;
    }

    return new Pixel(multiplyMatrices(rChannel), multiplyMatrices(gChannel),
            multiplyMatrices(bChannel));

  }

  /**
   * Applies the kernel to the target pixel and its surrounding pixels based on
   * the dimensions of the given kernel.
   * @param channel - Represents the red, green, or blue channel of a pixel.
   * @return - Returns the value after adding the product of the current kernel for this
   *        filter and the target pixels.
   */
  private int multiplyMatrices(int[][] channel) {
    double[][] kernel = this.kernel.getKernel();
    int val = 0;
    for (int row = 0; row <= this.kernel.getDimension() - 1; row++) {
      for (int col = 0; col <= this.kernel.getDimension() - 1; col++) {
        int calc = (int) (channel[row][col] * kernel[row][col]);
        val += calc;
      }
    }
    return ImageUtil.clamp(val);
  }
}
