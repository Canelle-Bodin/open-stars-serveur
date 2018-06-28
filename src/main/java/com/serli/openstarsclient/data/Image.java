package com.serli.openstarsclient.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "image")

public class Image {

    @Id
    @Column(name = "id_image")
    private Integer idImage;

    @Column(name = "nom_planete")
    private String nomPlanete;

    @Column(name = "image_base64")
    private String imageBase64;

    public Integer getIdImage() {
        return idImage;
    }

    public void setIdImage(Integer idImage) {
        this.idImage = idImage;
    }

    public String getNomPlanete() {
        return nomPlanete;
    }

    public void setNomPlanete(String nomPlanete) {
        this.nomPlanete = nomPlanete;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
