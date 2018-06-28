package com.serli.openstarsclient.controller;

import com.serli.openstarsclient.data.InformationPlanete;
import com.serli.openstarsclient.data.PicturePublication;
import com.serli.openstarsclient.data.User;
import com.serli.openstarsclient.repository.InformationPlaneteRepository;
import com.serli.openstarsclient.repository.InformationPublicationRepository;
import com.serli.openstarsclient.repository.PicturePublicationRepository;
import com.serli.openstarsclient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class InformationController {

    @Autowired
    private InformationPlaneteRepository informationPlaneteRepository;

    @Autowired
    private PicturePublicationRepository picturePublicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InformationPublicationRepository informationPublicationRepository;

    @RequestMapping(value = "api/getinfoplanete", method = RequestMethod.POST)
    InformationPlanete infoPlanete(@RequestBody InformationPlanete nomPlanete) {
        String nom = nomPlanete.getNomPlanete();
        return informationPlaneteRepository.findByNomPlanete(nom);
    }

    @RequestMapping(value = "api/getpicturesplanete", method = RequestMethod.POST)
    List<PicturePublication> imagesDePlanete(@RequestBody PicturePublication nomPlanete) {
        String nom = nomPlanete.getPlanete();
        List<PicturePublication> pictures = picturePublicationRepository.findByPlanete(nom);
        for (PicturePublication picture : pictures) {
            Long idMembre = picture.getIdMembre();
            User user = userRepository.findByIdMembre(idMembre);
            String pseudo = user.getPseudo();
            picture.setPseudo(pseudo);

            Long idPublication = picture.getIdPublication();
            Long nbLike = informationPublicationRepository.countBylikePictureAndIdPublication(true, idPublication);
            picture.setNumberLike(nbLike);
        }
        return pictures;
    }


}
