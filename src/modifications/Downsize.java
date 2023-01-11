package modifications;

import java.util.ArrayList;
import java.util.List;

import model.IInterface;
import model.Image;
import model.Pixel;
import model.Storage;

public class Downsize implements PPMModification {

  private int percentage;

  private int newHeight;

  private int newWidth;

  private double heightRatio;

  private double widthRatio;
  private String imgName;
  private String destName;

  private Storage imgStorage;


  /**
   * Downsizes a given image based on flip type which can be vertical or horizontal.
   *
   * @param imgStorage takes in a place to store and access image.
   * @param percentage   percentage to downsize the image by.
   * @param imgName    name of image to modify.
   * @param destName   saving modify image as dest name.
   */
  public Downsize(Storage imgStorage, int percentage,
                  String imgName, String destName) {

    if (percentage < 0 || percentage >= 100) {
      throw new IllegalArgumentException("Cannot downsize to a " +
              "bigger or negative sized image");
    }
    this.imgStorage = imgStorage;
    this.percentage = percentage;
    this.imgName = imgName;
    this.destName = destName;

  }


  @Override
  public void runFunction() throws Exception {


    IInterface image = ModificationUtils.getImage(imgStorage, imgName);

    this.newWidth = (int) (Math.ceil(image.getWidth() *
            (percentage / 100.0)));

    this.newHeight = (int) (Math.ceil(image.getHeight() *
            (percentage / 100.0)));


    this.heightRatio = image.getHeight() /
            (double) this.newHeight;

    this.widthRatio = image.getWidth() /
            (double) this.newWidth;

    Pixel[][] ogPixels = image.getPixels();

    Pixel[][] newPixels = new Pixel[this.newHeight][this.newWidth];

    for( int row = 0; row < this.newHeight; row++) {

      for(int col = 0; col < this.newWidth; col++) {

        double[] ogMapping = computeTransformationPos(row,col);
        newPixels[row][col] = computePixelColor(ogMapping, ogPixels);
      }
    }


    this.imgStorage.addImage(destName,
            new Image(newHeight, newWidth, newPixels));
  }

  /**
   * Computes the color of the four integer pixel locations around a
   *        floating-point location to compute the color at the
   *        desired location.
   * @param pixels - original image's pixels
   * @param ogMapping - location in the original image that maps to
   *                  the downsized position.
   */
  private Pixel computePixelColor(double[] ogMapping, Pixel[][] pixels) {


    int xFloored = (int) (Math.floor(ogMapping[0]));
    int xCeiling = (int) (Math.ceil(ogMapping[0]));

    int yFloored = (int) (Math.floor(ogMapping[1]));
    int yCeiling = (int) (Math.ceil(ogMapping[1]));

    if (ogMapping[0] == Math.floor(ogMapping[0]) &&
            ogMapping[1] == Math.floor(ogMapping[1])) {

      Pixel target = pixels[xFloored][yFloored];

      return new Pixel(target.getR(), target.getG(), target.getB());
    }


    Pixel pixelA = pixels[xFloored][yFloored];
    Pixel pixelB = pixels[xCeiling][yFloored];
    Pixel pixelC = pixels[xFloored][yCeiling];
    Pixel pixelD = pixels[xCeiling][yCeiling];

    List<Pixel> surroundingPixels = new ArrayList<>();
    surroundingPixels.add(pixelA);
    surroundingPixels.add(pixelB);
    surroundingPixels.add(pixelC);
    surroundingPixels.add(pixelD);

    int resultingR = 0;
    int resultingG = 0;
    int resultingB = 0;

    resultingR =
            floatingPointColorCalculator(surroundingPixels, "red",
                    xFloored, xCeiling, yFloored, yCeiling, ogMapping);

    resultingG =
            floatingPointColorCalculator(surroundingPixels, "green",
                    xFloored, xCeiling, yFloored, yCeiling, ogMapping);

    resultingB =
            floatingPointColorCalculator(surroundingPixels, "blue",
                    xFloored, xCeiling, yFloored, yCeiling, ogMapping);

    return new Pixel(resultingR, resultingG, resultingB);


  }

  /**
   * Calculates the color of a mapped pixel based on its surrounding
   *        4 pixels, and a provided color channel to focus on
   * @param sp
   * @param color
   * @return
   */
  private int floatingPointColorCalculator(List<Pixel> sp, String color,
                                           int xFloor, int xCeil,
                                           int yFloor, int yCeil, double[] ogMapping) {

    int colorA = 0;
    int colorB = 0;
    int colorC = 0;
    int colorD = 0;

    if(color.equals("red")) {

       colorA = sp.get(0).getR();
       colorB = sp.get(1).getR();
       colorC = sp.get(2).getR();
       colorD = sp.get(3).getR();
    }

    else if (color.equals("green")) {

      colorA = sp.get(0).getG();
      colorB = sp.get(1).getG();
      colorC = sp.get(2).getG();
      colorD = sp.get(3).getG();
    }

    else {

      colorA = sp.get(0).getB();
      colorB = sp.get(1).getB();
      colorC = sp.get(2).getB();
      colorD = sp.get(3).getB();
    }

    double m = (colorB * (ogMapping[0] - xFloor)) +
            (colorA * ((xFloor + 1) - ogMapping[0]));

    double n = (colorD * (ogMapping[0] - xFloor)) +
            (colorC * ((xFloor + 1) - ogMapping[0]));

      return (int) ((n * (ogMapping[1] - yFloor)) + (m * ((yFloor + 1) - ogMapping[1])));

  }

  /**
   * Transforms a given position into a primed position that
   *        maps into the newly downsized image.
   * @param row - Row pos of the original pixel location.
   * @param col - Col pos of the original pixel location.
   */
  private double[] computeTransformationPos(int row, int col) {

      return new double[]{row * this.heightRatio,col * this.widthRatio};
  }
}
