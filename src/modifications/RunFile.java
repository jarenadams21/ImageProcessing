package modifications;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Scanner;

import controller.ControllerImpl;
import controller.ImageProcessingController;
import model.ImageStorage;
import model.Storage;

/**
 * Runs a script file so that a user may use a file of valid commands as input
 * for the image processing software.
 */
public class RunFile implements PPMModification {

  private final Storage imageStorage;
  private final String filename;

  /**
   * Constructor for RunFile.
   *
   * @param imageStorage - Image storage where images are currently being stored
   *                     *                     and retrieved.
   * @param filename     - File name that holds valid scripts/commands to use
   *                     in the image processing software.
   */
  public RunFile(Storage imageStorage, String filename) {
    this.imageStorage = imageStorage;
    this.filename = filename;

  }

  @Override
  public void runFunction() throws Exception {
    String commands = getFileData().toString();
    StringReader input = new StringReader(commands);
    ImageProcessingController controller = new ControllerImpl(input, imageStorage);
    controller.startEditor();
  }

  /**
   * Returns the data of the file for this instance of RunFile,
   * appending each line to the Readable to be read by the controller
   * of the image processing software.
   *
   * @return - Returns a string representing the data/information in the file.
   * @throws FileNotFoundException - If no file is found under the provided name.
   */
  private StringBuilder getFileData() throws FileNotFoundException {
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(this.filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      builder.append(s + System.lineSeparator());
    }

    return builder;
  }
}
