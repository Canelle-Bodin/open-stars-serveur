package com.serli.openstarsclient.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rasp")
public class Rasp {

    @Id
    private String raspId;

    public String getRaspId() {
        return raspId;
    }

    public void setRaspId(String raspId) {
        this.raspId = raspId;
    }
}
