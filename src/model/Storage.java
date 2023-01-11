package model;

public interface Storage {

  /**
   * add an image to imageStorage.
   *
   * @param name  takes a name for an image
   * @param image takes an image to store in storage for a given name
   */
  public void addImage(String name, Image image);

  /**
   * retrieves an image from the storage.
   *
   * @param name takes in the name of image.
   * @return returns an image for a given name.
   * @throws Exception if could not find an image.
   */
  public Image getImage(String name) throws Exception;
}
