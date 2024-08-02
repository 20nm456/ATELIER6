package org.example.arobase;

import banque.ManageDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.nio.Buffer;
import java.sql.SQLException;

public class TransControl {

    @FXML
    private TextField idRec,idEnv,montant;

    @FXML
    private Button btnVal;

    public void transferer(ActionEvent actionEvent) throws SQLException {

        ManageDatabase manage=new ManageDatabase();
        manage.tansfert(idEnv.getText(),idRec.getText(), Double.parseDouble(montant.getText()));
        Stage stage=(Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
