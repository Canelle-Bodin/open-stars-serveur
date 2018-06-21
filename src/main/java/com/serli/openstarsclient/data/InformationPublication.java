package com.serli.openstarsclient.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "informationpublication")
public class InformationPublication {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Id_informationpublication")
    private Long idInformationPubication;

    @Column(name = "Id_publication")
    private Long idPublication;

    @Column(name = "Id_membre")
    private Long idMembre;

    @Column(name = "commentaire_photo")
    private String commentPicture;

    @Column(name = "aime_photo")
    private Boolean likePicture;

    @Column(name = "signal_photo")
    private Boolean signalPicture;

    @Column(name = "raison_signal")
    private String reasonSignal;

    @Transient
    private ProfilPicture profilPicture;

    @Transient
    private PicturePublication picturePublication;

    @Transient
    private User user;

    @Transient
    private Long numberLike;

    @Transient
    private Long numberDislike;

    public InformationPublication() {
    }

    public InformationPublication(Long idPublication, Long idMembre, String commentPicture, Boolean likePicture, Boolean signalPicture, String reasonSignal) {
        this.idPublication = idPublication;
        this.idMembre = idMembre;
        this.commentPicture = commentPicture;
        this.likePicture = likePicture;
        this.signalPicture = signalPicture;
        this.reasonSignal = reasonSignal;
    }

    public InformationPublication(Long idPublication, Long idMembre, Boolean signalPicture, String reasonSignal) {
        this.idPublication = idPublication;
        this.idMembre = idMembre;
        this.signalPicture = signalPicture;
        this.reasonSignal = reasonSignal;
    }

    public InformationPublication(Long idPublication, Long idMembre, Boolean likePicture) {
        this.idPublication = idPublication;
        this.idMembre = idMembre;
        this.likePicture = likePicture;
    }

    public Long getIdInformationPubication() {
        return idInformationPubication;
    }

    public void setIdInformationPubication(Long idInformationPubication) {
        this.idInformationPubication = idInformationPubication;
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

    public String getCommentPicture() {
        return commentPicture;
    }

    public void setCommentPicture(String commentPicture) {
        this.commentPicture = commentPicture;
    }

    public Boolean getLikePicture() {
        return likePicture;
    }

    public void setLikePicture(Boolean likePicture) {
        this.likePicture = likePicture;
    }

    public Boolean getSignalPicture() {
        return signalPicture;
    }

    public void setSignalPicture(Boolean signalPicture) {
        this.signalPicture = signalPicture;
    }

    public String getReasonSignal() {
        return reasonSignal;
    }

    public void setReasonSignal(String reasonSignal) {
        this.reasonSignal = reasonSignal;
    }

    public ProfilPicture getProfilPicture() {
        return profilPicture;
    }

    public void setProfilPicture(ProfilPicture profilPicture) {
        this.profilPicture = profilPicture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getNumberLike() {
        return numberLike;
    }

    public void setNumberLike(Long numberLike) {
        this.numberLike = numberLike;
    }

    public Long getNumberDislike() {
        return numberDislike;
    }

    public void setNumberDislike(Long numberDislike) {
        this.numberDislike = numberDislike;
    }

    public PicturePublication getPicturePublication() {
        return picturePublication;
    }

    public void setPicturePublication(PicturePublication picturePublication) {
        this.picturePublication = picturePublication;
    }
}
