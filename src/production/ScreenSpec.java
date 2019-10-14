package production;

/**
 * The ScreenSpec interface is used to instantiate the getResolution(), getRefreshRate(), and
 * getResponseTime() methods.
 *
 * @author: Andrew Cavallaro
 * @date: 10/14/2019
 */
interface ScreenSpec {

  /**
   * The getResolution() method is used to retrieve the movie products resolution.
   *
   * @return returns the screen resolution
   */
  String getResolution();

  /**
   * The getRefreshRate() method is used to retrieve the movie products refresh rate.
   *
   * @return returns the screen refresh rate
   */
  int getRefreshRate();

  /**
   * The getResponseTime() method is used to retrieve the movie products response time.
   *
   * @return returns the screen response time
   */
  int getResponseTime();
}
