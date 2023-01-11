package model.kernalmodels;

/**
 * Represents a sharpen kernel to apply the sharpen filter to images.
 */
public class SharpenKernel extends Kernel {

  /**
   * Constructor for a SharpenKernel, creating a 2D array of size 5.
   * 0.25, -0.0625, and 1.0 are all included in this kernel.
   */
  public SharpenKernel() {
    super(5);

    for (int i = 0; i < 5; i++) {

      for (int j = 0; j < 5; j++) {

        if (i != 0 && i != 4 && j != 0 && j != 4) {

          this.kernel[i][j] = 1.0 / 4;
        } else {

          this.kernel[i][j] = -1.0 / 8;
        }
      }
    }

    this.kernel[2][2] = 1.0;
  }
}
