package modifications;

/**
 * an interface to be implemented by the image modification functions.
 */
public interface PPMModification {
  /**
   * runs this function on the image.
   *
   * @throws Exception if the file or image was not found
   */
  void runFunction() throws Exception;
}
