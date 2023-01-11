package model.transformmodels;

import model.kernalmodels.Kernel;

/**
 * Represents a kernel used to grey scale an image.
 */
public class GreyscaleTransformModel extends Kernel {

  /**
   * GreyscaleTransformModel constructor that initializes a 3x3 kernel
   * to grey scale images.
   */
  public GreyscaleTransformModel() {
    super(3);
    for (int i = 0; i < getDimension(); i++) {

      for (int j = 0; j < getDimension(); j++) {

        if (j == 0) {
          this.kernel[i][j] = 0.2126;
        } else if (j == 1) {
          this.kernel[i][j] = 0.7152;
        } else {

          this.kernel[i][j] = 0.0722;
        }
      }
    }
  }
}
