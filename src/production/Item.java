package production;

/**
 * The Item interface is used to store the methods that must be used in the abstract Product class.
 * These methods can be used elsewhere if needed also.
 *
 * @author: Andrew Cavallaro
 * @date: 10/28/2019
 */
interface Item {

  /**
   * The getID() method is used for obtaining the ID number from the H2 Database.
   *
   * @return returns the ID number.
   */
  int getID();

  /**
   * The setName() method is used for setting the name of the item in the H2 Database.
   *
   * @param name is the name of the product being passed into the H2 Database.
   */
  void setName(String name);

  /**
   * The getName() method is used for obtaining the name of the product from the H2 Database.
   *
   * @return returns the name of the product.
   */
  String getName();

  /**
   * The setManufacturer() method is used for setting the manufacturer of the item in the H2
   * Database.
   *
   * @param manufacturer is the manufacturer of the product being passed into the H2 Database.
   */
  void setManufacturer(String manufacturer);

  /**
   * The getManufacturer() method is used for obtaining the manufacturer of the product from the H2
   * Database.
   *
   * @return returns the manufacturer of the product.
   */
  String getManufacturer();
}
