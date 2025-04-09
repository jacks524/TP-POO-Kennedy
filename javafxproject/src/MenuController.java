import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {
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
    private Button Enrgistrerapp;

    @FXML
    private Button aide;

    @FXML
    private Button rechercher;

    /**
     * @param event
     */
    @FXML
    void aide(ActionEvent event) {
        switchScene("aide.fxml", "aide", event);

    }

    @FXML

    void onbuttoncliked(ActionEvent event) {
        switchScene("Register.fxml", "Enregistrement d'un objet trouvé", event);

    }

    @FXML
    void clicked(ActionEvent event) {
        switchScene("appareil.fxml", "commander un appareil", event);
    }

}
