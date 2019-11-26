package production;

/**
 * The Screen class is used to set and get the details of the movie products. It implements all the
 * methods of the ScreenSpec interface.
 *
 * @author: Andrew Cavallaro
 * @date: 11/26/2019
 */
public class Screen implements ScreenSpec {

  /** Field member "resolution" is an attribute that belongs to the movie devices. */
  private final String resolution;

  /** Field member "refreshRate" is an attribute that belongs to the movie devices. */
  private final int refreshRate;

  /** Field member "responseTime" is an attribute that belongs to the movie devices. */
  private final int responseTime;

  /**
   * The constructor for the Screen class has the following parameters: "resolution", "refreshRate",
   * and "responseTime". All arguments are then assigned to the field members "resolution",
   * "refreshRate", and "responseTime", respectively.
   *
   * @param resolution for the "resolution" of the movie product
   * @param refreshRate for the "refresh rate" of the movie product
   * @param responseTime for the "response time" of the movie product
   */
  Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  /**
   * The getResolution() method gets the "resolution" of the movie product in the database.
   *
   * @return returns the movie product's "resolution"
   */
  public String getResolution() {
    return resolution;
  }

  /**
   * The getRefreshRate() method gets the "refresh rate" of the product in the database.
   *
   * @return returns the movie product's "refresh rate"
   */
  public int getRefreshRate() {
    return refreshRate;
  }

  /**
   * The getResponseTime() method gets the "response time" of the product in the database.
   *
   * @return returns the movie product's "response time"
   */
  public int getResponseTime() {
    return responseTime;
  }

  /**
   * The toString() method prints the values of an object to the console. This is what it performs
   * here.
   *
   * @return returns a sentence with all the appropriate values for the proper attributes.
   */
  public String toString() {
    return "Screen:"
        + "\nResolution: "
        + resolution
        + "\nRefresh rate: "
        + refreshRate
        + "\nResponse time: "
        + responseTime;
  }
}
