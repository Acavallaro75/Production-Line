package production;

/**
 * The class Product is used to implement the Item interface. It inherits all of its methods in the
 * Item interface.
 *
 * @author: Andrew Cavallaro
 * @date: 11/26/2019
 */
public class Product implements Item {

  /** Field member "id" is the ID number for the products in the database. */
  private int id;

  /**
   * Field member "type" is the type of product that is in the database. The enum ItemType will
   * establish this value.
   */
  final ItemType type;

  /** Field member "manufacturer" is the manufacturer of the product in the database. */
  private String manufacturer;

  /** Field member "name" is the name of the product in the database. */
  private String name;

  /**
   * The getType() method gets the product's ItemType from the database. I can not make this
   * package-private as the message keeps telling me as the function will not return the ItemType if
   * I do so.
   *
   * @return the product's ItemType
   */
  public ItemType getType() {
    return type;
  }

  /**
   * The Product constructor takes the following parameters: "id", "name", "manufacturer", and
   * "type". The passed arguments will be set to the field members "id", "name", "manufacturer", and
   * "type". The itemType argument must be of Object ItemType to be passed. Used for the
   * addProduct() method.
   *
   * @param id is the id of the item being passed into the database.
   * @param name is the name of the item being passed into the database.
   * @param manufacturer is the manufacturer of the item being passed into the database.
   * @param type is the type of item being passed into the database.
   */
  Product(int id, String name, String manufacturer, ItemType type) {
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * The Product constructor takes the following parameters: "name", "manufacturer", and "type". The
   * passed arguments will be set to the field members "name", "manufacturer", and "type". The
   * itemType argument must be of Object ItemType to be passed. Used for the addToProductionDB()
   * method.
   *
   * @param name is the name of the item being passed into the database.
   * @param manufacturer is the manufacturer of the item being passed into the database.
   * @param type is the type of item being passed into the database.
   */
  Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
    this.id = getID();
  }

  /**
   * The getID() method gets the ID number of the product in the database.
   *
   * @return returns the id number of the product from the database.
   */
  public int getID() {
    return id;
  }

  /**
   * The getID() method gets the name of the product in the database.
   *
   * @return returns the product name of the product from the database.
   */
  public String getName() {
    return name;
  }

  /**
   * The getID() method gets the manufacturer of the item in the database.
   *
   * @return returns the manufacturer of the product from the database.
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * The toString() method prints the values of an object to the console. This is what it performs
   * here.
   *
   * @return returns a sentence with all the appropriate values for the proper attributes.
   */
  public String toString() {
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
  }
}
