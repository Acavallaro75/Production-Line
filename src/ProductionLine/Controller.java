package ProductionLine;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Controller {

  @FXML private Button productLineButton;

  @FXML private Button recordProductionButton;

  @FXML private Button productionLogButton;

  @FXML
  void pushButton1(MouseEvent event) {
    System.out.println("Button 1 pressed");
  }

  @FXML
  void pushButton2(MouseEvent event) {
    System.out.println("Button 2 pressed");
  }

  @FXML
  void pushButton3(MouseEvent event) {
    System.out.println("Button 3 pressed");
  }
}
