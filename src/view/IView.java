package view;

import java.awt.event.ActionListener;

import model.Image;

/**
 * Represents a graphical view for image processing.
 */
public interface IView {
  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void makeVisible();

  /**
   * Provide the view with an action listener for
   * the button that should cause the program to
   * process a command. This is so that when the button
   * is pressed, control goes to the action listener
   *
   * @param actionEvent - Sets an action listener
   *                    for an actionable event/entity.
   */
  void setCommandButtonListener(ActionListener actionEvent);


  void setPanelImage(Image img);

  /**
   * Returns the desired value to brighten the current image
   * by in the form of a string.
   *
   * @return - A string representing an integer.
   */
  String getBrightenValue();

  /**
   * Returns the percentage value in the downsize box.
   * @return - Returns the percentage to downsize the image by.
   */
  String getPercent();

  String runLoadPanel();

  String runSavePanel();

  /**
   * Provides an indication that an error was thrown in the view.
   */
  void displayErrors(String s);

}
