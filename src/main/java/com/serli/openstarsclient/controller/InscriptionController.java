package com.serli.openstarsclient.controller;

import com.serli.openstarsclient.data.Rasp;
import com.serli.openstarsclient.data.RaspUser;
import com.serli.openstarsclient.data.User;
import com.serli.openstarsclient.repository.RaspRepository;
import com.serli.openstarsclient.repository.RaspUserRepository;
import com.serli.openstarsclient.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.serli.openstarsclient.security.SHA1PasswordEncoder.byteToHex;

@RestController
@CrossOrigin(origins = "http://192.168.86.105:8082")
@RequestMapping("/identification")
public class InscriptionController {

    @Autowired
    public UserRepository membreRepo;

    @Autowired
    public RaspRepository raspRepo;

    @Autowired
    public RaspUserRepository raspUserRepo;

    @RequestMapping(method = RequestMethod.POST, value = "/check", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String checkInscrit(@RequestBody User user) {
        UUID id = UUID.randomUUID();
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        Rasp rasp = new Rasp();
        rasp.setRaspId(id.toString());
        List<String> listeId = new ArrayList<String>();
        listeId.add(id.toString());

        String sha1 = user.getPassword();
        MessageDigest crypt;
        try {
            crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(user.getPassword().getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
            System.out.println(sha1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("adresse mail " + user.getEmail());
        System.out.println("Mot de passe crypté : " + sha1);

//        Membre membre = membreRepo.findByMail(user.getMail());
        User membre = membreRepo.findByPasswordAndEmail(user.getEmail(), sha1);

        if (membre == null) {
            return "Erreur lors de la saisie de l'identifiant ou du mot de passe";
        }

        if (membre.getRasps() == null) {
            membre.setRasps(new ArrayList<>());
        }

        RaspUser raspUser = raspUserRepo.findByRaspIdMembre(membre.getIdMembre());

        if (raspUser != null) {
            System.out.println("L'utilisateur existe possède déjà un ID rasp");
            return "ID déjà saisie";
        } else {
            System.out.println("Génération d'un ID pour ce nouvelle utilisateur");
            rasp = raspRepo.save(rasp);
            membre.getRasps().add(rasp);
            membreRepo.save(membre);
            return id.toString();
        }
    }
}
