package com.serli.openstarsclient.data;

import javax.persistence.*;

@Entity
@Table(name = "PHOTOSPROFIL")
public class ProfilPicture {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Id_photos_profil")
    private Long idProfilPicture;

    @Column(name = "Id_membre")
    private Long idMembrePicture;

    @Column(name = "urlphoto")
    private String urlPicture;

    @Transient
    private Long idStatus;

    @Transient
    private String pseudo;

    public ProfilPicture() {
    }

    public ProfilPicture(Long idMembrePicture, String urlPicture) {
        this.idMembrePicture = idMembrePicture;
        this.urlPicture = urlPicture;
    }

    public Long getIdProfilPicture() {
        return idProfilPicture;
    }

    public void setIdProfilPicture(Long idProfilPicture) {
        this.idProfilPicture = idProfilPicture;
    }

    public Long getIdMembrePicture() {
        return idMembrePicture;
    }

    public void setIdMembrePicture(Long idMembrePicture) {
        this.idMembrePicture = idMembrePicture;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

}
