package production;

public abstract class Product implements Item {
  private int ID;
  private String type;
  private String manufacturer;
  private String name;

  Product(String name, String manufacturer, String type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
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

  public String toString() {
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
  }
}

class Widget extends Product {

  Widget(String name, String manufacturer, String type) {
    super(name, manufacturer, type);
  }
}
