package controller;

import java.util.HashMap;
import java.util.Map;

import model.Image;
import model.ImageStorage;
import modifications.Flip;
import modifications.Greyscale;
import modifications.Load;
import modifications.PPMModification;
import modifications.Save;
import modifications.filters.BlurFilter;
import modifications.filters.SharpenFilter;
import modifications.transformations.GreyscaleTransform;
import modifications.transformations.Sepia;
import view.IView;

/**
 * Controller for graphical user interface.
 * Has all the functions that are supported in text program in graphical form.
 */
public class GUIController implements ImageProcessingController {

  private Image image;
  private final IView view;
  private final ImageStorage imgStorage;

  private Map<String, PPMModification> knownCommands;

  /**
   * Controller to handle interactions on the GUI view
   *        for the image processing software.
   * @param view - GUI view of the software.
   */
  public GUIController(IView view) {
    this.view = view;
    this.imgStorage = new ImageStorage();
    configureMappings();
    configureButtonListeners();
  }

  /**
   * Starts the Image Processing software with a GUI view, and a default image to work on.
   */
  @Override
  public void startEditor() {

    Load func = new Load(imgStorage, "download.jpeg", "current");
    Image dog = null;

    try {
      func.runFunction();
      dog = imgStorage.getImage("current");
    } catch (Exception e) {

      System.out.println(e);
    }
    this.view.setPanelImage(dog);
    this.view.makeVisible();
  }

  /**
   * Assigns a string action command to an image transformation.
   */
  public void configureButtonListeners() {

    ButtonListener buttonListening = new ButtonListener(this.view,
            this.imgStorage);
    buttonListening.setMap(this.knownCommands);

    this.view.setCommandButtonListener(buttonListening);
  }

  /**
   * Assigns an action command to a relevant image modification function.
   */
  public void configureMappings() {

    this.knownCommands = new HashMap<>();

    knownCommands.put("load", new Load(this.imgStorage, "download.jpeg", "current"));

    knownCommands.put("vertical-flip", new Flip(this.imgStorage, "vertical",
            "current", "current"));

    knownCommands.put("horizontal-flip", new Flip(this.imgStorage, "horizontal",
            "current", "current"));

    knownCommands.put("value-component", new Greyscale(imgStorage,
            "value-component", "current", "current"));

    knownCommands.put("red-component", new Greyscale(imgStorage, "red-component",
            "current", "current"));

    knownCommands.put("green-component", new Greyscale(imgStorage,
            "green-component", "current", "current"));

    knownCommands.put("blue-component", new Greyscale(imgStorage, "blue-component",
            "current", "current"));


    knownCommands.put("luma-component", new Greyscale(imgStorage, "luma-component",
            "current", "current"));


    knownCommands.put("intensity-component", new Greyscale(imgStorage, "intensity-component",
            "current", "current"));


    knownCommands.put("greyscale", new GreyscaleTransform(imgStorage,
            "current", "current"));


    knownCommands.put("save", new Save(imgStorage, "current", "current"));

    knownCommands.put("blur", new BlurFilter(imgStorage,
            "current", "current"));


    knownCommands.put("sharpen", new SharpenFilter(imgStorage,
            "current", "current"));


    knownCommands.put("sepia", new Sepia(imgStorage,
            "current", "current"));

  }
}
