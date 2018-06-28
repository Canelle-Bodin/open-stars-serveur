package com.serli.openstarsclient.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "requetesobservation")

public class Coordonnees {

    @Id
    @Column(name = "id_requete_observation")
    private Integer id;

    @Column(name = "nom_planete")
    private String nomPlanete;

    @Column(name = "coordonnee_planete")
    private String coord;

    @Column(name = "url_prise_de_vue")
    private String url;

    @Column(name = "etat_requete")
    private Etat etat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomPlanete() {
        return nomPlanete;
    }

    public void setNomPlanete(String nomPlanete) {
        this.nomPlanete = nomPlanete;
    }

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
}
