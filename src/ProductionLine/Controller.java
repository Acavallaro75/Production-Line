package ProductionLine;

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
import javafx.scene.control.ComboBox;
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

  @FXML private TableView<?> viewProducts;

  @FXML private Button recordProduction;

  @FXML private ComboBox<?> quantityBox;

  @FXML private Button productionLogButton;

  @FXML
  void pushButton1() {
    initializeDB();
    addProduct.setOnAction(
        e -> {
          try {
            String sql = "INSERT INTO Product (TYPE, MANUFACTURER, NAME) VALUES ('Audio', ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, manufacturerName.getText());
            preparedStatement.setString(2, productName.getText());
            preparedStatement.executeUpdate();
          } catch (SQLException ex) {
            ex.printStackTrace();
          }
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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    List<Integer> list = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      list.add(i);
    }
    ObservableList obList = FXCollections.observableList(list);
    quantityBox.getItems().clear();
    quantityBox.setItems(obList);
    quantityBox.getSelectionModel().selectFirst();
  }
}
