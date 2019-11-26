package production;

import java.sql.Timestamp;
import java.util.Date;

/**
 * The ProductionRecord class is mainly used to record production on the Produce tab.
 *
 * @author: Andrew Cavallaro
 * @date: 11/05/2019
 */
class ProductionRecord {

  /** Field member productionNumber records the number of items that are going into production. */
  private int productionNumber;

  /** Field member productID is the ID number of the product in the H2 Database. */
  private int productID;

  /** Field member serialNumber is the serial number of the item going into the H2 Database. */
  private String serialNumber;

  /**
   * Field member dateProduced records the date and time of production of an item or group of items
   * that are being stored into production.
   */
  private Date dateProduced;

  /**
   * The ProductionRecord() overloaded constructor. It takes the following parameters: Product and
   * numberOfItems.
   *
   * @param product requires a type of Product to be passed
   */
  ProductionRecord(Product product, int productionNumber, int productID, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.dateProduced = dateProduced;
    String padded = String.format("%05d", product.getID());
    this.serialNumber = product.getManufacturer().substring(0, 3) + product.type.getCode() + padded;
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
   * The setProductionNumber is used to set the production number of the item(s) being created.
   *
   * @param productionNumber requires a production number
   */
  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  /**
   * The getProductionNumber() method returns the production number of the item(s) being created.
   *
   * @return productionNumber field member value
   */
  int getProductionNumber() {
    return productionNumber;
  }

  /**
   * The setProductID() method sets the product ID in the H2 Database.
   *
   * @param productID requires the parameter for the Product ID
   */
  public void setProductID(int productID) {
    this.productID = productID;
  }

  /**
   * The getProductID() method returns the product ID of the item(s) being created.
   *
   * @return productID field member value
   */
  int getProductID() {
    return productID;
  }

  /**
   * The setSerialNumber() method sets the product's serial number in the H2 Database.
   *
   * @param serialNumber requires a serial number to be passed
   */
  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  /**
   * The getSerialNumber() method returns the serial number of the item(s) being created.
   *
   * @return serialNumber field member value
   */
  String getSerialNumber() {
    return serialNumber;
  }

  /**
   * The setDateProduced() method sets the date produced value for the item(s) being created.
   *
   * @param dateProduced requires a Date Object to be passed
   */
  public void setDateProduced(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  /**
   * The getDateProduced() method returns the date of the item(s) being created in Date format.
   *
   * @return dateProduced field member value
   * @param timestamp
   */
  Date getDateProduced(Timestamp timestamp) {
    return dateProduced;
  }
}
