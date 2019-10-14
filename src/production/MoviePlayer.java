package production;

/**
 * The MoviePlayer class is a subclass of the Product class and implements all the methods contained
 * inside the MultimediaControl interface. It contains all the methods inside both the Product and
 * MultimediaContol classes, respectively. The MoviePlayer() method is used to set up visual devices
 * in the H2 Database.
 *
 * @author: Andrew Cavallaro
 * @date: 10/14/2019
 */
public class MoviePlayer extends Product implements MultimediaControl {

  /** A new Screen object named Screen that can be used to get the values of a Screen. */
  private final Screen screen;

  /**
   * A new MonitorType object named monitorType that can be used to get the monitor type from the
   * Enum MonitorType.
   */
  private final MonitorType monitorType;

  /**
   * The MoviePlayer constructor takes the following parameters.
   *
   * @param name the name of the movie product
   * @param manufacturer the manufacturer of the movie product
   * @param screen the screen details of the movie product
   * @param monitorType the monitor type of the movie product
   */
  MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, "VISUAL");
    this.monitorType = monitorType;
    this.screen = screen;
  }

  /**
   * The toString() method prints the values of an object to the console. This is what it performs
   * here.
   *
   * @return returns a sentence with all the appropriate values for the proper attributes.
   */
  public String toString() {
    return super.toString()
        + "\nScreen:"
        + "\nResolution: "
        + screen.getResolution()
        + "\nRefresh rate: "
        + screen.getRefreshRate()
        + "\nResponse time: "
        + screen.getResponseTime()
        + "\nMonitor type: "
        + monitorType.toString();
  }

  /** The play() method currently serves no purpose but to print to the console. */
  public void play() {
    System.out.println("Playing movie");
  }

  /** The stop() method currently serves no purpose but to print to the console. */
  public void stop() {
    System.out.println("Stopping movie");
  }

  /** The next() method currently serves no purpose but to print to the console. */
  public void next() {
    System.out.println("Next movie");
  }

  /** The previous() method currently serves no purpose but to print to the console. */
  public void previous() {
    System.out.println("Previous movie");
  }
}
