package com.serli.openstarsclient.repository;

import com.serli.openstarsclient.data.PicturePublication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PicturePublicationRepository extends JpaRepository<PicturePublication, Long> {

    /**
     * @param idMembre person id
     * @return number of publication published
     */
    Long countByIdMembre(Long idMembre);

    /**
     * @param idMembre person id
     * @return a list of picturePublication
     */
    List<PicturePublication> findByIdMembre(Long idMembre);

    /**
     * @param idMembre person id
     * @return class picturePublication
     */
    PicturePublication findByPathPicture(String idMembre);

    /**
     * @param idpublication publication id
     * @return class picturePublication
     */
    PicturePublication findByIdPublication(Long idpublication);

}
