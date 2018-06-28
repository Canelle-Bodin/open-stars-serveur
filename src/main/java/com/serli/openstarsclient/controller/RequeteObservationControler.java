package com.serli.openstarsclient.controller;

import com.serli.openstarsclient.data.Coordonnees;
import com.serli.openstarsclient.data.Etat;
import com.serli.openstarsclient.data.Image;
import com.serli.openstarsclient.repository.CoordonneeRepository;
import com.serli.openstarsclient.repository.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://192.168.86.105:3000")
@RequestMapping("/obs")
public class RequeteObservationControler {

    @Autowired
    public CoordonneeRepository coordonneeRepo;

    @Autowired
    public ImageRepository imageRepo;

    @Autowired
    public WebSocketController webSocketController;

    @RequestMapping(method = RequestMethod.GET, value = "/check")
    public List<Coordonnees> checkRequest ()
    {
        List<Coordonnees> pasTraitee = coordonneeRepo.findByEtat(Etat.PASTRAITEE);
        List<Coordonnees> enCours = coordonneeRepo.findByEtat(Etat.ACCEPTEE);
        enCours.addAll(pasTraitee);
        return enCours;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/image/{idImage}")
    public String getImage(@PathVariable("idImage") Integer id)
    {
//        Image one = imageRepo.findOne(id);
        Image one = imageRepo.findById(id).orElse(null);
        return one.getImageBase64();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/check/{id}")
    public Coordonnees updateEtat(@PathVariable("id") Integer id)
    {
//        Coordonnees coord = coordonneeRepo.findOne(id);
        Coordonnees coord = coordonneeRepo.findById(id).orElse(null);
        Image imagePlanete = imageRepo.findFirstByNomPlanete(coord.getNomPlanete());
        coord.setEtat(Etat.TRAITEE);

        coordonneeRepo.save(coord);

        return coord;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/accept/{id}")
    public Coordonnees accept(@PathVariable("id") Integer id)
    {
//        Coordonnees coord = coordonneeRepo.findOne(id);
        Coordonnees coord = coordonneeRepo.findById(id).orElse(null);
        Image imagePlanete = imageRepo.findFirstByNomPlanete(coord.getNomPlanete());
        coord.setEtat(Etat.ACCEPTEE);

        coordonneeRepo.save(coord);

        webSocketController.onMessageReceive(coord.getNomPlanete());

        return coord;
    }

}
