package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to store all the images. You can access images by their name.
 * This has a map of string to images.
 */
public class ImageStorage implements Storage {
  Map<String, Image> images;

  /**
   * Creates image storage.
   */
  public ImageStorage() {
    images = new HashMap<>();
  }

  /**
   * add an image to imageStorage.
   *
   * @param name  takes a name for an image
   * @param image takes an image to store in storage for a given name
   */
  public void addImage(String name, Image image) {
    this.images.put(name, image);
  }

  /**
   * retrieves an image from the storage.
   *
   * @param name takes in the name of image.
   * @return returns an image for a given name.
   * @throws Exception if could not find an image.
   */
  public Image getImage(String name) throws Exception {
    if (this.images.get(name) == null) {
      throw new Exception("Image not in storage");
    }
    return this.images.get(name);
  }
}
