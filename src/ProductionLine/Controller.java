package ProductionLine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

  private static final String JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:./production_resources/production";
  private static final String USER = "";
  private static final String PASS = "";
  private Connection conn;
  private PreparedStatement pstmt = null;

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

  @FXML private Button recordProduction;

  @FXML private Button productionLogButton;

  @FXML
  void pushButton1() {
    initializeDB();
    addProduct.setOnAction(
        e -> {
          try {
            String sql = "INSERT INTO Product (TYPE, MANUFACTURER, NAME) VALUES ('Audio', ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, manufacturerName.getText());
            pstmt.setString(2, productName.getText());
            pstmt.executeUpdate(sql);
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
  void pushButton3() {}

  @FXML
  void saveProduct() {
    productName.setOnAction(e -> productName.getText());
  }

  @FXML
  void saveManufacturer() {}
}
