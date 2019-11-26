package production;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * The Controller class implements most of the logic behind the JavaFX application. Analyze code is
 * showing a declaration redundancy error that the Controller class can be made package-private, but
 * when that choice is made the project fails to compile.
 *
 * @author Andrew Cavallaro
 * @date 11/26/2019
 */
public class Controller {

  /** The table view that displays all products in the database and their associated values. */
  @FXML private TableView<Product> tableViewProducts;

  /** The product name text field that is used to gather the name of the product. */
  @FXML private TextField productName;

  /** The product manufacturer text field that is used to gather the manufacturer of the product. */
  @FXML private TextField manufacturerName;

  /** The choice box that holds the item type values from the ItemType Enum class. */
  @FXML private ChoiceBox<ItemType> productType;

  /** The combo box that holds the quantity amounts and is used to record production. */
  @FXML private ComboBox<Integer> quantityBox;

  /** The text area that is used to display all of the recorded production of products. */
  @FXML private TextArea productLogView;

  /** The list view that displays all of the products in the database. */
  @FXML private ListView<Product> produceList;

  /** The column for ID numbers which is generated from the database. */
  @FXML private TableColumn<?, ?> idColumn;

  /** The column for item type which is generated from the database. */
  @FXML private TableColumn<?, ?> typeColumn;

  /** The column for manufacturer name which is generated from the database. */
  @FXML private TableColumn<?, ?> manufacturerColumn;

  /** The column for product name which is generated from the database. */
  @FXML private TableColumn<?, ?> nameColumn;

  /**
   * Field member "conn" is a Connection object that allows the program to connect to the database.
   */
  private static Connection conn;

  /**
   * Field member "statement" is a Statement object that allows for data to be retrieved from the
   * database.
   */
  private Statement statement;

  /**
   * Field member "preparedStatement" is a PreparedStatement object that allows for data to be
   * passed into a SQL statement and for data to be retrieved from the database.
   */
  private PreparedStatement preparedStatement;

  /**
   * Field member "resultSet" is ResultSet object that allows for data in the database to searched
   * over.
   */
  private ResultSet resultSet;

