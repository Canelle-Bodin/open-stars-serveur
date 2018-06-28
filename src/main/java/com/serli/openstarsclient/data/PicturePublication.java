package com.serli.openstarsclient.data;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PUBLICATIONIMAGE")
public class PicturePublication{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Id_publication")
    private Long idPublication;

    @Column(name = "Id_membre")
    private Long idMembre;

    @Column(name = "date_publication")
    private Date publicationDate;

    @Column(name = "chemin_image")
    private String pathPicture;

    @Column(name = "description")
    private String description;

    @Column(name = "planete")
    private String planete;

    @Transient
    private String pseudo;

    @Transient
    private Boolean likePicture;

    @Transient
    private Long numberLike;

    public PicturePublication() {
    }

    public PicturePublication(Long idMembre, String pathPicture, Date publicationDate, String description) {
        this.idMembre = idMembre;
        this.pathPicture = pathPicture;
        this.publicationDate = publicationDate;
        this.description = description;
    }

    public String getPlanete() {
        return planete;
    }

    public void setPlanete(String planete) {
        this.planete = planete;
    }

    public Long getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(Long idPublication) {
        this.idPublication = idPublication;
    }

    public Long getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Long idMembre) {
        this.idMembre = idMembre;
    }

    public String getPathPicture() {
        return pathPicture;
    }

    public void setPathPicture(String pathPicture) {
        this.pathPicture = pathPicture;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getLikePicture() {
        return likePicture;
    }

    public void setLikePicture(Boolean likePicture) {
        this.likePicture = likePicture;
    }

    public Long getNumberLike() {
        return numberLike;
    }

    public void setNumberLike(Long numberLike) {
        this.numberLike = numberLike;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

}
