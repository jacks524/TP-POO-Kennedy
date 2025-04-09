import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AppareilController {

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
    private Button Menu;

    @FXML
    private Button butt;

    @FXML
    private TextField nom;

    @FXML
    private TextField numserie2;

    @FXML
    private Text titre;

    @FXML
    private TextField type;

    @FXML
    void Menu(ActionEvent event) {
        switchScene("Menuprincipal.fxml", "Lost Found !", event);
    }

    @FXML
    void clik(ActionEvent event) {
        String nomAppareil = nom.getText();
        String typeAppareil = type.getText();
        String numSerie = numserie2.getText();

        try {
            // Connexion à la base de données
            String url = "jdbc:mysql://localhost:3306/equipement_perdus";
            String user = "root";
            String password = "";
            Connection con = DriverManager.getConnection(url, user, password);

            // Vérifier si l'appareil est signalé volé
            String checkSql = "SELECT * FROM appareils WHERE numero_serie = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setString(1, numSerie);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // L'appareil est volé, afficher alerte
                FXMLLoader loader = new FXMLLoader(getClass().getResource("finally.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Appareil volé");
                stage.setScene(new Scene(root));
                stage.show();

                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();

            } else {
                // Appareil pas volé, on insère la commande
                String sql = "INSERT INTO commande (nom_appareil, type_appareil, numero_serie) VALUES (?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, nomAppareil);
                pstmt.setString(2, typeAppareil);
                pstmt.setString(3, numSerie);
                pstmt.executeUpdate();

                con.close();

                System.out.println("Appareil enregistré avec succès.");

                nom.clear();
                type.clear();
                numserie2.clear();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("controller.fxml"));
                Parent root = loader.load();
                ConfirmationController controller = loader.getController();
                controller.setInfos(nomAppareil, typeAppareil, numSerie);
                Stage stage = new Stage();
                stage.setTitle("Confirmation de commande");
                stage.setScene(new Scene(root));
                stage.show();
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'enregistrement.");
        }
    }
}
