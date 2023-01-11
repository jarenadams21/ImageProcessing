package modifications.filters;

import model.ImageStorage;
import model.Storage;
import model.kernalmodels.SharpenKernel;

/**
 * Represents a filter used to sharpen images.
 */
public class SharpenFilter extends Filter {

  /**
   * Constructor for SharpenFilter.
   *
   * @param imageStorage - Image storage where images are currently being stored
   *                     & retrieved.
   * @param imageName    - Original image to apply the sharpen filter to.
   * @param destName     - The resulting image name after the sharpen filter has
   *                     been applied.
   */
  public SharpenFilter(Storage imageStorage, String imageName, String destName) {
    super(imageStorage, imageName, new SharpenKernel(), destName);
  }
}
