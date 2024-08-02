package org.example.arobase;

import banque.ConnectDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HelloController  implements Initializable {

    @FXML
    private Label quit, supprimer, modifier, ajout,transfert, epargne,courant,tous, recherche,journal,title,identity,ac;

    @FXML
    private  Pane compteUser,menuBar,appBar,acceuil,arriere;
    @FXML
    private VBox menu;

    @FXML
    private TableView<Enregistrement> table;

    @FXML
    private TableColumn<Enregistrement, String> numCol;
    @FXML
    private TableColumn<Enregistrement, String> tituCol;
    @FXML
    private TableColumn<Enregistrement, Double> soldCol;
    @FXML
    private TableColumn<Enregistrement, String> typCol;

    @FXML
    private TextField userTextField;
    @FXML
    private Pane command;

    Enregistrement comptes;
    static ConnectDatabase conn=new ConnectDatabase();
    static Connection connection=conn.connected();

    public void quitter(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void modifier(MouseEvent mouseEvent) throws IOException {
    }

    public void suppimer(MouseEvent mouseEvent) throws IOException {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("supprimer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("supprimer");
        stage.setScene(scene);
        stage.show();
    }

    public void journal(MouseEvent mouseEvent) throws IOException {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("journal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("JOURNAL");
        stage.setScene(scene);
        stage.show();
    }

    public void transferer(MouseEvent mouseEvent) throws IOException {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("transfert.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("transfert de fonds");
        stage.setScene(scene);
        stage.show();
    }

    public void afficher(MouseEvent mouseEvent) {
        chargerDonnees();
    }

    public void courant(MouseEvent mouseEvent) {
        ObservableList<Enregistrement> compte = FXCollections.observableArrayList();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT  numeroCompte," +
                        "titulaire, solde, TYPE FROM CompteBancaire WHERE TYPE='CompteCourant'")) {

            while (resultSet.next()) {
                String num = resultSet.getString(1);
                String titulaire = resultSet.getString(2);
                Double solde= resultSet.getDouble(3);
                String type = resultSet.getString(4);

                compte.add(new Enregistrement(num,titulaire,solde,type));
            }


            numCol.setCellValueFactory(new PropertyValueFactory<>("numeroCompte'"));
            tituCol.setCellValueFactory(new PropertyValueFactory<>("titulaire"));
            soldCol.setCellValueFactory(new PropertyValueFactory<>("solde"));
            typCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            table.setItems(compte);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void epargne(MouseEvent mouseEvent) {
        ObservableList<Enregistrement> compte = FXCollections.observableArrayList();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT  numeroCompte," +
                        "titulaire, solde, TYPE FROM CompteBancaire WHERE TYPE='CompteEpargne'")) {

            while (resultSet.next()) {
                String num = resultSet.getString(1);
                String titulaire = resultSet.getString(2);
                Double solde = resultSet.getDouble(3);
                String type = resultSet.getString(4);

                compte.add(new Enregistrement(num, titulaire, solde, type));
            }


            numCol.setCellValueFactory(new PropertyValueFactory<>("numeroCompte"));
            tituCol.setCellValueFactory(new PropertyValueFactory<>("titulaire"));
            soldCol.setCellValueFactory(new PropertyValueFactory<>("solde"));
            typCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            table.setItems(compte);
        }catch (SQLException e) {
            e.printStackTrace();
        }

        }

    public void ajouter(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ajouterCompte.fxml"));
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void chargerDonnees() {
        ObservableList<Enregistrement> compte = FXCollections.observableArrayList();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT  numeroCompte," +
                        "titulaire, solde, TYPE FROM CompteBancaire")) {

            while (resultSet.next()) {
                String num = resultSet.getString(1);
                String titulaire = resultSet.getString(2);
                Double solde= resultSet.getDouble(3);
                String type = resultSet.getString(4);

                compte.add(new Enregistrement(num,titulaire,solde,type));
            }


            numCol.setCellValueFactory(new PropertyValueFactory<>("numeroCompte"));
            tituCol.setCellValueFactory(new PropertyValueFactory<>("titulaire"));
            soldCol.setCellValueFactory(new PropertyValueFactory<>("solde"));
            typCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            table.setItems(compte);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        chargerDonnees();
    }

}