package com.serli.openstarsclient.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "MEMBRE")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id_membre")
	private Long idMembre;

	@Column(name = "Nom")
	private String familyName;

	@Column(name = "Prenom")
	private String name;

	@Column(name = "Pseudo")
	private String pseudo;

	@Column(name = "Pays")
	private String country;

	@Column(name = "Biographie")
	private String biography;

	@Column(name = "motdepasse")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="rasp_user",
			joinColumns = @JoinColumn(name="rasp_id_membre"),
			inverseJoinColumns = @JoinColumn(name = "Id_Rasp"))
	private List<Rasp> rasps;

	@Column(name = "Email")
	private String email;

	@Column(name = "questionsecrete")
	private String secretQuestion;

	@Column(name = "reponsesecret")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String secretResponse;

	@Column(name = "verifyaccount")
	private Boolean verifyAccount;

	@Transient
	private List<User> abonnes;

	@Transient
	private List<User> abonnements;

	@Transient
	private List<PicturePublication> picturePublications;

	@Transient
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String oldPassword;

	@Transient
	private Long subscriber;

	@Transient
	private Long subscription;

	@Transient
	private Long publication;

	@Transient
	private String urlPicture;

	public User(String familyName, String name, String pseudo, String email, String password, String country, String biography,  String secretQuestion, String secretResponse, Boolean verifyAccount) {
		this.familyName = familyName;
		this.name = name;
		this.pseudo = pseudo;
		this.country = country;
		this.biography = biography;
		this.password = password;
		this.email = email;
		this.secretQuestion = secretQuestion;
		this.secretResponse = secretResponse;
		this.verifyAccount = verifyAccount;
	}

	public User() {
	}

	public Long getIdMembre() {
		return idMembre;
	}

	public void setIdMembre(Long idMembre) {
		this.idMembre = idMembre;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public String getSecretResponse() {
		return secretResponse;
	}

	public void setSecretResponse(String secretResponse) {
		this.secretResponse = secretResponse;
	}

	public List<User> getAbonnes() {
		return abonnes;
	}

	public void setAbonnes(List<User> abonnes) {
		this.abonnes = abonnes;
	}

	public List<User> getAbonnements() {
		return abonnements;
	}

	public void setAbonnements(List<User> abonnements) {
		this.abonnements = abonnements;
	}

	public Long getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Long subscriber) {
		this.subscriber = subscriber;
	}

	public Long getSubscription() {
		return subscription;
	}

	public void setSubscription(Long subscription) {
		this.subscription = subscription;
	}

	public String getUrlPicture() {
		return urlPicture;
	}

	public void setUrlPicture(String urlPicture) {
		this.urlPicture = urlPicture;
	}

	public Long getPublication() {
		return publication;
	}

	public void setPublication(Long publication) {
		this.publication = publication;
	}

	public List<PicturePublication> getPicturePublications() {
		return picturePublications;
	}

	public void setPicturePublications(List<PicturePublication> picturePublications) {
		this.picturePublications = picturePublications;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setNewPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Boolean getVerifyAccount() {
		return verifyAccount;
	}

	public void setVerifyAccount(Boolean verifyAccount) {
		this.verifyAccount = verifyAccount;
	}

	public List<Rasp> getRasps() {
		return rasps;
	}

	public void setRasps(List<Rasp> rasps) {
		this.rasps = rasps;
	}
}