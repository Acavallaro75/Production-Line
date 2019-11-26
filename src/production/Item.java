package production;

/**
 * The Item interface is used to store the methods that must be used in the Product class. These
 * methods can be used elsewhere if needed also.
 *
 * @author: Andrew Cavallaro
 * @date: 11/26/2019
 */
interface Item {

  /**
   * The getID() method is used for obtaining the product's "ID" from the database.
   *
   * @return returns the "ID" of the product
   */
  int getID();

  /**
   * The setName() method is used for setting the "name" of the product in the database.
   *
   * @param name is the "name" of the product being passed into the database
   */
  void setName(String name);

  /**
   * The getName() method is used for obtaining the "name" of the product from the database.
   *
   * @return returns the "name" of the product
   */
  String getName();

  /**
   * The setManufacturer() method is used for setting the "manufacturer" of the product in the
   * database.
   *
   * @param manufacturer is the "manufacturer" of the product being passed into the database
   */
  void setManufacturer(String manufacturer);

  /**
   * The getManufacturer() method is used for obtaining the "manufacturer" of the product from the
   * database.
   *
   * @return returns the "manufacturer" of the product
   */
  String getManufacturer();
}
