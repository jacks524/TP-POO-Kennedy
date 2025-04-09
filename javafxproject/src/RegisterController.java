import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
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
    private Button Enregistrer;

    @FXML
    private TextField Lieu;

    @FXML
    private TextField Nom_appareil;

    @FXML
    private TextField Numero_serie;

    @FXML
    private TextField Type_appareil;

    @FXML
    private DatePicker date;

    @FXML
    private Button Menu;

    /**
     * @param event
     */
    @FXML
    void onButtonclick(ActionEvent event) {
        String nomAppareil = Nom_appareil.getText();
        String typeAppareil = Type_appareil.getText();
        String numeroSerie = Numero_serie.getText();
        String lieu = Lieu.getText();
        java.sql.Date dateVol = (date.getValue() != null) ? java.sql.Date.valueOf(date.getValue()) : null;
        try {
            // Connexion à la base de données

            String url = "jdbc:mysql://localhost:3306/equipement_perdus";
            String user = "root";
            String password = "";
            Connection con = DriverManager.getConnection(url, user, password);

            // Requête SQL d'insertion
            String sql = "INSERT INTO appareils (nom_appareil, type_appareil, numero_serie, lieu, date_vol) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nomAppareil);
            pstmt.setString(2, typeAppareil);
            pstmt.setString(3, numeroSerie);
            pstmt.setString(4, lieu);
            pstmt.setDate(5, dateVol);

            pstmt.executeUpdate();
            con.close();

            System.out.println("Appareil enregistré avec succès.");

            Nom_appareil.clear();
            Type_appareil.clear();
            Numero_serie.clear();
            Lieu.clear();
            date.setValue(null);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'enregistrement.");
        }

    }

    @FXML
    void Menu(ActionEvent event) {
        switchScene("Menuprincipal.fxml", "Menu", event);

    }

    public Button getEnregistrer() {
        return Enregistrer;
    }

    public void setEnregistrer(Button enregistrer) {
        Enregistrer = enregistrer;
    }

    public TextField getLieu() {
        return Lieu;
    }

    public void setLieu(TextField lieu) {
        Lieu = lieu;
    }

    public TextField getNom_appareil() {
        return Nom_appareil;
    }

    public void setNom_appareil(TextField nom_appareil) {
        Nom_appareil = nom_appareil;
    }

    public TextField getNumero_serie() {
        return Numero_serie;
    }

    public void setNumero_serie(TextField numero_serie) {
        Numero_serie = numero_serie;
    }

    public TextField getType_appareil() {
        return Type_appareil;
    }

    public void setType_appareil(TextField type_appareil) {
        Type_appareil = type_appareil;
    }

    public DatePicker getDate() {
        return date;
    }

    public void setDate(DatePicker date) {
        this.date = date;
    }

}
