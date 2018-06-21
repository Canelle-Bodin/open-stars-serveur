package com.serli.openstarsclient.controller;

import com.serli.openstarsclient.data.*;
import com.serli.openstarsclient.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.serli.openstarsclient.security.SHA1PasswordEncoder.byteToHex;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserResponseRepository userResponseRepository;

    @Autowired
    private SubscriptionSubscriberRepository subscriptionSubscriberRepository;

    @Autowired
    private ProfilPictureRepository profilPictureRepository;

    @Autowired
    private PicturePublicationRepository picturePublicationRepository;

    @Autowired
    private InformationPublicationRepository informationPublicationRepository;

    @Autowired
    private InformationCommentRespository informationCommentRespository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private InformationNewsRepository informationNewsRepository;

    @Autowired
    private CodeRaspberryRepository codeRaspberryRepository;

    @RequestMapping(value = "/api/inscriptionapp", method = RequestMethod.POST)
    String createAccount (@RequestBody User userToCreateAccount) throws Exception {
        String email = userToCreateAccount.getEmail();
        String pseudo = userToCreateAccount.getPseudo();
        String password = userToCreateAccount.getPassword();
        String sha1;
        MessageDigest crypt;
        crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        sha1 = byteToHex(crypt.digest());
        User usersEmail = userRepository.findByEmail(email);
        if (usersEmail == null) {
            User usersPseudo = userRepository.findByPseudo(pseudo);
            if (usersPseudo == null){
                String familyName = userToCreateAccount.getFamilyName();
                String name = userToCreateAccount.getName();
                String biography = userToCreateAccount.getBiography();
                String country = userToCreateAccount.getCountry();
                String secretQuestion = userToCreateAccount.getSecretQuestion();
                String secretResponse = userToCreateAccount.getSecretResponse();
                userToCreateAccount.setFamilyName(familyName);
                userToCreateAccount.setName(name);
                userToCreateAccount.setBiography(biography);
                userToCreateAccount.setCountry(country);
                userToCreateAccount.setSecretQuestion(secretQuestion);
                userToCreateAccount.setSecretResponse(secretResponse);
                userToCreateAccount.setPassword(sha1);
                userToCreateAccount.setVerifyAccount(false);
                userRepository.save(userToCreateAccount);
                return "inscriptionOk";
            }else{
                return "pseudoDejaPrit";
            }
        }
        return "emailDejaPrise";
    }

    @RequestMapping(value = "/api/verifmail", method = RequestMethod.POST)
    String forgetPassword (@RequestBody User userToForgetPassword){
        String email = userToForgetPassword.getEmail();
        User usersEmail = userRepository.findByEmail(email);
        if (usersEmail == null){
            return "pasDansBdd";
        }
        return"emailTrouve";
    }

    @RequestMapping(value = "/api/recupquestionsecrete", method = RequestMethod.POST)
    User recuperationSecretQuestion (@RequestBody User userToRecuperationSecretQuestion){
        String email = userToRecuperationSecretQuestion.getEmail();
        return userRepository.findByEmail(email);
    }

    @RequestMapping(value = "/api/verifreponsesecrete", method = RequestMethod.POST)
    String verificationSecretResponse (@RequestBody UserResponse userResponseToVerifySecretResponse){
        String secretResponse = userResponseToVerifySecretResponse.getSecretResponse();
        String email = userResponseToVerifySecretResponse.getEmail();
        List<UserResponse> usersEmail = userResponseRepository.findBySecretResponseAndEmail(secretResponse, email);
        if (usersEmail == null || usersEmail.size() < 1){
            return "PasOkReponse";
        }
        return "ReponseOk";
    }

    @RequestMapping(value = "/api/changementmdpoublie", method = RequestMethod.POST)
    String changePasswordWhenForgotPassword (@RequestBody User userToChangePassword) throws Exception{
        String email = userToChangePassword.getEmail();
        User userChange = userRepository.findByEmail(email);
        String password = userToChangePassword.getPassword();
        String sha1;
        MessageDigest crypt;
        crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        sha1 = byteToHex(crypt.digest());
        userChange.setPassword(sha1);
        userRepository.save(userChange);
        return "PassOk";
    }

    @RequestMapping(value = "/api/compteutilisateur", method = RequestMethod.GET)
    User userConnectedAccount () {
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        Long idMembre = user.getIdMembre();
        Long abonne = subscriptionSubscriberRepository.countByAbonne(idMembre);
        Long abonnement = subscriptionSubscriberRepository.countByMembre(idMembre);
        Long publication = picturePublicationRepository.countByIdMembre(idMembre);
        ProfilPicture urlPicture = profilPictureRepository.findByIdMembrePicture(idMembre);
        List<PicturePublication> picturePublications = picturePublicationRepository.findByIdMembre(idMembre);
        user.setSubscriber(abonne);
        user.setSubscription(abonnement);
        user.setPublication(publication);
        if (urlPicture != null){
            user.setUrlPicture(urlPicture.getUrlPicture());
        }
        user.setPicturePublications(picturePublications);
        RecuperationLike(picturePublications, idMembre);
        return user;
    }

    private void RecuperationLike(List<PicturePublication> picturePublications, Long idMembre) {
        picturePublications.forEach(listPublication -> {
            List<InformationPublication> informationPictureLike = informationPublicationRepository.findByIdPublicationAndIdMembre(listPublication.getIdPublication(), idMembre);
            Long countBylikePictureAndIdPublication = informationPublicationRepository.countBylikePictureAndIdPublication(true, listPublication.getIdPublication());
            listPublication.setNumberLike(countBylikePictureAndIdPublication);
            if (informationPictureLike != null) {
                informationPictureLike.forEach(like -> {
                    Boolean likePicture = like.getLikePicture();
                    listPublication.setLikePicture(likePicture);
                });
            }
        });
    }

    @RequestMapping(value = "api/changementpseudo", method = RequestMethod.POST)
    String changementPseudo(@RequestBody User userToChangePseudo){
        String pseudo = userToChangePseudo.getPseudo();
        User users = userRepository.findByPseudo(pseudo);
        if (users == null){
            Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = utilisateur.getUser();
            user.setPseudo(pseudo);
            userRepository.save(user);
            return "modifie";
        }
        return "DejaPrit";
    }

    @RequestMapping(value = "api/changementbiographie", method = RequestMethod.POST)
    String changementBiography(@RequestBody User userToChangeBiography){
        String biography = userToChangeBiography.getBiography();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        user.setBiography(biography);
        userRepository.save(user);
        return "bioOk";
    }

    @RequestMapping(value = "api/changementmail", method = RequestMethod.POST)
    String changementEmail(@RequestBody User userToChangeEmail){
        String email = userToChangeEmail.getEmail();
        User user = userRepository.findByEmail(email);
        if (user == null){
            Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User users = utilisateur.getUser();
            users.setEmail(email);
            userRepository.save(users);
            return "modifie";
        }
        return "DejaPrit";
    }

    @RequestMapping(value = "api/changementpays", method = RequestMethod.POST)
    String changementCountry(@RequestBody User userToChangeCountry){
        String country = userToChangeCountry.getCountry();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        user.setCountry(country);
        userRepository.save(user);
        return "paysOk";
    }

    @RequestMapping(value = "api/changementmotdepasse", method = RequestMethod.POST)
    String changementPassword(@RequestBody User userToChangePassword) throws Exception{
        String newPassword = userToChangePassword.getPassword();
        String sha1;
        MessageDigest crypt;
        crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(newPassword.getBytes("UTF-8"));
        sha1 = byteToHex(crypt.digest());
        String sha2;
        MessageDigest crypt2;
        crypt2 = MessageDigest.getInstance("SHA-1");
        crypt2.reset();
        crypt2.update(newPassword.getBytes("UTF-8"));
        sha2 = byteToHex(crypt2.digest());
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        String email = user.getEmail();
        User recuperationUser = userRepository.findByPasswordAndEmail(sha2, email);
        if (recuperationUser == null){
            user.setPassword(sha1);
            userRepository.save(user);
            return "change";
        }
        return "PasLeBon";
    }

    @RequestMapping(value = "/api/photoprofil", method = RequestMethod.POST)
    String changementProfilPicture(@RequestBody ProfilPicture userToChangeProfilPicture){
        String picture = userToChangeProfilPicture.getUrlPicture();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        Long idMembre = user.getIdMembre();
        ProfilPicture idMembrePicture = profilPictureRepository.findByIdMembrePicture(idMembre);
        if (idMembrePicture == null){
            idMembrePicture = new ProfilPicture(idMembre, picture);
        }else {
            idMembrePicture.setUrlPicture(picture);
        }
        profilPictureRepository.save(idMembrePicture);
        return "photoOk";
    }

    @RequestMapping(value = "/api/ajouterphoto", method = RequestMethod.POST)
    Long addPicture(@RequestBody PicturePublication picturePublication){
        String pathPicture = picturePublication.getPathPicture();
        String descriptionPicture = picturePublication.getDescription();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        Long idMembre = user.getIdMembre();
        Date date = new Date();
        PicturePublication addPicture = new PicturePublication(idMembre, pathPicture, date ,descriptionPicture);
        picturePublicationRepository.save(addPicture);
        return addPicture.getIdPublication();
    }

    @RequestMapping(value = "/api/rechercheastronome", method = RequestMethod.GET)
    List<User> userForSearchList(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/api/compteutilisateurviarecherche", method = RequestMethod.POST)
    User accountBySearch(@RequestBody User userToFindAccountBySearch){
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User users = utilisateur.getUser();
        Long idConnecter = users.getIdMembre();
        String familyName = userToFindAccountBySearch.getFamilyName();
        String name = userToFindAccountBySearch.getName();
        User user = userRepository.findByNameAndFamilyName(name, familyName);
        Long idMembre = user.getIdMembre();
        ProfilPicture profilPicture = profilPictureRepository.findByIdMembrePicture(idMembre);
        if (profilPicture != null){
            user.setUrlPicture(profilPicture.getUrlPicture());
        }
        List<PicturePublication> publicationList = picturePublicationRepository.findByIdMembre(idMembre);
        user.setPicturePublications(publicationList);
        Long numberPublication = picturePublicationRepository.countByIdMembre(idMembre);
        user.setPublication(numberPublication);
        Long abonneIdMembre = subscriptionSubscriberRepository.countByAbonne(idMembre);
        user.setSubscriber(abonneIdMembre);
        Long membreIdMembre = subscriptionSubscriberRepository.countByMembre(idMembre);
        user.setSubscription(membreIdMembre);
        RecuperationLike(publicationList, idConnecter);
        return user;
    }

    @RequestMapping(value = "/api/ajouepersonnedanscontact", method = RequestMethod.POST)
    String addPersonToContact(@RequestBody User userToAddToContact){
        String pseudo = userToAddToContact.getPseudo();
        User user = userRepository.findByPseudo(pseudo);
        Long recuperationMember = user.getIdMembre();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User users = utilisateur.getUser();
        Long membreConnected = users.getIdMembre();
        SubscriptionSubscriber subscriptionSubscriberRepositoryByAbonneAndMembre = subscriptionSubscriberRepository.findByMembreAndAbonne(membreConnected, recuperationMember);
        if (subscriptionSubscriberRepositoryByAbonneAndMembre == null){
            if (membreConnected.equals(recuperationMember)){
                return "abonnementASoisMeme";
            }else {
                SubscriptionSubscriber subscriptionSubscriber = new SubscriptionSubscriber(membreConnected, recuperationMember);
                subscriptionSubscriberRepository.save(subscriptionSubscriber);
                return "ajouter";
            }
        }
        return "dejaAjouter";
    }

    @RequestMapping(value = "api/supprimerpersonneabonnement", method = RequestMethod.POST)
    String deletePersonSubscription(@RequestBody User userToDeleteSubscritpion){
        return deleteSubscription(userToDeleteSubscritpion);
    }

    @RequestMapping(value = "/api/recupabonneabonnement", method = RequestMethod.POST)
    List<User> recuperationSubscriber (@RequestBody User userToRecuperationSubscriber){
        String pseudo = userToRecuperationSubscriber.getPseudo();
        User subscribers = userRepository.findByPseudo(pseudo);
        Long idMembre = subscribers.getIdMembre();
        List<SubscriptionSubscriber> subscriberRepositoryByMembre = subscriptionSubscriberRepository.findByAbonne(idMembre);
        List<User> userAbonne = new ArrayList<>();
        subscriberRepositoryByMembre.forEach(subscriptionSubscriber -> {
            userAbonne.add(userRepository.findByIdMembre(subscriptionSubscriber.getMembre()));
        });
        return userAbonne;
    }

    @RequestMapping(value = "/api/recupabonnement", method = RequestMethod.POST)
    List<User> recupertionSubscription(@RequestBody User userToRecuperationSubscription){
        String pseudo = userToRecuperationSubscription.getPseudo();
        User subscription = userRepository.findByPseudo(pseudo);
        Long idMembre = subscription.getIdMembre();
        List<SubscriptionSubscriber> subscriptionSubscribers = subscriptionSubscriberRepository.findByMembre(idMembre);
        List<User> usersSubscription = new ArrayList<>();
        subscriptionSubscribers.forEach(subscriptionSubscriber -> {
            usersSubscription.add(userRepository.findByIdMembre(subscriptionSubscriber.getAbonne()));
        });
        return usersSubscription;
    }

    @RequestMapping(value = "/api/compteviaabonne", method = RequestMethod.POST)
    User userBySubscriptionSubscriber(@RequestBody User userToFindAccountBySubscriberSubscription){
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User users = utilisateur.getUser();
        Long idConnecter = users.getIdMembre();
        String pseudo = userToFindAccountBySubscriberSubscription.getPseudo();
        User user = userRepository.findByPseudo(pseudo);
        Long idMembre = user.getIdMembre();
        List<PicturePublication> publicationList = picturePublicationRepository.findByIdMembre(idMembre);
        user.setPicturePublications(publicationList);
        ProfilPicture profilPicture = profilPictureRepository.findByIdMembrePicture(idMembre);
        if (profilPicture != null){
            user.setUrlPicture(profilPicture.getUrlPicture());
        }
        Long numberPublication = picturePublicationRepository.countByIdMembre(idMembre);
        user.setPublication(numberPublication);
        Long abonneIdMembre = subscriptionSubscriberRepository.countByAbonne(idMembre);
        user.setSubscriber(abonneIdMembre);
        Long membreIdMembre = subscriptionSubscriberRepository.countByMembre(idMembre);
        user.setSubscription(membreIdMembre);
        RecuperationLike(publicationList, idConnecter);
        return user;
    }

    @RequestMapping(value = "/api/supprimerpersonneabonnementbypage", method = RequestMethod.POST)
    String userToDeleteSubscription(@RequestBody User userToDeleteSubscritpion){
        return deleteSubscription(userToDeleteSubscritpion);
    }

    private String deleteSubscription(@RequestBody User userToDeleteSubscritpion) {
        String pseudo = userToDeleteSubscritpion.getPseudo();
        User userMembre = userRepository.findByPseudo(pseudo);
        Long idMembreAbonne = userMembre.getIdMembre();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User users = utilisateur.getUser();
        Long idConnecter = users.getIdMembre();
        SubscriptionSubscriber membreAndAbonne = subscriptionSubscriberRepository.findByMembreAndAbonne(idConnecter, idMembreAbonne);
        subscriptionSubscriberRepository.delete(membreAndAbonne);
        return "suppOK";
    }

    @RequestMapping(value = "api/ajoutercommentaire", method = RequestMethod.POST)
    Long userToAddComment(@RequestBody InformationPublication userToAddComment){
        String comment = userToAddComment.getCommentPicture();
        Long idPublication = userToAddComment.getIdPublication();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User users = utilisateur.getUser();
        Long idMembreConnecter = users.getIdMembre();
        InformationPublication informationPublication = new InformationPublication(idPublication, idMembreConnecter, comment, false, false, " ");
        informationPublicationRepository.save(informationPublication);
        return informationPublication.getIdInformationPubication();
    }

    @RequestMapping(value = "api/recuperationimagepath", method = RequestMethod.POST)
    Long userToRecuperationPath(@RequestBody PicturePublication userToRecuperationPath){
        String pathPicture = userToRecuperationPath.getPathPicture();
        PicturePublication picturePublicationRepositoryByPathPicture = picturePublicationRepository.findByPathPicture(pathPicture);
        return picturePublicationRepositoryByPathPicture.getIdPublication();
    }

    @RequestMapping(value = "api/deletepublicationimage", method = RequestMethod.POST)
    String userToDeletePublication(@RequestBody PicturePublication userToDeletePublication){
        Long idPublication = userToDeletePublication.getIdPublication();
        PicturePublication picturePublication = picturePublicationRepository.findByIdPublication(idPublication);
        List<InformationPublication> informationPublications = informationPublicationRepository.findByIdPublication(idPublication);
        Long picturePublicationIdMembre = picturePublication.getIdMembre();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User users = utilisateur.getUser();
        Long idMembreConnecter = users.getIdMembre();
        if (idMembreConnecter.equals(picturePublicationIdMembre) ){
            informationPublications.forEach(information -> informationPublicationRepository.delete(information));
            picturePublicationRepository.delete(picturePublication);
            return "suppOk";
        }
        else{
            return "pasPossible";
        }
    }

    @RequestMapping(value = "api/addlikedislike", method = RequestMethod.POST)
    String userToLikePicture(@RequestBody PicturePublication userToAddLike){
        Long idPublicationPhoto = userToAddLike.getIdPublication();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User users = utilisateur.getUser();
        Long idMembreConnecter = users.getIdMembre();
        InformationPublication publicationRepositoryByIdPublication = informationPublicationRepository.findByIdPublicationAndIdMembreAndLikePicture(idPublicationPhoto, idMembreConnecter, true);
        if (publicationRepositoryByIdPublication == null){
            InformationPublication informationPublication = new InformationPublication(idPublicationPhoto, idMembreConnecter, "", true, false, " ");
            informationPublicationRepository.save(informationPublication);
            return "addOk";
        }else {
            informationPublicationRepository.delete(publicationRepositoryByIdPublication);
            return "deleteOk";
        }
    }

    @RequestMapping(value = "api/recuperationcomment", method = RequestMethod.POST)
    List<InformationPublication> recuperationComment(@RequestBody PicturePublication userToRecuperationPicture){
        Long idPublication = userToRecuperationPicture.getIdPublication();
        PicturePublication picturePublicationRepositoryByPathPicture = picturePublicationRepository.findByIdPublication(idPublication);
        List<InformationPublication> informationPublicationRepositoryByIdPublication = informationPublicationRepository.findByIdPublication(picturePublicationRepositoryByPathPicture.getIdPublication());
        informationPublicationRepositoryByIdPublication.forEach(information -> {
            ProfilPicture profilPictureRepositoryByIdMembrePicture = profilPictureRepository.findByIdMembrePicture(information.getIdMembre());
            information.setProfilPicture(profilPictureRepositoryByIdMembrePicture);
            User userRepositoryByIdMembre = userRepository.findByIdMembre(information.getIdMembre());
            information.setUser(userRepositoryByIdMembre);
            Long numberLike = informationCommentRespository.countByLikeCommentAndIdInformationPublication(true, information.getIdInformationPubication());
            Long numberDislike = informationCommentRespository.countByDislikeCommentAndIdInformationPublication(true, information.getIdInformationPubication());
            information.setNumberLike(numberLike);
            information.setNumberDislike(numberDislike);
        });
        return informationPublicationRepositoryByIdPublication;
    }

    @RequestMapping(value = "api/addlikefromcomment", method = RequestMethod.POST)
    InformationComment addLikeForComment(@RequestBody InformationPublication userToAddLikeForComment){
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User users = utilisateur.getUser();
        Long idMembreConnecter = users.getIdMembre();
        Long idCommentPicture = userToAddLikeForComment.getIdInformationPubication();
        InformationComment membreAndLikeComment = informationCommentRespository.findByIdMembreAndLikeCommentAndIdInformationPublication(idMembreConnecter, true, idCommentPicture);
        Long countLike = informationCommentRespository.countByLikeCommentAndIdInformationPublication(true, idCommentPicture);
        InformationComment recuperation = null;
        Long recupCountMore = countLike + 1;
        if (membreAndLikeComment == null){
            InformationComment informationCommentDislike = informationCommentRespository.findByIdMembreAndDislikeCommentAndIdInformationPublication(idMembreConnecter, true, idCommentPicture);
            if (informationCommentDislike != null){
                informationCommentRespository.delete(informationCommentDislike);
                recuperation = new InformationComment(true, recupCountMore);
            }else {
                recuperation = new InformationComment(false, recupCountMore);
            }
            InformationComment informationComment = new InformationComment(idCommentPicture, idMembreConnecter, true, false);
            informationCommentRespository.save(informationComment);
            return recuperation ;
        }else {
            informationCommentRespository.delete(membreAndLikeComment);
            Long recupCountLess = countLike - 1;
            return new InformationComment(false, recupCountLess);
        }

    }

    @RequestMapping(value = "api/adddisllikefromcomment", method = RequestMethod.POST)
    InformationComment adddisllikefromcomment(@RequestBody InformationPublication userToDislikeForComment){
        Long idCommentPictureDislike = userToDislikeForComment.getIdInformationPubication();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User users = utilisateur.getUser();
        Long idMembreConnecter = users.getIdMembre();
        InformationComment membreAndDislikeComment = informationCommentRespository.findByIdMembreAndDislikeCommentAndIdInformationPublication(idMembreConnecter, true, idCommentPictureDislike);
        Long countDislike = informationCommentRespository.countByDislikeCommentAndIdInformationPublication(true, idCommentPictureDislike);
        InformationComment recuperation = null;
        Long recupCountMoreDislike = countDislike + 1;
        if (membreAndDislikeComment == null ){
            InformationComment informationDislike = informationCommentRespository.findByIdMembreAndLikeCommentAndIdInformationPublication(idMembreConnecter, true, idCommentPictureDislike);
            if (informationDislike != null){
                informationCommentRespository.delete(informationDislike);
                recuperation = new InformationComment(true, recupCountMoreDislike);
            }else {
                recuperation = new InformationComment(false, recupCountMoreDislike);
            }
            InformationComment informationComment = new InformationComment(idCommentPictureDislike, idMembreConnecter, false, true);
            informationCommentRespository.save(informationComment);
            return recuperation;
        } else {
            informationCommentRespository.delete(membreAndDislikeComment);
            Long recupCountLessDislike = countDislike - 1;
            return new InformationComment(false, recupCountLessDislike);
        }
    }

    @RequestMapping(value = "api/supprimercommentaireajouter", method = RequestMethod.POST)
    String deleteCommentAdd(@RequestBody InformationPublication userToIdPublication){
        Long userToIdPublicationIdInformationPublication = userToIdPublication.getIdInformationPubication();
        InformationPublication publicationRepositoryByCommentPicture = informationPublicationRepository.findByIdInformationPubication(userToIdPublicationIdInformationPublication);
        List<InformationComment> informationComment = informationCommentRespository.findByIdInformationPublication(userToIdPublicationIdInformationPublication);
        if (informationComment == null || informationComment.size() < 1){
            informationPublicationRepository.delete(publicationRepositoryByCommentPicture);
        }
        if (informationComment != null) {
            informationComment.forEach(information -> {
                informationCommentRespository.delete(information);
                informationPublicationRepository.delete(publicationRepositoryByCommentPicture);
            });
        }
        return "suppOk";
    }

    @RequestMapping(value = "api/personneconnecter", method = RequestMethod.GET)
    String profilConnecter(){
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        Long idMembre = user.getIdMembre();
        ProfilPicture membrePicture = profilPictureRepository.findByIdMembrePicture(idMembre);
        return membrePicture.getUrlPicture();
    }

    @RequestMapping(value = "api/personneconnecterPseudo", method = RequestMethod.GET)
    String profilConnecterPseudo(){
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        return user.getPseudo();
    }

    @RequestMapping(value = "api/personneconnecterId", method = RequestMethod.GET)
    Long profilConnecterId(){
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        return user.getIdMembre();
    }

    @RequestMapping(value = "api/personneconnecterNomPrenom", method = RequestMethod.GET)
    User profilConnecterNameFamilyName(){
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return utilisateur.getUser();
    }

    @RequestMapping(value = "api/ajouterstatus", method = RequestMethod.POST)
    ProfilPicture addStatus(@RequestBody News userToAddNews){
        String status = userToAddNews.getStatus();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        Long idMembre = user.getIdMembre();
        Date date = new Date();
        List<News> newsRepositoryByIdMembreAndArchive = newsRepository.findByIdMembreAndArchive(idMembre, false);
        if (newsRepositoryByIdMembreAndArchive.size() < 1){
            News newNews = new News(idMembre,status, false,date);
            News save = newsRepository.save(newNews);
            Long idFilActualite = save.getIdFilActualite();
            ProfilPicture findByIdMembrePicture = profilPictureRepository.findByIdMembrePicture(idMembre);
            findByIdMembrePicture.setIdStatus(idFilActualite);
            User userRepositoryByIdMembre = userRepository.findByIdMembre(idMembre);
            String pseudo = userRepositoryByIdMembre.getPseudo();
            findByIdMembrePicture.setPseudo(pseudo);
            return findByIdMembrePicture;
        } else {
            News news = newsRepositoryByIdMembreAndArchive.get(0);
            news.setArchive(true);
            newsRepository.save(news);
            News newNews = new News(idMembre,status, false,date);
            News save = newsRepository.save(newNews);
            Long idFilActualite = save.getIdFilActualite();
            ProfilPicture findByIdMembrePicture = profilPictureRepository.findByIdMembrePicture(idMembre);
            findByIdMembrePicture.setIdStatus(idFilActualite);
            return findByIdMembrePicture;
        }
    }

    @RequestMapping(value = "api/recuperationstatus", method = RequestMethod.GET)
    List<News> recuperationStatus(){
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        Long idMembre = user.getIdMembre();
        List<SubscriptionSubscriber> subscriptionSubscriberRepositoryByMembers = subscriptionSubscriberRepository.findByMembre(idMembre);
        List<News> newsPerson = new ArrayList<>();
        if (subscriptionSubscriberRepositoryByMembers.size() < 1){
            return null;
        }
        else {
            subscriptionSubscriberRepositoryByMembers.forEach(subscriber -> {
                Long abonne = subscriber.getAbonne();
                List<News> idMembreAndArchive = newsRepository.findByIdMembreAndArchive(abonne, false);
                statusPicture(abonne, newsPerson, idMembreAndArchive);
            });
            List<News> byIdMembreAndArchive = newsRepository.findByIdMembreAndArchive(idMembre, false);
            statusPicture(idMembre, newsPerson, byIdMembreAndArchive);
            return newsPerson;
        }
    }

    private void statusPicture(Long idMembre, List<News> newsPerson, List<News> byIdMembreAndArchive) {
        byIdMembreAndArchive.forEach(status ->{
            ProfilPicture profilPicture = profilPictureRepository.findByIdMembrePicture(idMembre);
            Long idFilActualite = status.getIdFilActualite();
            Long countByLikeStatusAndIdFilActualite = informationNewsRepository.countByLikeStatusAndIdFilActualite(true, idFilActualite);
            User pseudoByIdMember = userRepository.findByIdMembre(idMembre);
            String pseudo = pseudoByIdMember.getPseudo();
            status.setPseudo(pseudo);
            status.setNbLikeStatus(countByLikeStatusAndIdFilActualite);
            String urlPictureConnecter = profilPicture.getUrlPicture();
            status.setUrlPicture(urlPictureConnecter);
            newsPerson.add(status);
        });
    }

    @RequestMapping(value = "api/archiverstatus", method = RequestMethod.POST)
    String achiveStatus(@RequestBody News userToAchiveStatus){
        Long idFilActualite = userToAchiveStatus.getIdFilActualite();
        News repositoryByIdFilActualite = newsRepository.findByIdFilActualite(idFilActualite);
        repositoryByIdFilActualite.setArchive(true);
        newsRepository.save(repositoryByIdFilActualite);
        return "archiver";
    }

    @RequestMapping(value = "api/supprimerstatus", method = RequestMethod.POST)
    String deleteStatus(@RequestBody News userToDeleteStatus){
        Long idFilActualite = userToDeleteStatus.getIdFilActualite();
        News repositoryByIdFilActualite = newsRepository.findByIdFilActualite(idFilActualite);
        List<InformationNews> informationNewsRepositoryByIdFilActualite = informationNewsRepository.findByIdFilActualite(idFilActualite);
        if (informationNewsRepositoryByIdFilActualite.size() != 0){
            informationNewsRepositoryByIdFilActualite.forEach(informationNews -> informationNewsRepository.delete(informationNews));
        }
        newsRepository.delete(repositoryByIdFilActualite);
        return "supprimer";
    }

    @RequestMapping(value = "api/likedislikestatus", method = RequestMethod.POST)
    String addLikeStatus(@RequestBody News userToAddLikeStatus){
        Long idFilActualite = userToAddLikeStatus.getIdFilActualite();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        Long idMembre = user.getIdMembre();
        InformationNews informationNewsRepositoryByIdFilActualiteAndIdMember = informationNewsRepository.findByIdFilActualiteAndIdMemberAndLikeStatus(idFilActualite, idMembre, true);
        if (informationNewsRepositoryByIdFilActualiteAndIdMember == null){
            Date date = new Date();
            InformationNews informationNews = new InformationNews(idFilActualite, idMembre, true, date);
            informationNewsRepository.save(informationNews);
            return "like";
        }else {
            informationNewsRepository.delete(informationNewsRepositoryByIdFilActualiteAndIdMember);
            return "dislike";
        }

    }

    @RequestMapping(value = "api/ajouecommentairestatus", method = RequestMethod.POST)
    String addCommentForStatus(@RequestBody InformationNews userToAddCommentForStatus){
        Long idFilActualite = userToAddCommentForStatus.getIdFilActualite();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        Long idMembre = user.getIdMembre();
        String commentStatus = userToAddCommentForStatus.getCommentStatus();
        Date date = new Date();
        InformationNews informationNews = new InformationNews(idFilActualite, idMembre, false, commentStatus, date);
        informationNewsRepository.save(informationNews);
        return "addOk";
    }


    @RequestMapping(value = "api/recuperationcommentstatus", method = RequestMethod.POST)
    List<InformationNews> recuperationCommentForStatus(@RequestBody InformationNews userToGetCommentStatus){
        Long idFilActualite = userToGetCommentStatus.getIdFilActualite();
        List<InformationNews> informationNewsRepositoryByIdFilActualite = informationNewsRepository.findByIdFilActualite(idFilActualite);
        informationNewsRepositoryByIdFilActualite.forEach(comment -> {
            ProfilPicture profilPictureRepositoryByIdMembrePicture = profilPictureRepository.findByIdMembrePicture(comment.getIdMember());
            String urlPicture = profilPictureRepositoryByIdMembrePicture.getUrlPicture();
            comment.setUrlPhoto(urlPicture);
            User userRepositoryByIdMembre = userRepository.findByIdMembre(comment.getIdMember());
            String pseudo = userRepositoryByIdMembre.getPseudo();
            comment.setPseudo(pseudo);
            Long countByLikeStatusAndIdFilActualite = informationNewsRepository.countByLikeStatusAndIdFilActualite(true, idFilActualite);
            comment.setNumberLike(countByLikeStatusAndIdFilActualite);
        });
        return informationNewsRepositoryByIdFilActualite;
    }

    @RequestMapping(value = "api/supprimercommentaireajouterstatus", method = RequestMethod.POST)
    String deleteCommentStatus(@RequestBody InformationNews userToDeleteCommentStatus){
        Long idFilActualite = userToDeleteCommentStatus.getIdFilActualite();
        InformationNews findByIdFilActualite = informationNewsRepository.findByIdInformationFilActualite(idFilActualite);
        informationNewsRepository.delete(findByIdFilActualite);
        return "suppOk";
    }

    @RequestMapping(value = "api/ajoutercommentairestatus", method = RequestMethod.POST)
    Long addCommentForStatusComment(@RequestBody InformationNews userToAddNewComment){
        Long idFilActualite = userToAddNewComment.getIdFilActualite();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        Long idMembre = user.getIdMembre();
        String commentStatus = userToAddNewComment.getCommentStatus();
        Date date = new Date();
        InformationNews informationNews = new InformationNews(idFilActualite, idMembre, false, commentStatus, date);
        InformationNews save = informationNewsRepository.save(informationNews);
        return save.getIdInformationFilActualite();
    }

    @RequestMapping(value = "api/recuperationppublicationwhithabonnement", method = RequestMethod.GET)
    List<PicturePublication> recuperationPublicationWithSubscription(){
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        Long idMembre = user.getIdMembre();
        List<SubscriptionSubscriber> subscriptionSubscriberRepositoryByMembre = subscriptionSubscriberRepository.findByMembre(idMembre);
        List<PicturePublication> picturePublications = new ArrayList<>();
        if(subscriptionSubscriberRepositoryByMembre.size() != 0){
            subscriptionSubscriberRepositoryByMembre.forEach(subscriber -> {
                Long abonne = subscriber.getAbonne();
                List<PicturePublication> picturePublicationRepositoryByIdMembre = picturePublicationRepository.findByIdMembre(abonne);
                if (picturePublicationRepositoryByIdMembre.size() != 0){
                    int size = picturePublicationRepositoryByIdMembre.size();
                    Long countIdPublication = informationPublicationRepository.countBylikePictureAndIdPublication(true, picturePublicationRepositoryByIdMembre.get(size - 1).getIdPublication());
                    User byIdMembre = userRepository.findByIdMembre(abonne);
                    String pseudo = byIdMembre.getPseudo();
                    picturePublicationRepositoryByIdMembre.get(size - 1).setPseudo(pseudo);
                    picturePublicationRepositoryByIdMembre.get(size -1).setNumberLike(countIdPublication);
                    picturePublications.add(picturePublicationRepositoryByIdMembre.get(size - 1));
                }
            });
        }
        return picturePublications;
    }

    @RequestMapping(value = "api/ajouteraimephotopubliefilactu", method = RequestMethod.POST)
    String addLikePictureNews(@RequestBody PicturePublication userToAddLikePictureNews){
        Long idPublication = userToAddLikePictureNews.getIdPublication();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        Long idMembre = user.getIdMembre();
        InformationPublication publicationByIdPublicationAndIdMembreAndLikePicture = informationPublicationRepository.findByIdPublicationAndIdMembreAndLikePicture(idPublication, idMembre, true);
        if(publicationByIdPublicationAndIdMembreAndLikePicture == null){
            InformationPublication informationPublication = new InformationPublication(idPublication, idMembre, true);
            informationPublicationRepository.save(informationPublication);
            return "addOk";
        }else {
            informationPublicationRepository.delete(publicationByIdPublicationAndIdMembreAndLikePicture);
            return "supprOk";
        }
    }

    @RequestMapping(value = "api/photoplusaime", method = RequestMethod.GET)
    InformationPublication pictureMoreLike(){
        List<InformationPublication> informationPublications = informationPublicationRepository.findByLikePicture(true);
        Long maximum = 0L;
        int indice = 0;
        List<InformationPublication> picturePublications = new ArrayList<>();
        informationPublications.forEach(information -> {
            Long aLong = informationPublicationRepository.countBylikePictureAndIdPublication(information.getLikePicture(), information.getIdPublication());
            information.setNumberLike(aLong);
            PicturePublication picturePublicationRepositoryByIdPublication = picturePublicationRepository.findByIdPublication(information.getIdPublication());
            information.setPicturePublication(picturePublicationRepositoryByIdPublication);
            User userRepositoryByIdMembre = userRepository.findByIdMembre(information.getPicturePublication().getIdMembre());
            information.setUser(userRepositoryByIdMembre);
            picturePublications.add(information);
        });
        for (int i = 0; i < picturePublications.size(); i ++){
            if (picturePublications.get(i).getNumberLike() > maximum ){
                maximum = picturePublications.get(i).getNumberLike();
                indice = i;
            }
        }
        return picturePublications.get(indice);
    }

    @RequestMapping(value = "api/profilplussuivi", method = RequestMethod.GET)
    User recuperationProfilMoreFollow(){
        List<User> userRepositoryAll = userRepository.findAll();
        Long maximum = 0L;
        int indice = 0;
        List<User> users = new ArrayList<>();
        userRepositoryAll.forEach(user -> {
            Long countByAbonne = subscriptionSubscriberRepository.countByAbonne(user.getIdMembre());
            if (countByAbonne != null){
                user.setSubscriber(countByAbonne);
            }
            ProfilPicture profilPictureRepositoryByIdMembrePicture = profilPictureRepository.findByIdMembrePicture(user.getIdMembre());
            if (profilPictureRepositoryByIdMembrePicture != null){
                user.setUrlPicture(profilPictureRepositoryByIdMembrePicture.getUrlPicture());
            }
            Long countByIdMembre = picturePublicationRepository.countByIdMembre(user.getIdMembre());
            if (countByIdMembre != null){
                user.setPublication(countByIdMembre);
            }
            users.add(user);
        });
        for (int i = 0; i < users.size(); i ++){
            if (users.get(i).getSubscriber() > maximum && users.get(i).getSubscriber() != null){
                maximum = users.get(i).getSubscriber();
                indice = i;
            }
        }
        return users.get(indice);
    }

    @RequestMapping(value = "api/signalementphoto", method = RequestMethod.POST)
    String signalPicture(@RequestBody InformationPublication userToSignalPicture){
        Long idPublication = userToSignalPicture.getIdPublication();
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        Long idMembre = user.getIdMembre();
        String reasonSignal = userToSignalPicture.getReasonSignal();
        InformationPublication informationPublicationRepositoryBySignalPictureAndIdMembreAndIdPublication = informationPublicationRepository.findBySignalPictureAndIdMembreAndIdPublication(true, idMembre, idPublication);
        if (informationPublicationRepositoryBySignalPictureAndIdMembreAndIdPublication != null) {
            return "dejasignaler";
        } else {
            InformationPublication informationPublication = new InformationPublication(idPublication, idMembre, true, reasonSignal);
            informationPublicationRepository.save(informationPublication);
            return "signalement";
        }
    }

    @RequestMapping(value = "api/recuperationverrifiercompte", method = RequestMethod.GET)
    String recuperationAccountVerification(){
        Actor utilisateur = (Actor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = utilisateur.getUser();
        Long idMembre = user.getIdMembre();
        User userRepositoryByVerifyAccountAndIdMembre = userRepository.findByVerifyAccountAndIdMembre(true, idMembre);
        if (userRepositoryByVerifyAccountAndIdMembre != null) {
            return "oui";
        } else {
            return "non";
        }
    }

    @RequestMapping(value = "api/connectionespacetelescope", method = RequestMethod.POST)
    String connectionEspaceTelescope(@RequestBody CodeRaspberry userToRecuperationCodeRaspberry){
        Long codeRaspberry = userToRecuperationCodeRaspberry.getCodeRaspberry();
        String ownerName = userToRecuperationCodeRaspberry.getOwnerName();
        CodeRaspberry codeRaspberryRepositoryByOwnerNameAndCodeRaspberry = codeRaspberryRepository.findByOwnerNameAndCodeRaspberry(ownerName, codeRaspberry);
        if (codeRaspberryRepositoryByOwnerNameAndCodeRaspberry != null){
            codeRaspberryRepositoryByOwnerNameAndCodeRaspberry.setActive(true);
            codeRaspberryRepository.save(codeRaspberryRepositoryByOwnerNameAndCodeRaspberry);
            User pseudo = userRepository.findByPseudo(ownerName);
            pseudo.setVerifyAccount(true);
            userRepository.save(pseudo);
            return "connecter";
        }else {
            return "pasdansbase";
        }
    }
}