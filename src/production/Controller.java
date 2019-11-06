package production;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
 * showing a declaration redundancy error that the Controller class can be package-private, but when
 * that choice is made the project fails to compile.
 *
 * @author Andrew Cavallaro
 * @date 11/05/2019
 */
public class Controller {

  /**
   * Field member JDBC_DRIVER is used to establish what type of JDBC Driver will be used. We are
   * using an H2 Driver for this project.
   */
  private static final String JDBC_DRIVER = "org.h2.Driver";

  /**
   * Field member DB_URL holds the location of the H2 Database that will be implemented in this
   * project.
   */
  private static final String DB_URL = "jdbc:h2:./production_resources/production";

  /**
   * Field member USER holds the user name to gain access to the H2 Database. Since there is no user
   * name currently, it's left blank.
   */
  private static final String USER = "";

  /**
   * Field member PASS holds the password to gain entry to the H2 Database. Since there is no
   * password currently, it's left blank. There will be bugs due to there being limited security.
   */
  private static final String PASS = "";

  /**
   * Field member conn is a Connection object that allows the program to connect to the H2 Database.
   */
  private static Connection conn;

  /**
   * The iniatilizeDB() method is used to create a connection to the H2 Database. The getConnection
   * method uses the DB_URL, USER, and PASS fields from above for the Connection object.
   */
  void initializeDB() {
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** The table view in the Production Line tab. */
  @FXML private TableView<Product> tableViewProducts;

  /** The product name text field in the Production Line tab. */
  @FXML private TextField productName;

  /** The manufacturer name text field in the Production Line tab. */
  @FXML private TextField manufacturerName;

  /** The choice box that holds the product types in the Production Line tab. */
  @FXML private ChoiceBox<ItemType> productType;

  /** The combo box that holds the quantity amounts in the Produce tab. */
  @FXML private ComboBox<?> quantityBox;

  /** The text area in the Production Log tab. */
  @FXML private TextArea productLogView;

  /** The list view in the Produce tab. */
  @FXML private ListView<Product> produceList;

  /** The column for ID numbers in the Production Line tab. */
  @FXML private TableColumn<?, ?> idColumn;

  /** The column for item type in the Production Line tab. */
  @FXML private TableColumn<?, ?> typeColumn;

  /** The column for manufacturer name in the Production Line tab. */
  @FXML private TableColumn<?, ?> manufacturerColumn;

  /** The column for product name in the Production Line tab. */
  @FXML private TableColumn<?, ?> nameColumn;

  /** ObservableList will be used to accept new product objects on the Production Line tab. */
  private ObservableList<Product> productLine;

  /**
   * The initialize() method is used to instantiate the options for the combo box and choice box,
   * respectively. It also pulls from the H2 Database to populate the list view on the Produce tab.
   * Since the combo box is being instantiated with numbers 1-10, a loop was used to store the
   * numbers. Easy replacement of the number 10 would generate more or less numbers if desired. The
   * second list uses the ItemType enum to instantiate the values to be stored in the choicebox. An
   * addAll() method was used to bring in all the values from the enum. Both the choice box and
   * combo box are set to select the first item in their observable list and display upon first run
   * of the program.
   */
  public void initialize() throws SQLException {
    productLine = FXCollections.observableArrayList();
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    tableViewProducts.setItems(productLine);
    productLine.add(new Product("iPod", "Apple", ItemType.AUDIO));
    produceList.setItems(productLine);
    String query = "SELECT * FROM PRODUCT";
    Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(query);
    while (resultSet.next()) {
      if (resultSet.getString("Type").equalsIgnoreCase("AUDIO")) {
        productLine.add(
            new Product(
                resultSet.getString("Name"), resultSet.getString("Manufacturer"), ItemType.AUDIO));
        produceList.setItems(productLine);
      } else if (resultSet.getString("Type").equalsIgnoreCase("VISUAL")) {
        productLine.add(
            new Product(
                resultSet.getString("Name"), resultSet.getString("Manufacturer"), ItemType.VISUAL));
        produceList.setItems(productLine);
      } else if (resultSet.getString("Type").equalsIgnoreCase("AUDIO_MOBILE")) {
        productLine.add(
            new Product(
                resultSet.getString("Name"),
                resultSet.getString("Manufacturer"),
                ItemType.AUDIO_MOBILE));
        produceList.setItems(productLine);
      } else if (resultSet.getString("Type").equalsIgnoreCase("VISUAL_MOBILE")) {
        productLine.add(
            new Product(
                resultSet.getString("Name"),
                resultSet.getString("Manufacturer"),
                ItemType.VISUAL_MOBILE));
        produceList.setItems(productLine);
      }
    }
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
    statement.close();
  }

  /**
   * The addProduct() method is used to initialize and enter items into the H2 Database. A prepared
   * SQL statement is made that will be populated using the productType choice box, manufacturerName
   * text field, and productName text field. The SQL statement will be populated and passed in a
   * PreparedStatement and then used to execute an update. The entered information and selection
   * will then be stored into the H2 Database. An error message is displayed to the user if either
   * of the name or manufacturer text fields are left blank. This avoids accidental input of blank
   * items into the H2 Database.
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
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, productType.getSelectionModel().getSelectedItem().toString());
        pstmt.setString(2, manufacturerName.getText());
        pstmt.setString(3, productName.getText());
        pstmt.executeUpdate();
        pstmt.close();
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
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * The recordProduction() function is used to get the product from the H2 Database and make
   * production logs of that product. String data is used to get the combo box value as a String and
   * then converted into an integer named record. An alert is shown if no item in the list view is
   * chosen. Once an item in the list view is chosen and a quantity is selected, the user will push
   * the Record Production button to submit. On the Production Log tab, the user will see the items
   * they just produced, how many, and when the production was done.
   */
  @FXML
  void recordProduction() {
    String data = quantityBox.getValue().toString();
    int record = Integer.parseInt(data);
    if (produceList.getSelectionModel().getSelectedItem() == null) {
      Alert error = new Alert(AlertType.ERROR);
      error.setHeaderText("Error");
      error.setContentText("Please choose a product to add to the production log.");
      error.show();
    } else {
      productLogView.appendText(
          produceList.getSelectionModel().getSelectedItem()
              + "\nQuantity: "
              + record
              + "\nDate: "
              + new Date()
              + "\n");
    }
  }

  /**
   * The productionLog() method is the main() method used from issue 5 to show that it is indeed
   * working on this project.
   */
  @FXML
  void productionLog() {
    Product productProduced = new Widget("iPod", "Apple", ItemType.AUDIO);
    String quantityChosen = quantityBox.getValue().toString();
    int record = Integer.parseInt(quantityChosen);
    int itemCount = 0;
    for (int i = 0; i < record; i++) {
      ProductionRecord addToProduction = new ProductionRecord(productProduced, itemCount);
      itemCount = itemCount + 1;
      System.out.println(addToProduction.toString());
    }
  }
}
