package com.serli.openstarsclient.data;


import javax.persistence.*;

@Entity
@Table(name = "Membre")
public class UserResponse {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Id_membre")
    private Long idMembre;

    @Column(name = "Email")
    private String email;

    @Column(name = "reponsesecret")
    private String secretResponse;

    public Long getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Long idMembre) {
        this.idMembre = idMembre;
    }

    public String getSecretResponse() {
        return secretResponse;
    }

    public void setSecretResponse(String secretResponse) {
        this.secretResponse = secretResponse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
