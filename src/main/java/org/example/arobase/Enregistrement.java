package org.example.arobase;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Enregistrement {
    private StringProperty numeroCompte = new SimpleStringProperty(this, "numeroCompte");
    public StringProperty numeroCompteProperty() {
        return numeroCompte;
    }

    public String getNumeroCompte() {
        return numeroCompte.get();
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte.set(numeroCompte);
    }

    private StringProperty titulaire = new SimpleStringProperty(this, "titulaire");
    public StringProperty titulaireProperty() {
        return titulaire;
    }


    public void setTitulaire(String titulaire) {
        this.titulaire.set(titulaire);
    }

    private DoubleProperty solde = new SimpleDoubleProperty(this, "solde");
    public DoubleProperty soldeProperty() {
        return solde;
    }

    public Enregistrement(String numeroCompte, String titulaire, Double solde, String type) {
        this.numeroCompte = new SimpleStringProperty(numeroCompte);
        this.titulaire = new SimpleStringProperty(titulaire);
        this.solde = new SimpleDoubleProperty(solde);
        this.type = new SimpleStringProperty(type);

    }
    public String getTitulaire() {
        return titulaire.get();
    }



    public Double getSolde() {
        return solde.get();
    }

    public void setSolde(Double solde) {
        this.solde.set(solde);
    }

    public String getType() {
        return type.get();
    }

    private StringProperty type = new SimpleStringProperty(this, "type");
    public StringProperty typeProperty() {
        return type;
    }
        public void setType(String type) {
        this.type.set(type);
    }

}
