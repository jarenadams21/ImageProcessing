package modifications;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.ImageStorage;
import model.Storage;
import modifications.filters.BlurFilter;
import modifications.filters.SharpenFilter;
import modifications.transformations.GreyscaleTransform;
import modifications.transformations.Sepia;

/**
 * List of commands available to all images in a
 * particular image storage.
 */
public class Commands {

  /**
   * Commands constructor.
   * @param imgStorage - Image storage that the provided modifications will
   *                   retrieve and save to.
   */
  public Commands(Storage imgStorage) {
    Map<String, Function<Scanner, PPMModification>>
            knownCommands = new HashMap<>();
    knownCommands.put("load", (Scanner s) -> {
      return new Load(imgStorage, "img", "img");
    });

    knownCommands.put("brighten", (Scanner s) -> {
      return new Brighten(imgStorage, s.nextInt(), s.next(), s.next());
    });

    knownCommands.put("vertical-flip", (Scanner sc) -> {
      return new Flip(imgStorage, "vertical", sc.next(), sc.next());
    });

    knownCommands.put("horizontal-flip", (Scanner sc) -> {
      return new Flip(imgStorage, "horizontal", sc.next(), sc.next());
    });

    knownCommands.put("value-component", (Scanner sc) -> {
      return new Greyscale(imgStorage, "value-component", sc.next(), sc.next());
    });

    knownCommands.put("red-component", (Scanner sc) -> {
      return new Greyscale(imgStorage, "red-component", sc.next(), sc.next());
    });

    knownCommands.put("green-component", (Scanner sc) -> {
      return new Greyscale(imgStorage, "green-component", sc.next(), sc.next());
    });

    knownCommands.put("blue-component", (Scanner sc) -> {
      return new Greyscale(imgStorage, "blue-component", sc.next(), sc.next());
    });

    knownCommands.put("luma-component", (Scanner sc) -> {
      return new Greyscale(imgStorage, "luma-component", sc.next(), sc.next());
    });

    knownCommands.put("intensity-component", (Scanner sc) -> {
      return new Greyscale(imgStorage, "intensity-component", sc.next(), sc.next());
    });

    knownCommands.put("greyscale", (Scanner sc) -> {
      return new GreyscaleTransform(imgStorage, sc.next(), sc.next());
    });

    knownCommands.put("save", (Scanner sc) -> {
      return new Save(imgStorage, sc.next(), sc.next());
    });

    knownCommands.put("blur", (Scanner sc) -> {
      return new BlurFilter(imgStorage, sc.next(), sc.next());
    });

    knownCommands.put("sharpen", (Scanner sc) -> {
      return new SharpenFilter(imgStorage, sc.next(), sc.next());
    });

    knownCommands.put("sepia", (Scanner sc) -> {
      return new Sepia(imgStorage, sc.next(), sc.next());
    });

    knownCommands.put("file", (Scanner sc) -> {
      return new RunFile(imgStorage, sc.next());
    });
  }
}
