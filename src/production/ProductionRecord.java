package production;

import java.util.Date;

/** */
class ProductionRecord {

  /** */
  private int productionNumber;

  /** */
  private int productID;

  /** */
  private String serialNumber;

  /** */
  private Date dateProduced = new Date();

  /** @param productID */
  public ProductionRecord(int productID) {
    this.productID = productID;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
  }

  /**
   * @param product
   * @param numberOfItems
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
