package modifications.transformations;

import model.ImageStorage;
import model.Storage;
import model.transformmodels.GreyscaleTransformModel;

/**
 * Function object to transform an image by greyscaling it.
 */
public class GreyscaleTransform extends Transformations {

  /**
   * Constructor for GreyscaleTransform.
   *
   * @param imageStorage - Image storage where images are currently being stored
   *                     and retrieved.
   * @param imageName    - Original image to apply the greyscale transformation to.
   * @param destName     - The desired resulting image name after the greyscale has been applied.
   */
  public GreyscaleTransform(Storage imageStorage, String imageName, String destName) {
    super(imageStorage, imageName, new GreyscaleTransformModel(), destName);
  }
}
