package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import model.ImageStorage;
import model.Storage;
import modifications.Brighten;
import modifications.Downsize;
import modifications.Load;
import modifications.PPMModification;
import modifications.Save;
import view.IView;

/**
 * Button listner listend for all the actions and commands that buttons run in the view.
 */
public class ButtonListener implements ActionListener {

  private final IView view;
  private Map<String, PPMModification> commands;

  private final Storage storage;

  /**
   * constructor for button listner.
   *
   * @param view    takes a view with buttons
   * @param storage iamge storage to keep track of all the images.
   */
  public ButtonListener(IView view, Storage storage) {

    this.view = view;
    this.storage = storage;
  }

  /**
   * sets the command map to provided map of commands.
   * this is used to run functions
   *
   * @param commands map of commands from string to PPM Modification
   */
  public void setMap(Map<String, PPMModification> commands) {

    this.commands = commands;
  }

  /**
   * Listens to button events from the view.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {


    if(e.getActionCommand().equals("downsize")) {

      try {
        commands.put("downsize", new Downsize(storage,
                Integer.parseInt(view.getPercent()), "current", "current"));
      } catch (Exception error) {

        view.displayErrors("Invalid input, percentage must be an integer 0 =< x < 100");
        return;
      }

    }
    try {
      commands.put("brighten", new Brighten(storage,
              Integer.parseInt(view.getBrightenValue()), "current", "current"));
    } catch (Exception error) {

      // Do Nothing.
    }


    if (commands.containsKey(e.getActionCommand())) {

      if (e.getActionCommand().equals("load")) {
        String imagePath = this.view.runLoadPanel();
        try {
          Load l = new Load(storage, imagePath, "current");
          l.runFunction();
          view.setPanelImage(storage.getImage("current"));
        } catch (Exception error) {
          throw new IllegalArgumentException(String.valueOf(error));
        }
      } else if (e.getActionCommand().equals( "save")) {
        String imagePath = this.view.runSavePanel();
        try {
          Save l = new Save(storage, imagePath, "current");
          l.runFunction();
          view.setPanelImage(storage.getImage("current"));
        } catch (Exception error) {
          throw new IllegalArgumentException(String.valueOf(error));
        }
      } else {
        try {
          commands.get(e.getActionCommand()).runFunction();
          view.setPanelImage(storage.getImage("current"));
        } catch (Exception error) {
          view.displayErrors(String.valueOf(error));
          throw new IllegalArgumentException(String.valueOf(error));
        }
      }

    }
  }

}
