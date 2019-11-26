package production;

/**
 * The abstract class Product is used to implement the Item interface. It inherits all of its
 * methods in the Item interface.
 *
 * @author: Andrew Cavallaro
 * @date: 11/05/2019
 */
public class Product implements Item {

  /** Field member id is the ID number for the products in the H2 Database. */
  private int id;

  /**
   * Field member type is the type of product that is in the H2 Database. The enum ItemType will
   * establish this value.
   */
  final ItemType type;

  /** Field member manufacturer is the manufacturer of the product in the H2 Database. */
  private String manufacturer;

  /** Field member name is the name of the product in the H2 Database. */
  private String name;

  public ItemType getType() {
    return type;
  }

  /**
   * The Product constructor takes the following parameters: name, manufacturer, and type. The
   * passed arguments will be set to the field members name, manufacturer, and type. The itemType
   * argument must be of Object ItemType to be passed.
   *
   * @param name is the name of the item being passed into the H2 Database.
   * @param manufacturer is the manufacturer of the item being passed into the H2 Database.
   * @param type is the type of item being passed into the H2 Database.
   */
  Product(int id, String name, String manufacturer, ItemType type) {
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  Product(ItemType type) {
    this.type = type;
  }

  Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
    this.id = getID();
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
