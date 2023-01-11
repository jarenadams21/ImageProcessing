package view;


import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import model.Image;

import static interactions.ImageUtil.parseImage;
import static interactions.ImageUtil.reverseParseImage;

/**
 * Represents the panel that holds the image being edited
 *        and its associated histogram.
 */
public class ImageHistPanel extends JPanel {
  private BufferedImage image;

  private final JLabel imageLabel;
  private final JLabel histogramLabel;

  /**
   * ImageHistPanel constructor.
   */
  public ImageHistPanel() {
    super();
    this.setBackground(Color.WHITE);
    this.setLayout(new FlowLayout());
    this.imageLabel = new JLabel();
    this.histogramLabel = new JLabel();

    this.image = new BufferedImage(256, 256,
            BufferedImage.TYPE_INT_RGB);
    this.add(imageLabel);
    this.add(histogramLabel);

    setBufferedImage(this.image);

  }

  /**
   * Sets a given buffered image as a panel containing
   *        the image as an icon and its histogram.
   * @param image - The image to be displayed in the panel.
   */
  public void setBufferedImage(BufferedImage image) {
    this.image = image;
    HistogramViewPanel histogramView = new HistogramViewPanel(parseImage(image), 600, 400);

    imageLabel.setIcon(new ImageIcon(this.image));
    histogramLabel.setIcon(histogramView);
  }

  public void setImage(Image image) {

    this.setBufferedImage(reverseParseImage(image));
  }
}
