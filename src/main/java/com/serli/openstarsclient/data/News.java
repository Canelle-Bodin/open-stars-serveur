package com.serli.openstarsclient.data;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "filactualite")
public class News {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_filactualite")
    private Long idFilActualite;

    @Column(name = "id_membre")
    private Long idMembre;

    @Column(name = "status")
    private String status;

    @Column(name = "archiver")
    private Boolean archive;

    @Column(name = "date_publication")
    private Date publicationDate;

    @Transient
    private Long nbLikeStatus;

    @Transient
    private String urlPicture;

    @Transient
    private String pseudo;

    public News(Long idMembre, String status, Boolean archive, Date publicationDate) {
        this.idMembre = idMembre;
        this.status = status;
        this.archive = archive;
        this.publicationDate = publicationDate;
    }

    public News() {
    }

    public Long getIdFilActualite() {
        return idFilActualite;
    }

    public void setIdFilActualite(Long idFilActualite) {
        this.idFilActualite = idFilActualite;
    }

    public Long getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Long idMembre) {
        this.idMembre = idMembre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicure) {
        this.urlPicture = urlPicure;
    }

    public Long getNbLikeStatus() {
        return nbLikeStatus;
    }

    public void setNbLikeStatus(Long nbLikeStatus) {
        this.nbLikeStatus = nbLikeStatus;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
