package production;

public abstract class Product implements Item {
  private int ID;
  String type;
  private String manufacturer;
  private String name;

  public Product(String name) {
    this.name = name;
  }

  public int getID() {
    return ID;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public String  toString() {
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
  }
}
