//package modifications;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import javax.imageio.ImageIO;
//
//import model.Image;
//import model.ImageStorage;
//import model.Pixel;
//
///**
// * Reads normal images and saves them as Image in imageStorage.
// */
//public class ReadNormalImages implements PPMModification{
//
//  private final ImageStorage imgStorage;
//  String fileName;
//  String destName;
//
//  /**
//   * constructor to create Load object that can store images. This is for testing.
//   *
//   * @param imgStorage stores images
//   */
//  public ReadNormalImages(ImageStorage imgStorage) {
//    this.imgStorage = imgStorage;
//  }
//
//  /**
//   * constructor to create Load object that can store images.
//   *
//   * @param imgStorage  stores images
//   * @param fileName takes in a file to load
//   * @param destName saves the image with destName
//   */
//  public ReadNormalImages(ImageStorage imgStorage, String fileName, String destName) {
//    this.imgStorage = imgStorage;
//    this.fileName = fileName;
//    this.destName = destName;
//  }
//
//  public Image fileToImage(String fileName){
//    try
//    {
//      BufferedImage image = ImageIO.read(new File(fileName));
//      return parseImage(image);
//    }
//    catch (IOException e)
//    {
//      System.out.println(e.toString());
//    }
//    return null;
//  }
//
//  @Override
//  public void runFunction() throws Exception {
//    Image img;
//
//    try {
//      BufferedImage image = ImageIO.read(new File(fileName));
//      img = parseImage(image);
//    } catch (FileNotFoundException e) {
//      throw new Exception(String.valueOf(e));
//    }
//
//    this.imgStorage.addImage(this.destName, img);
//  }
//
//
//
//}