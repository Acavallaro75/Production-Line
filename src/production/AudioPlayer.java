package production;

/**
 * The AudioPlayer class is a subclass of the Product class and implements all the methods contained
 * inside the MultimediaControl interface. It contains all the methods inside both the Product and
 * MultimediaContol classes, respectively. The AudioPlayer() method is used to set up audio devices
 * in the H2 Database.
 *
 * @author: Andrew Cavallaro
 * @date: 10/28/2019
 */
public class AudioPlayer extends Product implements MultimediaControl {

  /** Field member supportedAudioFormats is an attribute that belongs to the audio devices. */
  private final String supportedAudioFormats;

  /** Field member supportedPlaylistFormat is an attribute that belongs to the audio devices. */
  private final String supportedPlaylistFormat;

  /**
   * The constructor for the AudioPlayer class has the following parameters.
   *
   * @param name for the name of the audio product
   * @param manufacturer for the manufacturer of the audio product
   * @param supportedAudioFormats for the supportedAudioFormat of the audio product
   * @param supportedPlaylistFormat for the supportedPlaylistFormat of the audio product
   */
  AudioPlayer(
      String name,
      String manufacturer,
      String supportedAudioFormats,
      String supportedPlaylistFormat) {
    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormat = supportedPlaylistFormat;
  }

  /**
   * The toString() method prints the values of an object to the console. This is what it performs
   * here.
   *
   * @return returns a sentence with all the appropriate values for the proper attributes.
   */
  public String toString() {
    return super.toString()
        + "\nSupported Audio Formats: "
        + supportedAudioFormats
        + "\nSupported Playlist Formats: "
        + supportedPlaylistFormat;
  }

  /** The play() method currently serves no purpose but to print to the console. */
  public void play() {
    System.out.println("Playing");
  }

  /** The stop() method currently serves no purpose but to print to the console. */
  public void stop() {
    System.out.println("Stopping");
  }

  /** The next() method currently serves no purpose but to print to the console. */
  public void next() {
    System.out.println("Next");
  }

  /** The previous() method currently serves no purpose but to print to the console. */
  public void previous() {
    System.out.println("Previous");
  }
}
