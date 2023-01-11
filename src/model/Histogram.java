package model;

import java.util.HashMap;
import java.util.Map;

import model.Image;
import model.Pixel;

/**
 * Represents a Histogram of an image
 * showing its red, blue, green, and intensity.
 */
public class Histogram {
  private Image image;

  private HashMap<Integer, Integer> red;
  private HashMap<Integer, Integer> green;
  private HashMap<Integer, Integer> blue;
  private HashMap<Integer, Integer> intensity;
  int maxFreq;
  int minFreq;

  /**
   * Histogram constructor.
   *
   * @param image - Image to construct a histogram for.
   */
  public Histogram(Image image) {
    this.image = image;
    red = new HashMap<>();
    green = new HashMap<>();
    blue = new HashMap<>();
    intensity = new HashMap<>();
    setValues();
    this.maxFreq = gMaxFrequency();
    this.minFreq = gMinFrequency();
  }

  private void setValues() {
    Pixel[][] pixels = this.image.getPixels();
    for (Pixel[] row : pixels) {
      for (Pixel each : row) {

        int redFreq = red.getOrDefault(each.getR(), 1);
        int greenFreq = green.getOrDefault(each.getG(), 1);
        int blueFreq = blue.getOrDefault(each.getB(), 1);

        red.put(each.getR(), redFreq + 1);
        green.put(each.getG(), greenFreq + 1);
        blue.put(each.getB(), blueFreq + 1);

        int newIntensity = (each.getR() + each.getG() + each.getB()) / 3;
        int intenFreq = intensity.getOrDefault(newIntensity, 1);

        intensity.put(newIntensity, intenFreq + 1);

      }
    }
  }


  public HashMap<Integer, Integer> getR() {
    return red;
  }

  public HashMap<Integer, Integer> getG() {
    return green;
  }

  public HashMap<Integer, Integer> getB() {
    return blue;
  }

  public HashMap<Integer, Integer> getI() {
    return intensity;
  }

  private int getMaxFrequencyComponent(HashMap<Integer, Integer> comp) {
    int max = 0;
    for (Map.Entry<Integer, Integer> entry : comp.entrySet()) {
      max = Math.max(max, entry.getValue());
    }
    return max;
  }

  private int getMinFrequencyComponent(HashMap<Integer, Integer> comp) {
    int min = 0;
    for (Map.Entry<Integer, Integer> entry : comp.entrySet()) {
      min = Math.min(min, entry.getValue());
    }
    return min;
  }

  private int gMaxFrequency() {
    return Math.max(getMaxFrequencyComponent(red),
            Math.max(getMaxFrequencyComponent(green),
                    Math.max(getMaxFrequencyComponent(blue),
                            getMaxFrequencyComponent(intensity)
                    )));
  }

  public int getMaxFrequency() {
    return this.maxFreq;
  }

  private int gMinFrequency() {
    return Math.min(getMinFrequencyComponent(red),
            Math.min(getMinFrequencyComponent(green),
                    Math.min(getMinFrequencyComponent(blue),
                            getMinFrequencyComponent(intensity)
                    )));
  }

  public int getMinFrequency() {
    return this.minFreq;
  }


}
