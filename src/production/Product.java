package production;

/**
 * The abstract class Product is used to implement the Item interface. It inherits all of its
 * methods in the Item interface.
 *
 * @author: Andrew Cavallaro
 * @date: 10/07/2019
 */
public abstract class Product implements Item {

  /** Field member id is the ID number for the products in the H2 Database. */
  private int id;

  /**
   * Field member type is the type of product that is in the H2 Database. The enum ItemType will
   * establish this value.
   */
  String type;

  /** Field member manufacturer is the manufacturer of the product in the H2 Database. */
  private String manufacturer;

  /** Field member name is the name of the product in the H2 Database. */
  private String name;

  /**
   * The Product constructor takes the following three parameters:
   *
   * @param name is the name of the item being passed into the H2 Database.
   * @param manufacturer is the manufacturer of the item being passed into the H2 Database.
   * @param type is the type of item being passed into the H2 Database.
   */
  Product(String name, String manufacturer, String type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * The getID() method gets the ID number of the item in the H2 Database.
   *
   * @return returns the id number from the H2 Database.
   */
  public int getID() {
    return id;
  }

  /**
   * The setName() method will set the name of the item in the H2 Database.
   *
   * @param name is the name of the product being passed into the H2 Database.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * The getID() method gets the name of the item in the H2 Database.
   *
   * @return returns the item name from the H2 Database.
   */
  public String getName() {
    return name;
  }

  /**
   * The setManufacturer() method will set the name of the item in the H2 Database.
   *
   * @param manufacturer is the manufacturer of the product being passed into the H2 Database.
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * The getID() method gets the manufacturer of the item in the H2 Database.
   *
   * @return returns the manufacturer from the H2 Database.
   */
  public String getManufacturer() {
    return manufacturer;
  }

  public String toString() {
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
  }
}

/**
 * The class Widget is here primarily for testing purposes as of now. Widget is a child of Product
 * and inherits all of its fields and methods. There will be an error because Widget is not in a
 * file on its own as normal.
 */
class Widget extends Product {
  Widget(String name, String manufacturer, String type) {
    super(name, manufacturer, type);
  }
}
