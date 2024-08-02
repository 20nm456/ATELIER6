package org.example.arobase;

import banque.ManageDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AjoutControl {

    @FXML
    private TextField numCh;
    @FXML
    private TextField nomch;
    @FXML
    private TextField montantch;
    @FXML
    private CheckBox typecc, typece;
    @FXML
    private Button ajoutbtn;
    @FXML
    private Button retourbtn;

    private final ManageDatabase manage = new ManageDatabase();

    private String getAccountType() {
        if (typece.isSelected()) {
            return "Compte Epargne";
        } else if (typecc.isSelected()) {
            return "Compte Courant";
        }
        return "";
    }

    private Double getTaux() {
        return typecc.isSelected() ? 8.0 : null;
    }

    private Double getDecouvert() {
        return typecc.isSelected() ? 5000.0 : null;
    }

    @FXML
    private void ajouter(ActionEvent actionEvent) {
        try {
            Double montant = Double.valueOf(montantch.getText());
            manage.inserer(numCh.getText(), nomch.getText(), montant, getAccountType(), getDecouvert(), getTaux());
            loadScene(actionEvent, "acceuil.fxml");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur SQL : " + e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Erreur de format de nombre : " + e.getMessage());
        }
    }


    @FXML
    private void retourAcceuil(ActionEvent actionEvent) {
        loadScene(actionEvent, "acceuil.fxml");
    }

    private void loadScene(ActionEvent actionEvent, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
