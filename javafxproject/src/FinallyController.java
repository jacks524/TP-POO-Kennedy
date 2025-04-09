import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FinallyController {
    private void switchScene(String fxmlFile, String title, ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root;
        try {
            root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {

            System.err.println("Erreur lors du chargement de l'interface.");
            e.printStackTrace();
        }

    }

    @FXML
    private Button cherchezapp;

    @FXML
    private Button menu;

    @FXML
    private Text message;

    @FXML
    void cherchezapp(ActionEvent event) {
        switchScene("appareil.fxml", "Chercher un appareil", event);

    }

    @FXML
    void retourmenu(ActionEvent event) {
        switchScene("Menuprincipal.fxml", "Menu", event);

    }
}
