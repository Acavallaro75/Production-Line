package production;

import java.sql.Timestamp;
import java.util.Date;

/**
 * The ProductionRecord class is mainly used to record production on the Produce tab.
 *
 * @author: Andrew Cavallaro
 * @date: 11/30/2019
 */
class ProductionRecord {

  /** Field member "productionNumber" records the number of items that are going into production. */
  private final int productionNumber;

  /** Field member "productID" is the ID number of the product in the database. */
  private final int productID;

  /** Field member "serialNumber" is the serial number of the item going into the database. */
  private final String serialNumber;

  /**
   * Field member "dateProduced" records the date and time of production of an item or group of
   * items that are being stored into production.
   */
  private final Date dateProduced;

  /**
   * The overloaded ProductionRecord constructor. It takes the following parameters: "product",
   * "productionNumber", "productID", and "dateProduced". String padded is used to create the serial
   * number the way it is intended to be used.
   *
   * @param product must be of type Product
   * @param productionNumber number of products being entered into the database
   * @param productID the product's ID number from the database
   * @param dateProduced the date the product was produced
   */
  ProductionRecord(Product product, int productionNumber, int productID, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.dateProduced = dateProduced;
    String padded = String.format("%05d", product.getID());
    if (product.getManufacturer().length() < 3) {
      this.serialNumber =
          product.getManufacturer().substring(0, 2) + product.type.getCode() + padded;
    } else {
      this.serialNumber =
          product.getManufacturer().substring(0, 3) + product.type.getCode() + padded;
    }
  }

  /**
   * The toString() method prints the values of an object to the console. This is what it performs
   * here.
   *
   * @return returns a sentence with all the appropriate values for the proper attributes.
   */
  @Override
  public String toString() {
    return "Prod. Num: "
        + productionNumber
        + "\nProduct ID: "
        + productID
        + "\nSerial Num: "
        + serialNumber
        + "\nDate: "
        + dateProduced;
  }

  /**
   * The getProductionNumber() method returns the production number of the product(s) being created.
   *
   * @return productionNumber field member value
   */
  int getProductionNumber() {
    return productionNumber;
  }

  /**
   * The getSerialNumber() method returns the serial number of the product(s) being created.
   *
   * @return serialNumber field member value
   */
  String getSerialNumber() {
    return serialNumber;
  }

  /**
   * The getDateProduced() method returns the date of the product(s) being created in Date format.
   *
   * @param timestamp is required for the SQL statement
   * @return dateProduced field member value
   */
  Date getDateProduced(Timestamp timestamp) {
    return dateProduced;
  }
}
