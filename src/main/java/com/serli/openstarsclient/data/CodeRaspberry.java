package com.serli.openstarsclient.data;

import javax.persistence.*;

@Entity
@Table(name = "raspberrycode")
public class CodeRaspberry {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_raspberry")
    private Long idRaspberry;

    @Column(name = "numero_raspberry")
    private Long numberRaspberry;

    @Column(name = "code_raspberry")
    private Long codeRaspberry;

    @Column(name = "prenom_proprietaire")
    private String ownerName;

    @Column(name = "active")
    private Boolean active;

    public Long getIdRaspberry() {
        return idRaspberry;
    }

    public void setIdRaspberry(Long idRaspberry) {
        this.idRaspberry = idRaspberry;
    }

    public Long getNumberRaspberry() {
        return numberRaspberry;
    }

    public void setNumberRaspberry(Long numberRaspberry) {
        this.numberRaspberry = numberRaspberry;
    }

    public Long getCodeRaspberry() {
        return codeRaspberry;
    }

    public void setCodeRaspberry(Long codeRaspberry) {
        this.codeRaspberry = codeRaspberry;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
