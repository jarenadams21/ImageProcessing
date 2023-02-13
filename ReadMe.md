# Image Processor with an implemented GUI view using Java Swing, allows for various edits including greyscale, transforms, and more!

Read Me

Three packages make up this image processing project, interactions, model, and modifications. In the interactions package, you will find the ImageUtil class which contains the readPPM method. The readPPM method takes a file name, attempts to retrieve that file, then converts it to our Image object. This leads us to the next package called model which houses the Image, ImageStorage, and the Pixel. The Pixel object is a simple object that holds an r, g, and b value. We made this to represent our image as a two-dimensional list of Pixels rather than all of the individual RGB values. The Image object contains all of the metadata and data that the ppm image had, but is better organized for us to interact with all the individual components of the ppm file. We then have an ImageStorage to store the Image objects we create. This class contains a hashmap of Strings representing the names of Images and their respective Image objects. The modifications package contains all of the function objects that take in an image and modifies it in a specific way. These functions are as follows: Brighten, changes all pixels by the provided argument brightens with a positive value and darkens with a negative value; flip, flips the image horizontally or vertically depending on the argument; greyscale, turns the image to greyscale depending on different components according to the argument provided; load, retrieve an image from the file system and adds it to our ImageStorage; and save, writes a new file and saves the image to the file system. These functions all implement our PPMModification interface. The main function adds all of these functions to our knownCommands hashmap, then prompts the user for input in the format: function-name <space> optional-function-argument <space> image-name <space> desired-new-image-name. The exception to this format is for load and save which are in the format: function-name <space> file-name <space> image-name. There is also a file that contains our starting images and testing images as well as a testing file.  There is also a controller interface as well as an implementation of that controller so that the main method does not directly change the model.

The script is meant to be typed in line by line one at a time.  Each new one is a separate command and after running each line provided, there should be new modified images in the ImageProcessing folder.

Example images were created by us using GIMP.  We aren't citing anyone because we didn't take the images from anywhere.

Updated GUI Info:

To implement a Java Swing view, we used a main panel so that the entire GUI view was scrollable. We then placed sub panels representing the image and histogram, save & load, and image modifications to organize the panels meaningfully. To implement the actual functionality of button pressing we created a GUI controller that communicated with the GUI view as we figured this would behave much differently than our traditional controller. This controller uses a ButtonListener class to process all button clicks on the view, and updates the current image in the view through the model(storage). All functionality from the previous assignments was retained, and we only needed to create a view and GUI controller to make our program functional. You also may notice 3 new classes involving the Histogram, and this was to simplify the code in our view. The histogram itself required a decent amount of new code, and we needed to produce it as an ImageIcon so that it could be added to our view.


# Extra Credit Updates:

## Level 1 : Image Downsizing
### Algorithm/Behavior
  * I implemented image downsizing to take in a percentage by the user to indicate the scale to which the image should be downsized. This takes the ceiling of the result of the original width and height multiplied by the percentage.
    * I chose this approach because it simplified things on the user's end, and taking the ceiling provides a safe insurance on the downsized image having color in each pixel slot.

### Changes in parts

--> Across all classes, I changed instances of ImageStorage to a Storage interface with promised public methods. I also implement an IInterface which is now the interface responsible for the public methods of an Image.
* IView
  * Promised a getPercent() method in this interface so that the button lister was able to retrieve the current value in the percentage box when attempting to downsize an image.
  * Promised a setText functionality for the error box whenever an invalid text box argument was provided.
 * SwingProcessorGUI
   * Added a JButton and JTextField to handle events for the downsize button. When a valid percentage that is 0 <= x <= 100, the program carries out the downsizing.
   * Also implemented the getPercent method(), returning the string that is currently in the JTextField.
   * Implemented an uneditable text field and associated setText method for it to display errors suitably.
   * Added the new JButton to the list of buttons to listen to
 * Controllers
   * Standard Terminal Controller (ControllerImpl)
     * Added downsizing into the list of commands, taking all input from a Scanner.
   * ButtonListener
     * Added downsizing to the list of dynamic commands to place into the map
       * Brightness and Downsize both are newly added to the map whenever there is button activity.
   * Downsize
     * New PPMModification implementation to offer functionality that downsizes an image.
