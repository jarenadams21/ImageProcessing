package modifications;

import model.Image;
import model.ImageStorage;
import model.Storage;
import model.kernalmodels.Kernel;

/**
 * Abstract class for general transformation on an image behavior.
 */
public abstract class AbstractTransform implements PPMModification {
  protected Storage imgStore;

  protected String destName;
  protected String imgName;
  protected Kernel kernel;

  /**
   * Constructor for AbstractTransform.
   *
   * @param imageStorage - Image storage where images are currently being stored and retrieved.
   * @param imageName    - Original image name.
   * @param kernel       - Kernel for this transformation.
   * @param destName     - The resulting image name after this transformation has been
   *                     applied.
   */
  public AbstractTransform(Storage imageStorage, String imageName,
                           Kernel kernel, String destName) {
    this.imgStore = imageStorage;
    this.imgName = imageName;
    this.kernel = kernel;
    this.destName = destName;
  }

  @Override
  public void runFunction() throws Exception {
    Image image = ModificationUtils.getImage(this.imgStore, this.imgName);
    this.imgStore.addImage(this.destName, applyTransformation(image));
  }

  /**
   * Applies this transformation onto the original image.
   *
   * @param image - The image to be modified.
   * @return - The resulting image after this transformation
   *        has been applied to the original image.
   */
  protected Image applyTransformation(Image image) {
    return image;
  }


}
