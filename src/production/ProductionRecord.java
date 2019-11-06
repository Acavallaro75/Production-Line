package production;

import java.util.Date;

/**
 * The ProductionRecord class is mainly used to record production on the Produce tab.
 *
 * @author: Andrew Cavallaro
 * @date: 10/29/2019
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
   * The ProductionRecord() overloaded constructor
   *
   * @param product requires a type of Product to be passed
   * @param numberOfItems is selected from the combo box on the Produce tab
   */
  ProductionRecord(Product product, int numberOfItems) {
    String padded = String.format("%05d", numberOfItems);
    serialNumber = product.getManufacturer().substring(0, 3) + product.type.getCode() + padded;
  }

  /**
   * @param productionNumber
   * @param productID
   * @param serialNumber
   * @param dateProduced
   */
  public ProductionRecord(
      int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  /** @return */
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

  /** @param productionNumber */
  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  /** @return */
  public int getProductionNumber() {
    return productionNumber;
  }

  /** @param productID */
  public void setProductID(int productID) {
    this.productID = productID;
  }

  /** @return */
  public int getProductID() {
    return productID;
  }

  /** @param serialNumber */
  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  /** @return */
  public String getSerialNumber() {
    return serialNumber;
  }

  /** @param dateProduced */
  public void setDateProduced(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  /** @return */
  public Date getDateProduced() {
    return dateProduced;
  }
}
