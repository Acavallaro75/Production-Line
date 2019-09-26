package production;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller implements Initializable {

  private static final String JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:./production_resources/production";
  private static final String USER = "";
  private static final String PASS = "";
  private static Connection conn;
  private static PreparedStatement preparedStatement;

  private void initializeDB() {
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML private Button addProduct;

  @FXML private TextField productName;

  @FXML private TextField manufacturerName;

  @FXML private ChoiceBox<?> itemType;

  @FXML private TableView<?> viewProducts;

  @FXML private TableColumn<?, ?> columnId;

  @FXML private TableColumn<?, ?> columnType;

  @FXML private TableColumn<?, ?> columnManufacturer;

  @FXML private TableColumn<?, ?> columnName;

  @FXML private Button recordProduction;

  @FXML private ComboBox<?> quantityBox;

  @FXML private Button productionLogButton;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    List<Integer> numbers = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      numbers.add(i);
    }
    ObservableList obList = FXCollections.observableList(numbers);
    quantityBox.getItems().clear();
    quantityBox.setItems(obList);
    quantityBox.getSelectionModel().selectFirst();
    List<String> types = new ArrayList<>();
    types.add("Appliances");
    types.add("Audio");
    types.add("Video");
    ObservableList observableList = FXCollections.observableList(types);
    itemType.getItems().clear();
    itemType.setItems(observableList);
    itemType.getSelectionModel().selectFirst();
  }

  @FXML
  void pushButton1() {
    addProduct.setOnAction(
        e -> {
          try {
            initializeDB();
            String sql = "INSERT INTO Product (TYPE, MANUFACTURER, NAME) VALUES (?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(
                1, itemType.getSelectionModel().getSelectedItem().toString());
            preparedStatement.setString(2, manufacturerName.getText());
            preparedStatement.setString(3, productName.getText());
            preparedStatement.executeUpdate();
          } catch (SQLException ex) {
            ex.printStackTrace();
          }
          System.out.println("Hello");
        });
  }

  @FXML
  void pushButton2() {
    System.out.println("Button 2 pressed");
  }

  @FXML
  void pushButton3() {
    System.out.println("Button 3 pressed");
  }
}
