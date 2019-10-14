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
 * The Controller class implements most of the logic behind the JavaFX application. Analyze code is
 * showing a declaration redundancy error that the Controller class can be package-private, but when
 * that choice is made the project fails to compile.
 *
 * @author Andrew Cavallaro
 * @date 10/14/2019
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
  private void initializeDB() {
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** The add product button from the Production Line tab. */
  @FXML private Button addProduct;

  /** The product name text field in the Production Line tab. */
  @FXML private TextField productName;

  /** The manufacturer name text field in the Production Line tab. */
  @FXML private TextField manufacturerName;

  /** The choice box that holds the product types in the Production Line tab. */
  @FXML private ChoiceBox<?> productType;

  /** The table view used to view all the elements in the H2 Database in the Production Line tab. */
  @FXML private TableView<?> viewProducts;

  /** The column in the table view that is labeled ID. ID numbers will be displayed here. */
  @FXML private TableColumn<?, ?> columnID;

  /** The column in the table view that is labeled Type. Product types will be displayed here. */
  @FXML private TableColumn<?, ?> columnType;

  /**
   * The column in the table view that is labeled manufacturer. Product manufacturers will be
   * displayed here.
   */
  @FXML private TableColumn<?, ?> columnManufacturer;

  /** The column in the table view that is labeled Name. Product names will be displayed here. */
  @FXML private TableColumn<?, ?> columnName;

  /** The record production button in the Produce tab. */
  @FXML private Button recordProduction;

  /** The combo box that holds the quantity amounts in the Produce tab. */
  @FXML private ComboBox<?> quantityBox;

  /** The production log button in the Production Log tab. */
  @FXML private Button productionLogButton;

  /**
   * The initialize() method is used to instantiate the options for the combo box and choice box,
   * respectively. Since the combo box is being instantiated with numbers 1-10, a loop was used to
   * store the numbers. Easy replacement of the number 10 would generate more or less numbers if
   * desired. The second list uses the ItemType enum to instantiate the values to be stored in the
   * choicebox. An addAll() method was used to bring in all the values from the enum. Both the
   * choice box and combo box are set to select the first item in their observable list and display
   * upon first run of the program. The combo box has the option and ability to edit the value.
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
   * The addProduct() method is used to initialize and enter items into the H2 Database. A prepared
   * SQL statement is made that will be populated using the productType choice box, manufacturerName
   * text field, and productName text field. The SQL statement will be populated and passed in a
   * PreparedStatement and then used to execute an update. The entered information and selection
   * will then be stored into the H2 Database.
   */
  @FXML
  void addProduct() {
    try {
      initializeDB();
      String sql = "INSERT INTO Product (TYPE, MANUFACTURER, NAME) VALUES (?, ?, ?)";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, productType.getSelectionModel().getSelectedItem().toString());
      pstmt.setString(2, manufacturerName.getText());
      pstmt.setString(3, productName.getText());
      pstmt.executeUpdate();
      pstmt.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * The recordProduction() method tests the functionality of the MultimediaControl interface. It
   * establishes that both AudioPlayer and MoviePlayer can both be instantiated and pass methods and
   * values to the classes. To test functionality, press the Record Production button on the Produce
   * tab. The results will simply print to the console.
   */
  @FXML
  void recordProduction() {
    AudioPlayer newAudioProduct =
        new AudioPlayer(
            "DP-X1A", "Onkyo", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);
    ArrayList<MultimediaControl> productList = new ArrayList<>();
    productList.add(newAudioProduct);
    productList.add(newMovieProduct);
    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
    }
  }

  /** The productionLog() method simply prints to the console for now. */
  @FXML
  void productionLog() {
    System.out.println("Button 3 pushed");
  }
}
