package production;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The ProductionLine class holds the start() method for the JavaFX project. ProductionLine is a
 * subclass that extends the superclass Application and derives its methods and fields.
 *
 * @author Andrew Cavallaro
 * @date 11/26/2019
 */
public class ProductionLine extends Application {

  /**
   * The parent root will link with the production_line.fxml file to receive what will be displayed
   * on the production Stage. The title of the stage is currently set to "Product Tracking System."
   * The scene will originally be displayed at a width of 550 pixels and a height of 500 pixels.
   */
  @Override
  public void start(Stage production) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("production_line.fxml"));
    production.setTitle("Product Tracking System");
    production.setScene(new Scene(root, 550, 500));
    production.show();
  }

  /**
   * This is the main method that launches any arguments being passed. Controller object
   * myController is used to initialize the database without having to press a button first.
   */
  public static void main(String[] args) {
    Controller.initializeDB();
    launch(args);
  }
}
