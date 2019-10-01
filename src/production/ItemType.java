package production;

public enum ItemType {
  Audio("AU"),
  Visual("VI"),
  AudioMobile("AM"),
  VisualMobile("VM");

  String code;

  ItemType(String code) {
    this.code = code;
  }
}