  /**
   * The iniatilizeDB() method is used to create a connection to the database. Field member "driver"
   * is used to establish what type of JDBC driver will be used. Field member "url" holds the
   * location of the database. Field member "user" holds the username to gain access to the
   * database. Field member "pass" holds the password to gain entry to the database. Since there is
   * no username, it is left blank. There will be bugs due to there being a hardcoded password. The
   * getConnection() method uses the "url", "user", and "pass" field members for the Connection
   * object.
   */
  static void initializeDB() {
    try {
      final String driver = "org.h2.Driver";
      final String url = "jdbc:h2:./production_resources/production";
      final String user = "";
      final String pass = "password";
      Class.forName(driver);
      conn = DriverManager.getConnection(url, user, pass);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * The ObservableList "productLine" will be used to accept new Product objects and the contents of
   * the ObservableList will be used to generate the table view and list view.
   */
  private ObservableList<Product> productLine;

  /**
   * The initialize() method calls the following methods: setupProductLineTable(),
   * loadProductList(), setupProductLineComboBoxes(), and showProduction(). Please see the
   * individual methods to learn more about what they do.
   *
   * @throws SQLException in case of a SQL error
   */
  public void initialize() throws SQLException {
    setupProductLineTable();
    loadProductList();
    setupProductLineComboBoxes();
    showProduction();
  }

  /**
   * The setupProductLineComboBoxes() method is used to set up the combo boxes with appropriate
   * values. Since the "quantity" combo box is being instantiated with numbers 1-10, a loop was used
   * to store the numbers in a List named "numbers", which was then used to populate the combo box.
   * The second List, named "productTypes", uses the ItemType Enum class to instantiate the values
   * to be stored in the combo box. An addAll() method was used to bring in all of the values from
   * the ItemType Enum class. Both of the combo boxes are set to display the first item in their
   * Observablelist.
   */
  private void setupProductLineComboBoxes() {
    List<Integer> numbers = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      numbers.add(i);
    }
    ObservableList numberList = FXCollections.observableList(numbers);
    quantityBox.getItems().clear();
    quantityBox.setItems(numberList);
    quantityBox.getSelectionModel().selectFirst();
    List<ItemType> productTypes = new ArrayList<>(Arrays.asList(ItemType.values()));
    ObservableList typesList = FXCollections.observableList(productTypes);
    productType.getItems().clear();
    productType.setItems(typesList);
    productType.getSelectionModel().selectFirst();
  }

  /**
   * The setupProductionLineTable() method is used to set up the table view with the appropriate
   * values from the "productLine" ObservableList which is populated from the database.
   */
  private void setupProductLineTable() {
    productLine = FXCollections.observableArrayList();
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
    tableViewProducts.setItems(productLine);
  }

  /**
   * The loadProductList() method is used to set up the list view and uses the ObservabltList
   * "productLine" to generate values to display. The SQL statement searches the database and
   * returns all the products that will populate the "productLine" ObservableList. If-else
   * statements are used to differentiate the item types of the products from the database. Lastly,
   * it calls the loadProductionLog() method.
   *
   * @throws SQLException in case of a SQL error
   */
  private void loadProductList() throws SQLException {
    produceList.setItems(productLine);
    String query = "SELECT * FROM PRODUCT";
    statement = conn.createStatement();
    resultSet = statement.executeQuery(query);
    while (resultSet.next()) {
      if (resultSet.getString("Type").equalsIgnoreCase("AUDIO")) {
        productLine.add(
            new Product(
                resultSet.getInt("ID"),
                resultSet.getString("Name"),
                resultSet.getString("Manufacturer"),
                ItemType.AUDIO));
        produceList.setItems(productLine);
      } else if (resultSet.getString("Type").equalsIgnoreCase("VISUAL")) {
        productLine.add(
            new Product(
                resultSet.getInt("ID"),
                resultSet.getString("Name"),
                resultSet.getString("Manufacturer"),
                ItemType.VISUAL));
        produceList.setItems(productLine);
      } else if (resultSet.getString("Type").equalsIgnoreCase("AUDIO_MOBILE")) {
        productLine.add(
            new Product(
                resultSet.getInt("ID"),
                resultSet.getString("Name"),
                resultSet.getString("Manufacturer"),
                ItemType.AUDIO_MOBILE));
        produceList.setItems(productLine);
      } else if (resultSet.getString("Type").equalsIgnoreCase("VISUAL_MOBILE")) {
        productLine.add(
            new Product(
                resultSet.getInt("ID"),
                resultSet.getString("Name"),
                resultSet.getString("Manufacturer"),
                ItemType.VISUAL_MOBILE));
        produceList.setItems(productLine);
      }
    }
    statement.close();
  }

  /**
   * The addProduct() method is used to enter products into the database. A PreparedStatement is
   * made that will be populated using the "productType" choice box, "manufacturerName" text field,
   * and "productName" text field. The populated SQL statement will then be passed in a
   * PreparedStatement and used to execute an update to the database. If-else statements are used to
   * differentiate between item types and allow them to be passed to the database. An error message
   * is displayed to the user if either of the name or manufacturer text fields are left blank. This
   * avoids accidental input of blank items into the database. Lastly, it calls the
   * setupProductLineTable() and loadProductList() methods.
   */
  @FXML
  private void addProduct() {
    try {
      if (productName.getText().equalsIgnoreCase("")
          || manufacturerName.getText().equalsIgnoreCase("")) {
        Alert error = new Alert(AlertType.ERROR);
        error.setHeaderText("Error");
        error.setContentText("Please fill out all fields before entering.");
        error.show();
      } else {
        String sql = "INSERT INTO Product (TYPE, MANUFACTURER, NAME) VALUES (?, ?, ?)";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(
            1, productType.getSelectionModel().getSelectedItem().toString());
        preparedStatement.setString(2, manufacturerName.getText());
        preparedStatement.setString(3, productName.getText());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        String name = productName.getText();
        String manufacturer = manufacturerName.getText();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        if (productType
            .getSelectionModel()
            .getSelectedItem()
            .toString()
            .equalsIgnoreCase("AUDIO")) {
          productLine.add(new Product(name, manufacturer, ItemType.AUDIO));
        } else if (productType
            .getSelectionModel()
            .getSelectedItem()
            .toString()
            .equalsIgnoreCase("VISUAL")) {
          productLine.add(new Product(name, manufacturer, ItemType.VISUAL));
        } else if (productType
            .getSelectionModel()
            .getSelectedItem()
            .toString()
            .equalsIgnoreCase("AUDIO_MOBILE")) {
          productLine.add(new Product(name, manufacturer, ItemType.AUDIO_MOBILE));
        } else if (productType
            .getSelectionModel()
            .getSelectedItem()
            .toString()
            .equalsIgnoreCase("VISUAL_MOBILE")) {
          productLine.add(new Product(name, manufacturer, ItemType.VISUAL_MOBILE));
        }
        setupProductLineTable();
        loadProductList();
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * The recordProduction() method is used to gather ProductionRecord objects. An ArrayList named
   * "productionRun" is used to store the ProductionRecord objects that can be used to populate the
   * "productLogView" text area. An alert is shown if no item in the list view is chosen. Lastly, it
   * calls the addToProductionDB(), loadProductionLog(), and showProduction() methods.
   */
  @FXML
  void recordProduction() {
    Date date = new Date();
    if (produceList.getSelectionModel().getSelectedItem() == null) {
      Alert error = new Alert(AlertType.ERROR);
      error.setHeaderText("Error");
      error.setContentText("Please choose a product to add to the production log.");
      error.show();
    } else {
      Alert confirmation = new Alert(AlertType.CONFIRMATION);
      confirmation.setContentText(
          "Confirm production of: "
              + quantityBox.getSelectionModel().getSelectedItem().toString()
              + " "
              + produceList.getSelectionModel().getSelectedItem().getManufacturer()
              + " "
              + produceList.getSelectionModel().getSelectedItem().getName()
              + "?");
      Optional<ButtonType> result = confirmation.showAndWait();
      ButtonType button = result.orElse(ButtonType.CANCEL);
      if (button == ButtonType.OK) {
        ArrayList<ProductionRecord> productionRun = new ArrayList<>();
        productionRun.add(
            new ProductionRecord(
                produceList.getSelectionModel().getSelectedItem(),
                quantityBox.getSelectionModel().getSelectedItem(),
                produceList.getSelectionModel().getSelectedItem().getID(),
                new Timestamp(date.getTime())));
        addToProductionDB(productionRun);
        showProduction();
      } else {
        Alert cancel = new Alert(AlertType.INFORMATION);
        cancel.setHeaderText("Cancel");
        cancel.setContentText(
            "Canceling production of "
                + quantityBox.getSelectionModel().getSelectedItem().toString()
                + " "
                + produceList.getSelectionModel().getSelectedItem().getManufacturer()
                + " "
                + produceList.getSelectionModel().getSelectedItem().getName());
        cancel.show();
      }
    }
  }

  /**
   * The showProduction() method shows all the production of items in the database. It is called
   * whenever an item is put into the database and loaded upon initial run in the initialize()
   * method.
   */
  private void showProduction() {
    productLogView.clear();
    try {
      String sql = "SELECT * FROM PRODUCTIONRECORD";
      statement = conn.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        productLogView.appendText(
            "Product Name: "
                + resultSet.getString("NAME")
                + "\nProduction Number: "
                + resultSet.getString("PRODUCTION_NUMBER")
                + "\nSerial Number: "
                + resultSet.getString("SERIAL_NUMBER")
                + "\nDate Produced: "
                + resultSet.getTimestamp("DATE_PRODUCED")
                + "\n--------------------------------------------------------------------\n");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * The addToProductionDB() method is used to enter product production into the database. The
   * ArrayList "productionRun" is made into an array of ProductionRecord objects in order to obtain
   * the methods associated with the ProductionRecord class. A PreparedStatement is made that will
   * be populated using the ProductRecord objects and their associated methods. The information is
   * then entered into the database. Lastly, it calls the loadProductionLog() method.
   *
   * @param productionRun requires an ArrayList of ProductionRecord objects
   */
  private void addToProductionDB(ArrayList<ProductionRecord> productionRun) {
    ProductionRecord[] productionRecord = productionRun.toArray(new ProductionRecord[0]);
    Product product =
        new Product(
            produceList.getSelectionModel().getSelectedItem().getID(),
            produceList.getSelectionModel().getSelectedItem().getName(),
            produceList.getSelectionModel().getSelectedItem().getManufacturer(),
            produceList.getSelectionModel().getSelectedItem().getType());
    Date date = new Date();
    try {
      String sql =
          "INSERT INTO PRODUCTIONRECORD (PRODUCTION_NUMBER, PRODUCT_ID, SERIAL_NUMBER, "
              + "DATE_PRODUCED, NAME) VALUES (?, ?, ?, ?, ?)";
      preparedStatement = conn.prepareStatement(sql);
      preparedStatement.setInt(1, productionRecord[0].getProductionNumber());
      preparedStatement.setInt(2, product.getID());
      preparedStatement.setString(3, productionRecord[0].getSerialNumber());
      preparedStatement.setTimestamp(
          4, (Timestamp) productionRecord[0].getDateProduced(new Timestamp(date.getTime())));
      preparedStatement.setString(5, product.getName());
      preparedStatement.executeUpdate();
      preparedStatement.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * The productionLog() method is used to show the user the updated production log of products from
   * the database.
   */
  @FXML
  void productionLog() {
    showProduction();
    Alert confirmation = new Alert(AlertType.CONFIRMATION);
    confirmation.setContentText("All production items have been successfully updated");
    confirmation.show();
  }
}
