package com.serli.openstarsclient.data;

import javax.persistence.*;

@Entity
@Table(name = "INFORMATIONPLANETE")
public class InformationPlanete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_informationplanete")
    private Long idInformationPlanete;

    @Column(name = "nom_planete")
    private String nomPlanete;

    @Column(name = "rayon_equatorial")
    private Long rayonEquatorial;

    @Column(name = "perimetre")
    private Long perimetre;

    @Column(name = "superficie")
    private Long superficie;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "masse")
    private String masse;

    @Column(name = "temperature_moyenne")
    private Long tempMoy;

    @Column(name = "temperature_min")
    private Long tempMin;

    @Column(name = "temperature_max")
    private Long tempMax;

    @Column(name = "revolution")
    private Long revolution;

    @Column(name = "nb_satellites")
    private Long nbSatellites;

    @Column(name = "type_planete")
    private String typePlanete;

    @Column(name = "details")
    private String details;


    public InformationPlanete() {}


    public Long getIdInformationPlanete() {
        return idInformationPlanete;
    }

    public void setIdInformationPlanete(Long idInformationPlanete) {
        this.idInformationPlanete = idInformationPlanete;
    }

    public String getNomPlanete() {
        return nomPlanete;
    }

    public void setNomPlanete(String nomPlanete) {
        this.nomPlanete = nomPlanete;
    }

    public Long getRayonEquatorial() {
        return rayonEquatorial;
    }

    public void setRayonEquatorial(Long rayonEquatorial) {
        this.rayonEquatorial = rayonEquatorial;
    }

    public Long getPerimetre() {
        return perimetre;
    }

    public void setPerimetre(Long perimetre) {
        this.perimetre = perimetre;
    }

    public Long getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Long superficie) {
        this.superficie = superficie;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public String getMasse() {
        return masse;
    }

    public void setMasse(String masse) {
        this.masse = masse;
    }

    public Long getTempMoy() {
        return tempMoy;
    }

    public void setTempMoy(Long tempMoy) {
        this.tempMoy = tempMoy;
    }

    public Long getTempMin() {
        return tempMin;
    }

    public void setTempMin(Long tempMin) {
        this.tempMin = tempMin;
    }

    public Long getTempMax() {
        return tempMax;
    }

    public void setTempMax(Long tempMax) {
        this.tempMax = tempMax;
    }

    public Long getRevolution() {
        return revolution;
    }

    public void setRevolution(Long revolution) {
        this.revolution = revolution;
    }

    public Long getNbSatellites() {
        return nbSatellites;
    }

    public void setNbSatellites(Long nbSatellites) {
        this.nbSatellites = nbSatellites;
    }

    public String getTypePlanete() {
        return typePlanete;
    }

    public void setTypePlanete(String typePlanete) {
        this.typePlanete = typePlanete;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
