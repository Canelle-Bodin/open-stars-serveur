package com.serli.openstarsclient.data;

import javax.persistence.*;

@Entity
@Table(name = "informationcommentaire")
public class InformationComment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_information_commentaire")
    private Long idInformationCommentaire;

    @Column(name = "id_information_publication")
    private Long idInformationPublication;

    @Column(name = "id_membre")
    private Long idMembre;

    @Column(name = "aime_commentaire")
    private Boolean likeComment;

    @Column(name = "dislike_commentaire")
    private Boolean dislikeComment;

    @Transient
    private Boolean etatLikeDislike;

    @Transient
    private Long countLikeDislike;

    public InformationComment() {
    }

    public InformationComment(Long idInformationPublication, Long idMembre, Boolean likeComment, Boolean dislikeComment) {
        this.idInformationPublication = idInformationPublication;
        this.idMembre = idMembre;
        this.likeComment = likeComment;
        this.dislikeComment = dislikeComment;
    }

    public InformationComment(Boolean etatLikeDislike, Long countLikeDislike) {
        this.etatLikeDislike = etatLikeDislike;
        this.countLikeDislike = countLikeDislike;
    }


    public Long getIdInformationCommentaire() {
        return idInformationCommentaire;
    }

    public void setIdInformationCommentaire(Long idInformationCommentaire) {
        this.idInformationCommentaire = idInformationCommentaire;
    }

    public Long getIdInformationPublication() {
        return idInformationPublication;
    }

    public void setIdInformationPublication(Long idInformationPublication) {
        this.idInformationPublication = idInformationPublication;
    }

    public Long getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Long idMembre) {
        this.idMembre = idMembre;
    }

    public Boolean getLikeComment() {
        return likeComment;
    }

    public void setLikeComment(Boolean likeComment) {
        this.likeComment = likeComment;
    }

    public Boolean getDislikeComment() {
        return dislikeComment;
    }

    public void setDislikeComment(Boolean dislikeComment) {
        this.dislikeComment = dislikeComment;
    }

    public Boolean getEtatLikeDislike() {
        return etatLikeDislike;
    }

    public void setEtatLikeDislike(Boolean etatLikeDislike) {
        this.etatLikeDislike = etatLikeDislike;
    }

    public Long getCountLikeDislike() {
        return countLikeDislike;
    }

    public void setCountLikeDislike(Long countLikeDislike) {
        this.countLikeDislike = countLikeDislike;
    }
}
