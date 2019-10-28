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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The Controller class implements most of the logic behind the JavaFX application. Analyze code is
 * showing a declaration redundancy error that the Controller class can be package-private, but when
 * that choice is made the project fails to compile.
 *
 * @author Andrew Cavallaro
 * @date 10/28/2019
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

  /** The product name text field in the Production Line tab. */
  @FXML private TextField productName;

  /** The manufacturer name text field in the Production Line tab. */
  @FXML private TextField manufacturerName;

  /** The choice box that holds the product types in the Production Line tab. */
  @FXML private ChoiceBox<?> productType;

  /** The combo box that holds the quantity amounts in the Produce tab. */
  @FXML private ComboBox<?> quantityBox;

  /** The text area in the Production Log tab. */
  @FXML private TextArea productLogView;

  /** The list view in the Produce tab. */
  @FXML private ListView<String> produceList;

  /**
   * The initialize() method is used to instantiate the options for the combo box and choice box,
   * respectively. It also pulls from the H2 Database to populate the list view on the Produce tab.
   * Since the combo box is being instantiated with numbers 1-10, a loop was used to store the
   * numbers. Easy replacement of the number 10 would generate more or less numbers if desired. The
   * second list uses the ItemType enum to instantiate the values to be stored in the choicebox. An
   * addAll() method was used to bring in all the values from the enum. Both the choice box and
   * combo box are set to select the first item in their observable list and display upon first run
   * of the program. The combo box has the option and ability to edit the value.
   */
  public void initialize() throws SQLException {
    ObservableList<String> items = FXCollections.observableArrayList();
    produceList.setItems(items);
    String query = "SELECT * FROM PRODUCT WHERE NAME != ''";
    Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(query);
    while (resultSet.next()) {
      items.addAll(
          "Manufacturer: "
              + resultSet.getString("Manufacturer")
              + " \nName: "
              + resultSet.getString("Name")
              + " \nType: "
              + resultSet.getString("Type"));
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
   * will then be stored into the H2 Database.
   */
  @FXML
  private void addProduct() {
    try {
      if (productName.getSelectedText().equalsIgnoreCase("")
          || manufacturerName.getSelectedText().equalsIgnoreCase("")) {
        System.out.println("Please try again");
      } else {
        String sql = "INSERT INTO Product (TYPE, MANUFACTURER, NAME) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, productType.getSelectionModel().getSelectedItem().toString());
        pstmt.setString(2, manufacturerName.getText());
        pstmt.setString(3, productName.getText());
        pstmt.executeUpdate();
        pstmt.close();
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

  /** The productionLog() method simply prints to the console for now. */
  @FXML
  void productionLog() {
    System.out.println("Button 3 pushed");
  }
}
