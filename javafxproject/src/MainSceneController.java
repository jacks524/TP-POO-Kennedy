import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainSceneController {

    @FXML
    private TextField tiTitle;

    @FXML
    void btnOnClicked(ActionEvent event) {
        Stage mainWindows = (Stage) tiTitle.getScene().getWindow();
        String Title = tiTitle.getText();
        mainWindows.setTitle(Title);
    }

}
