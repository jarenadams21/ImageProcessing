import java.io.InputStreamReader;
import java.io.StringReader;

import controller.ControllerImpl;
import controller.GUIController;
import controller.ImageProcessingController;
import model.ImageStorage;
import view.IView;
import view.SwingImageProcessorGUI;

/**
 * Main class to run the program in 3 different possible ways through a jar.
 * The 3 options
 * include a user entering commands through the terminal, a script file, or GUI view.
 */
public class TerminalMain {

  /**
   * Main method to run the 3 different command lines that may be
   * passed to the software.
   *
   * @param args - Potential arguments to process.
   */
  public static void main(String[] args) {
    ImageStorage imgStorage = new ImageStorage();
    ImageProcessingController controller;

    if (args.length == 1 && args[0].equals("-text")) {
      controller =
              new ControllerImpl(new InputStreamReader(System.in), imgStorage);

    } else if (args.length == 2 && args[0].equals("-file")) {
      StringBuilder stringInp = new StringBuilder();

      stringInp.append(args[0].replace("-", ""));
      stringInp.append(" " + args[1]);

      controller =
              new ControllerImpl(new StringReader(stringInp.toString()),
                      imgStorage);
    } else if (args.length > 0) {

      StringBuilder stringInp = new StringBuilder();
      for (String each : args) {
        stringInp.append(each + " ");
      }

      controller =
              new ControllerImpl(new StringReader(stringInp.toString()),
                      imgStorage);


    } else {
      IView guiView = new SwingImageProcessorGUI();
      controller = new GUIController(guiView);
    }

    controller.startEditor();


  }
}
