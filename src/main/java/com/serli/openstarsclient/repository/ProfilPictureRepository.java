package com.serli.openstarsclient.repository;

import com.serli.openstarsclient.data.ProfilPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Blob;
import java.util.Base64;

public interface ProfilPictureRepository extends JpaRepository<ProfilPicture, Long> {

    /**
     * @param idMembre person id
     * @return class profilPicture
     */
    ProfilPicture findByIdMembrePicture(Long idMembre);
}
