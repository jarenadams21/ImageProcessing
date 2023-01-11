package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import model.Image;
import model.ImageStorage;
import modifications.Flip;
import modifications.Greyscale;
import modifications.Load;
import modifications.PPMModification;
import modifications.filters.BlurFilter;
import modifications.filters.SharpenFilter;
import modifications.transformations.GreyscaleTransform;
import modifications.transformations.Sepia;
import view.IView;

/**
 * Mock controller for tests. has the same functions as normal controller.
 */
public class GUIMockController implements ImageProcessingController, ActionListener {

  private Image image;
  private final IView view;
  private final ImageStorage imgStorage;

  private Map<String, PPMModification> knownCommands;

  private final StringBuilder stringBuilder;

  /**
   * Mock controller class for testing purposes.
   * @param view - GUI view of the image software.
   * @param stringBuilder - Stringbuilder to track information passage.
   */
  public GUIMockController(IView view, StringBuilder stringBuilder) {
    this.view = view;
    this.imgStorage = new ImageStorage();
    configureMappings();
    configureButtonListeners();
    this.stringBuilder = stringBuilder;

    Load func = new Load(imgStorage, "download.jpeg", "current");

    try {
      func.runFunction();
      this.image = imgStorage.getImage("current");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Override
  public void startEditor() {
    this.view.setPanelImage(this.image);
    this.view.makeVisible();
  }

  /**
   * Assigns a string action command to an image transformation.
   */
  public void configureButtonListeners() {
    this.view.setCommandButtonListener(this);
  }

  /**
   * Assigns an action command to a relevant image modification function.
   */
  public void configureMappings() {

    this.knownCommands = new HashMap<>();

    // TODO.
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


    // TODO.
    //knownCommands.put("save", new Save(imgStorage, "current", "current"));

    knownCommands.put("blur", new BlurFilter(imgStorage,
            "current", "current"));


    knownCommands.put("sharpen", new SharpenFilter(imgStorage,
            "current", "current"));


    knownCommands.put("sepia", new Sepia(imgStorage,
            "current", "current"));


    // TODO.
    // knownCommands.put("file", new RunFile(imgStorage, "current"));

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (this.knownCommands.containsKey(e.getActionCommand())) {

      try {
        this.knownCommands.get(e.getActionCommand()).runFunction();
        this.stringBuilder.append(e.getActionCommand());
        view.setPanelImage(this.imgStorage.getImage("current"));

      } catch (Exception error) {
        throw new IllegalArgumentException(String.valueOf(error));
      }
    }
  }
}
