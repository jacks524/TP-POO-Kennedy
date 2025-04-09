import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConfirmationController {

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
    private Button commande;

    @FXML
    private Label nom;

    @FXML
    private Label serie;

    @FXML
    private Text text;

    @FXML
    private Label type;

    @FXML
    void commande(ActionEvent event) {
        switchScene("appareil.fxml", "rechercher un appareil", event);

    }

    public void setInfos(String nom, String type, String numeroSerie) {
        this.nom.setText(nom);
        this.type.setText(type);
        this.serie.setText(numeroSerie);
    }

}
