package production;

/**
 * The Enum ItemType is used to hold all of the values for the choice box and their associated
 * "codes".
 *
 * @author: Andrew Cavallaro
 * @date: 11/26/2019
 */
public enum ItemType {
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");

  /** Field member "code" is used to establish the "code" for the ItemType. */
  private final String code;

  /**
   * This is the constructor for the enum ItemType. It takes one parameter for the product "code"
   * and sets it equal to the String "code" field member.
   *
   * @param code is used to establish what the "code" is for the product
   */
  ItemType(String code) {
    this.code = code;
  }

  /**
   * The getCode() method is used to return the "code" for the ItemType.
   *
   * @return the "code" for the ItemType
   */
  String getCode() {
    return this.code;
  }
}
