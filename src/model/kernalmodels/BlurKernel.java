package model.kernalmodels;

/**
 * Represents a kernel used to apply a blur filter onto an image.
 */
public class BlurKernel extends Kernel {

  /**
   * BlurKernel Constructor that initializes the 3x3 blur filter.
   */
  public BlurKernel() {
    super(3);
    this.kernel[0][0] = 1.0 / 16;
    this.kernel[0][1] = 1.0 / 8;
    this.kernel[0][2] = 1.0 / 16;
    this.kernel[1][0] = 1.0 / 8;
    this.kernel[1][1] = 1.0 / 4;
    this.kernel[1][2] = 1.0 / 8;
    this.kernel[2][0] = 1.0 / 16;
    this.kernel[2][1] = 1.0 / 8;
    this.kernel[2][2] = 1.0 / 16;
  }
}
