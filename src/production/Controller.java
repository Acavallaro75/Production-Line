package production;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * The Controller class that implements most of the logic behind the JavaFX application. Analyze
 * code is showing a declaration redundancy error that the Controller class can be package-private,
 * but when that choice is made the project fails to compile.
 *
 * @author Andrew Cavallaro
 * @date 09/27/2019
 */
public class Controller {

  /**
   * Field members of JDBC_DRIVER, DB_URL, USER, and PASS are instantiated for future use. A
   * Connection Object is created as well as a PreparedStatement Object that will be used to
   * connecting and communicating with the database. JDBC_DRIVER is the H2 driver that will be used
   * for the database in this project. The DB_URL is the location of the database and what type of
   * driver that will be implemented. The USER and PASS fields are the username and password to gain
   * access to the database. Currently there are no restrictions on the database, hence the "" and
   * "". There will be bugs due to there being limited security on the database.
   */
  private static final String JDBC_DRIVER = "org.h2.Driver";

  private static final String DB_URL = "jdbc:h2:./production_resources/production";
  private static final String USER = "";
  private static final String PASS = "";
  private static Connection conn;
  private static PreparedStatement pstmt;

  /**
   * The iniatilizeDB() method is used to create a connection to the H2 database. The getConnection
   * uses the DB_URL, USER, and PASS fields from above for the Connection Object.
   */
  private void initializeDB() {
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Added below are three (3) buttons: adding a product in the Product Line tab, record production
   * in the Produce tab, and a button in the Production Log tab. Also added are two (2) text fields
   * in the Product Line tab that are used for recording user input for a product name and
   * manufacturer name. There are four (4) table columns added for populating the also added table
   * view with items from the database on the Product Line tab. Additionally, there is a choice box
   * added to the Product Line tab used for making choices on an added product. Finally, a combo box
   * is added to the Produce tab that allows a user to make a selection.
   */
  @FXML private Button addProduct;

  @FXML private TextField productName;

  @FXML private TextField manufacturerName;

  @FXML private ChoiceBox<?> productType;

  @FXML private TableView<?> viewProducts;

  @FXML private TableColumn<?, ?> columnID;

  @FXML private TableColumn<?, ?> columnType;

  @FXML private TableColumn<?, ?> columnManufacturer;

  @FXML private TableColumn<?, ?> columnName;

  @FXML private Button recordProduction;

  @FXML private ComboBox<?> quantityBox;

  @FXML private Button productionLogButton;

  /**
   * The initialize method is used to instantiate the options for the combo box and choice box,
   * respectively. Since the combo box is being instantiated with numbers 1-10, a loop was used to
   * store the numbers. Easy replacement of the number 10 would generate more or fewer numbers if
   * desired. The same is said for instantiating the choice box. Hard code was used during this to
   * populate the choice box. Both the choice box and combo box are set to select the first item in
   * their observable list and display upon first run of the program. The combo box has the option
   * and ability to edit the value.
   */
  public void initialize() {
    List<Integer> numbers = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      numbers.add(i);
    }
    ObservableList numberList = FXCollections.observableList(numbers);
    quantityBox.getItems().clear();
    quantityBox.setItems(numberList);
    quantityBox.getSelectionModel().selectFirst();
    quantityBox.setEditable(true);
    List<ItemType> productTypes = new ArrayList<>(Arrays.asList(ItemType.values()));
    ObservableList typesList = FXCollections.observableList(productTypes);
    productType.getItems().clear();
    productType.setItems(typesList);
    productType.getSelectionModel().selectFirst();
  }

  /**
   * This addProductButton() method is used to create an action event upon the clicking of the add
   * product button. Once clicked, the initializeDB() method will be called creating a conncetion to
   * the database. A string SQL Statement is made using the ? placeholders that will be populated
   * using the choice box, manufacture name text field, and product name text field. The SQL
   * Statement will be populated and passed in a PreparedStatement and then used to execute an
   * update. The entered information and selection will then be stored into the database. The button
   * also creates a print line statement to the console to show that it has been pressed. Current
   * issue: need to press the Add Product button twice initially before the Add Product button
   * works. Looking for solutions proactively.
   */
  @FXML
  void addProductButton() {
    addProduct.setOnAction(
        e -> {
          try {
            initializeDB();
            String sql = "INSERT INTO Product (TYPE, MANUFACTURER, NAME) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productType.getSelectionModel().getSelectedItem().toString());
            pstmt.setString(2, manufacturerName.getText());
            pstmt.setString(3, productName.getText());
            pstmt.executeUpdate();
          } catch (SQLException ex) {
            ex.printStackTrace();
          }
          System.out.println("Add Product button was pressed");
        });
  }

  /**
   * This is a simple method for producing a print line to the console when the Record Production
   * button is pressed on the Produce tab.
   */
  @FXML
  void recordProductionButton() {
    System.out.println("Button 2 pressed");
  }

  /**
   * This is a simple method for producing a print line to the console when the Production Log
   * button is pressed on the Production Log tab.
   */
  @FXML
  void productionLogButton() {
    System.out.println("Button 3 pressed");
  }
}
