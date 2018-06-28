package com.serli.openstarsclient.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rasp_user")
public class RaspUser {

    @Id
    @Column(name = "rasp_id_membre")
    private Long raspIdMembre;

    @Column(name = "Id_Rasp")
    private String idRasp;

    public Long getRaspIdMembre() {
        return raspIdMembre;
    }

    public void setRaspIdMembre(Long raspIdMembre) {
        this.raspIdMembre = raspIdMembre;
    }

    public String getIdRasp() {
        return idRasp;
    }

    public void setIdRasp(String idRasp) {
        this.idRasp = idRasp;
    }
}
