package com.serli.openstarsclient.data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "informationfilactualite")
public class InformationNews {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_informationfilactualite")
    private Long idInformationFilActualite;

    @Column(name = "id_filactualite")
    private Long idFilActualite;

    @Column(name = "id_membre")
    private Long idMember;

    @Column(name = "aime")
    private Boolean likeStatus;

    @Column(name = "commentaire")
    private String commentStatus;

    @Column(name = "date_information")
    private Date informationDate;

    @Transient
    private String urlPhoto;

    @Transient
    private Long numberLike;

    @Transient
    private String pseudo;

    public InformationNews() {
    }

    public InformationNews(Long idFilActualite, Long idMember, Boolean likeStatus, Date informationDate) {
        this.idFilActualite = idFilActualite;
        this.idMember = idMember;
        this.likeStatus = likeStatus;
        this.informationDate = informationDate;
    }

    public InformationNews(Long idFilActualite, Long idMember, Boolean likeStatus, String commentStatus, Date informationDate) {
        this.idFilActualite = idFilActualite;
        this.idMember = idMember;
        this.likeStatus = likeStatus;
        this.commentStatus = commentStatus;
        this.informationDate = informationDate;
    }

    public Long getIdInformationFilActualite() {
        return idInformationFilActualite;
    }

    public void setIdInformationFilActualite(Long idInformationFilActualite) {
        this.idInformationFilActualite = idInformationFilActualite;
    }

    public Long getIdFilActualite() {
        return idFilActualite;
    }

    public void setIdFilActualite(Long idFilActualite) {
        this.idFilActualite = idFilActualite;
    }

    public Boolean getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Date getInformationDate() {
        return informationDate;
    }

    public void setInformationDate(Date informationDate) {
        this.informationDate = informationDate;
    }

    public Long getIdMember() {
        return idMember;
    }

    public void setIdMember(Long idMember) {
        this.idMember = idMember;
    }

    public Long getNumberLike() {
        return numberLike;
    }

    public void setNumberLike(Long numberLike) {
        this.numberLike = numberLike;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

}
