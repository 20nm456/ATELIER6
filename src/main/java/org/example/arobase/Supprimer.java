package org.example.arobase;

import banque.ManageDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Supprimer {
    @FXML
    private TextField id;
    @FXML
    private Button btnSup;
    public void supprimer(ActionEvent actionEvent) throws SQLException {
        ManageDatabase manage=new ManageDatabase();
        manage.supprimerCompte(id.getText());
        Stage stage=(Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
