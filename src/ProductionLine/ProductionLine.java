package ProductionLine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProductionLine extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("production_line.fxml"));
    primaryStage.setTitle("ASSET-DEX");
    primaryStage.setScene(new Scene(root, 550, 500));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

  /*

  private void showData() {
    String productName =
    try {
      String sql = "INSERT INTO Product(Type, Manufacturer, Name) VALUES " + tableName;
      ResultSet rs = stmt.executeQuery(sql);
      ResultSetMetaData rsmd = rs.getMetaData();
      int numberOfColumns = rsmd.getColumnCount();
      for (int i = 1; i <= numberOfColumns; i++) {
        ta.appendText(rsmd.getColumnName(i) + "\t");
      }
      ta.appendText("\n");
      while (rs.next()) {
        for (int i = 1; i <= numberOfColumns; i++) {
          ta.appendText(rs.getString(i) + "\t");
        }
        ta.appendText("\n");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }*/
