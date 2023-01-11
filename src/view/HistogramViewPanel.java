package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import javax.swing.Icon;

import model.Histogram;
import model.Image;

/**
 * Represents the panel that contains a histogram
 *        in the form of an Icon to be inserted
 *        into the GUI view.
 */
public class HistogramViewPanel implements Icon {
  Image image;
  int width;
  int height;

  float barWidth;
  int maxBarHeight;

  int maxFrequency;

  Histogram histogram;

  /**
   * HistogramViewPanel constructor.
   * @param image - Original image to construct the histogram.
   * @param width - Width of the panel.
   * @param height - Height of the panel.
   */
  public HistogramViewPanel(Image image, int width, int height) {
    this.image = image;
    this.width = width;
    this.height = height;

    this.barWidth = width / (float) 256;
    this.maxBarHeight = (int) Math.floor(0.85 * height);
    this.histogram = new Histogram(image);
    this.maxFrequency = histogram.getMaxFrequency();

  }

  @Override
  public void paintIcon(Component c, Graphics g, int x, int y) {

    if (histogram != null) {
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setColor(Color.DARK_GRAY);
      g2d.drawRect(20, 20, width, height);
      drawHistorgram(g2d, histogram.getR(), new Color(255, 0, 0, 40));
      drawHistorgram(g2d, histogram.getG(), new Color(0, 255, 40, 40));
      drawHistorgram(g2d, histogram.getB(), new Color(0, 0, 255, 40));
      drawHistorgram(g2d, histogram.getI(), new Color(0, 0, 0, 40));

      g2d.dispose();

    }
  }

  private float normalise(float inValue, float min, float max) {
    return (inValue - min) / (max - min);
  }

  private void drawHistorgram(Graphics2D g2d, HashMap<Integer, Integer> mapFreq, Color color) {
    float xPos = 20;
    for (Integer key : mapFreq.keySet()) {
      float value = normalise(mapFreq.get(key),
              this.histogram.getMinFrequency(), this.histogram.getMaxFrequency());

      float barHeight = (value * this.height);
      float yPos = height + 20 - barHeight;
      g2d.setColor(color);

      Rectangle2D bar = new Rectangle2D.Float(
              xPos, yPos, barWidth, barHeight);
      g2d.fill(bar);
      g2d.setColor(Color.DARK_GRAY);
      g2d.draw(bar);
      xPos += barWidth;

    }
  }


  @Override
  public int getIconWidth() {
    return this.width;
  }

  @Override
  public int getIconHeight() {
    return this.height;
  }
}



