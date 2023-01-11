package model.kernalmodels;

/**
 * Represents a 2D array of doubles to use in image transformations
 * and filters using matrix multiplication.
 */
public class Kernel {

  protected double[][] kernel;

  /**
   * Kernel constructor that takes in a dimension.
   *
   * @param dimension - length and width of the kernel.
   * @throws IllegalArgumentException - If the dimension is even.
   */
  public Kernel(int dimension) throws IllegalArgumentException {

    if (dimension % 2 == 0 || dimension <= 1) {
      throw new IllegalArgumentException("Invalid dimension for kernels.");
    } else {
      this.kernel = new double[dimension][dimension];
    }
  }

  /**
   * Returns the dimension length for this kernel.
   *
   * @return - An integer representing the legnth/width of the kernel.
   */
  public int getDimension() {

    return this.kernel.length;
  }

  /**
   * Returns a copy of this kernel.
   *
   * @return - A copy of this kernel (i.e. 2D array of doubles).
   */
  public double[][] getKernel() {

    double[][] copy = new double[this.kernel.length][this.kernel[0].length];

    for (int row = 0; row < this.kernel.length; row++) {

      for (int col = 0; col < this.kernel[0].length; col++) {

        copy[row][col] = this.kernel[row][col];
      }
    }

    return copy;
  }

}
