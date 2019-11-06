package production;

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
  private Date dateProduced = new Date();

  /**
   * The ProductionRecord() default constructor that is used to record production on the Produce
   * tab.
   *
   * @param productID requires a product ID
   */
  public ProductionRecord(int productID) {
    this.productID = productID;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
  }

  /**
   * The ProductionRecord() overloaded constructor. It takes the following parameters: Product and
   * numberOfItems.
   *
   * @param product requires a type of Product to be passed
   * @param numberOfItems is selected from the combo box on the Produce tab
   */
  ProductionRecord(Product product, int numberOfItems) {
    String padded = String.format("%05d", numberOfItems);
    serialNumber = product.getManufacturer().substring(0, 3) + product.type.getCode() + padded;
  }

  /**
   * The constructor for the ProductionRecord class. It takes the following parameters:
   * productionNumber, productID, serialNumber, and dateProduced.
   *
   * @param productionNumber requires a production number value
   * @param productID requires a product ID value
   * @param serialNumber requires a serial number value
   * @param dateProduced requires a date object to be passed
   */
  public ProductionRecord(
      int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
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
        + " Product ID: "
        + productID
        + " Serial Num: "
        + serialNumber
        + " Date: "
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
  public int getProductionNumber() {
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
  public int getProductID() {
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
  public String getSerialNumber() {
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
   */
  public Date getDateProduced() {
    return dateProduced;
  }
}
