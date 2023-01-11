package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.ImageStorage;
import model.Storage;
import modifications.Brighten;
import modifications.Downsize;
import modifications.Flip;
import modifications.Greyscale;
import modifications.Load;
import modifications.PPMModification;
import modifications.RunFile;
import modifications.Save;
import modifications.filters.BlurFilter;
import modifications.filters.SharpenFilter;
import modifications.transformations.GreyscaleTransform;
import modifications.transformations.Sepia;

/**
 * Controller impleneation for the image processing controller.
 * This will run the software.
 */
public class ControllerImpl implements ImageProcessingController {

  Scanner sc;
  Storage imgStorage;
  Map<String, Function<Scanner, PPMModification>> knownCommands;

  /**
   * Constructor for the image processing program controller.
   *
   * @param input      takes in user input
   * @param imgStorage takes a storage
   */
  public ControllerImpl(Readable input, Storage imgStorage) {
    if (input == null || imgStorage == null) {
      throw new IllegalArgumentException("Either storage or input is null");
    }

    this.sc = new Scanner(input);
    this.imgStorage = imgStorage;
    this.knownCommands = new HashMap<>();
    knownCommands.put("load", (Scanner s) -> {
      return new Load(imgStorage, s.next(), s.next());
    });

    knownCommands.put("brighten", (Scanner s) -> {
      return new Brighten(imgStorage, s.nextInt(), s.next(), s.next());
    });

    knownCommands.put("downsize", (Scanner s) -> {
      return new Downsize(imgStorage, s.nextInt(), s.next(), s.next());
    });

    knownCommands.put("vertical-flip", (Scanner s) -> {
      return new Flip(imgStorage, "vertical", sc.next(), sc.next());
    });

    knownCommands.put("horizontal-flip", (Scanner s) -> {
      return new Flip(imgStorage, "horizontal", sc.next(), sc.next());
    });

    knownCommands.put("value-component", (Scanner s) -> {
      return new Greyscale(imgStorage, "value-component", sc.next(), sc.next());
    });

    knownCommands.put("red-component", (Scanner s) -> {
      return new Greyscale(imgStorage, "red-component", sc.next(), sc.next());
    });

    knownCommands.put("green-component", (Scanner s) -> {
      return new Greyscale(imgStorage, "green-component", sc.next(), sc.next());
    });

    knownCommands.put("blue-component", (Scanner s) -> {
      return new Greyscale(imgStorage, "blue-component", sc.next(), sc.next());
    });

    knownCommands.put("luma-component", (Scanner s) -> {
      return new Greyscale(imgStorage, "luma-component", sc.next(), sc.next());
    });

    knownCommands.put("intensity-component", (Scanner s) -> {
      return new Greyscale(imgStorage, "intensity-component", sc.next(), sc.next());
    });

    knownCommands.put("greyscale", (Scanner s) -> {
      return new GreyscaleTransform(imgStorage, sc.next(), sc.next());
    });

    knownCommands.put("save", (Scanner s) -> {
      return new Save(imgStorage, sc.next(), sc.next());
    });

    knownCommands.put("blur", (Scanner s) -> {
      return new BlurFilter(imgStorage, sc.next(), sc.next());
    });

    knownCommands.put("sharpen", (Scanner s) -> {
      return new SharpenFilter(imgStorage, sc.next(), sc.next());
    });

    knownCommands.put("sepia", (Scanner s) -> {
      return new Sepia(imgStorage, sc.next(), sc.next());
    });

    knownCommands.put("file", (Scanner s) -> {
      return new RunFile(imgStorage, sc.next());
    });

  }

  /**
   * starts listening to commands that are supported by the program.
   */
  @Override
  public void startEditor() {
    while (sc.hasNext()) {
      String in = sc.next();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        return;
      }

      Function<Scanner, PPMModification> cmd = knownCommands.getOrDefault(in, null);

      if (cmd == null) {
        throw new IllegalArgumentException("Invalid Command");

      } else {
        PPMModification c = cmd.apply(sc);
        try {
          c.runFunction();
        } catch (Exception e) {
          System.out.println(e);
        }
      }
    }
  }

}
