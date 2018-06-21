package com.serli.openstarsclient.data;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
@Table(name = "abonneabonnement")
public class SubscriptionSubscriber {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_abonneabonnement")
    private Long idAbonneAbonnement;

    @Column(name = "id_membre")
    private Long membre;

    @Column(name = "id_abonne")
    private Long abonne;

    public SubscriptionSubscriber() {
    }

    public SubscriptionSubscriber(Long membre, Long abonne) {
        this.membre = membre;
        this.abonne = abonne;
    }

    public Long getIdAbonneAbonnement() {
        return idAbonneAbonnement;
    }

    public void setIdAbonneAbonnement(Long idAbonneAbonnement) {
        this.idAbonneAbonnement = idAbonneAbonnement;
    }

    public Long getMembre() {
        return membre;
    }

    public void setMembre(Long membre) {
        this.membre = membre;
    }

    public Long getAbonne() {
        return abonne;
    }

    public void setAbonne(Long abonne) {
        this.abonne = abonne;
    }

}
