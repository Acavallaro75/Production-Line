package production;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * The EmployeeLogin class is used as the Controller to the employee login screen. It compares data
 * entered into the text fields and compares them to values in the database. It allows for access to
 * the actual Production Line program.
 *
 * @author: Andrew Cavallaro
 * @date: 12/03/2019
 */
public class EmployeeLogin {

  /**
   * The text field to gather the username of the employee that will be compared to the database.
   */
  @FXML private TextField userName;

  /**
   * The text field to gather the password of the employee that will be compared to the database.
   */
  @FXML private PasswordField password;

  /**
   * The label that is used to display the error message to the employee if the username/password
   * combination is incorrect.
   */
  @FXML private Label badLogin;

  /**
   * The checkCredentials() method is used to verify the login information from the employee. If it
   * matches data in the database, the employee is logged into the system. If there is no match to
   * the database, the employee is given an error message.
   *
   * @param event mouse click event
   * @throws IOException yes, it does
   */
  @FXML
  public void checkCredentials(MouseEvent event) throws IOException {
    if (userName.getText().equalsIgnoreCase("") || password.getText().equalsIgnoreCase("")) {
      badLogin.setText("Bad login - Try again");
    } else {
      Controller controller = new Controller();
      if (controller.checkEmployee(userName.getText(), password.getText())) {
        Parent productionParent = FXMLLoader.load(getClass().getResource("production_line.fxml"));
        Scene productionScene = new Scene(productionParent);
        Stage productionStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        productionStage.setScene(productionScene);
        productionStage.show();
      } else {
        badLogin.setText("Bad login - Try again");
      }
    }
  }
}
