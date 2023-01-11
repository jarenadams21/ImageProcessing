package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Image;

/**
 * Represents the GUI view of the image processing
 *        software.
 */
public class SwingImageProcessorGUI extends JFrame
        implements IView {

  private final ImageHistPanel imageHistPanel;

  private final ArrayList<JButton> actionableButtonList;

  private final JTextField brightenValue;

  private final JTextField downPercent;

  private JTextField errorBox;

  private JLabel fileOpenDisplay;

  private JLabel fileSaveDisplay;

  /**
   * SwingImageProcessorGUI that sets up the GUI view.
   */
  public SwingImageProcessorGUI() {

    super("Image Processor");
    this.setSize(1200, 1200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    JScrollPane mainScrollPane = new JScrollPane((mainPanel));
    this.actionableButtonList = new ArrayList<>();

    // Image & Histogram
    imageHistPanel = new ImageHistPanel();

    mainPanel.add(imageHistPanel);
    //  imageHistPanel.setImage(dog);
    add(mainScrollPane);

    setVisible(true);
    

    //file open
    fileOpenDisplay = new JLabel();
    JPanel dialogBoxesPanel = new JPanel();
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    makeButton(fileopenPanel, "load");
    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    //file save
    fileSaveDisplay = new JLabel();
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    makeButton(filesavePanel, "save");
    fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay);

    mainPanel.add(fileopenPanel);
    mainPanel.add(filesavePanel);


    // Transformation Buttons
    JPanel allButtons = new JPanel();
    allButtons.setLayout(new GridLayout(4, 4, 10, 10));
    allButtons.setBorder(BorderFactory.createTitledBorder("Image Modifications"));
    mainPanel.add(allButtons);

    //  Buttons without Input
    makeButton(allButtons, "blur");
    makeButton(allButtons, "sharpen");
    makeButton(allButtons, "greyscale");
    makeButton(allButtons, "red-component");
    makeButton(allButtons, "blue-component");
    makeButton(allButtons, "green-component");
    makeButton(allButtons, "sepia");
    makeButton(allButtons, "horizontal-flip");
    makeButton(allButtons, "vertical-flip");
    makeButton(allButtons, "luma-component");
    makeButton(allButtons, "value-component");
    makeButton(allButtons, "intensity-component");


    // ImageEditing Buttons that need Input
    JPanel brighten = new JPanel();
    brighten.setLayout(new FlowLayout());
    mainPanel.add(brighten);
    JButton brightenButton = new JButton("brighten");
    this.brightenValue = new JTextField(5);
    brighten.add(brightenButton);
    brighten.add(this.brightenValue);
    this.actionableButtonList.add(brightenButton);

    JPanel downsize = new JPanel();
    downsize.setLayout(new FlowLayout());
    mainPanel.add(downsize);
    JButton downsizeButton = new JButton("downsize");
    this.downPercent = new JTextField(5);
    downsize.add(downsizeButton);
    downsize.add(this.downPercent);
    this.actionableButtonList.add(downsizeButton);

    JPanel information = new JPanel();
    information.setLayout(new FlowLayout());
    mainPanel.add(information);
    this.errorBox = new JTextField(35);
    this.errorBox.setEditable(false);
    information.add(this.errorBox);
  }

  /**
   * Provides a popup that allows a user to load an image from their
   *        computer.
   * @return - Returns the filepath the user wants to load an image from.
   */
  public String runLoadPanel() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "gif");
    String filePath = "";
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(SwingImageProcessorGUI.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      filePath = f.getAbsolutePath();
      fileOpenDisplay.setText(filePath);
    }
    return filePath;
  }

  /**
   * Targets the desired file path to where the user wants to save an image to.
   * @return - Returns the file path to save the current image.
   */
  public String runSavePanel() {
    String filePath = "";
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(SwingImageProcessorGUI.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      filePath = f.getAbsolutePath();
      fileSaveDisplay.setText(filePath);
    }
    return filePath;
  }

  @Override
  public void displayErrors(String s) {
    this.errorBox.setText(s);
  }

  private void makeButton(JPanel main, String name) {
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());
    main.add(panel);
    JButton button = new JButton(name);
    button.setActionCommand(name);
    this.actionableButtonList.add(button);
    panel.add(button);
  }

  @Override
  public void makeVisible() {
    // This is controlled by the controller and thus does not
    // need implementation.
  }

  public String getBrightenValue() {

    return this.brightenValue.getText();
  }

  public String getPercent() {

    return this.downPercent.getText();
  }

  @Override
  public void setCommandButtonListener(ActionListener actionEvent) {

    for (JButton b : this.actionableButtonList) {

      b.addActionListener(actionEvent);
    }
  }

  @Override
  public void setPanelImage(Image img) {

    this.imageHistPanel.setImage(img);
  }
}