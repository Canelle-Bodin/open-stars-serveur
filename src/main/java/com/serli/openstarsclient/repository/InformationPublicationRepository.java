package com.serli.openstarsclient.repository;

import com.serli.openstarsclient.data.InformationPublication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InformationPublicationRepository extends JpaRepository<InformationPublication, Long> {

    /**
     * @param idPublication publication id
     * @return a list of informationPublication
     */
    List<InformationPublication> findByIdPublication(Long idPublication);

    /**
     * @param idPublication publication id
     * @param idMembre person id
     * @return a list of informationPublication
     */
    List<InformationPublication> findByIdPublicationAndIdMembre(Long idPublication, Long idMembre);

    /**
     * @param likePicture if like is true or false
     * @param idPicture picture id
     * @return number of how many like the picture
     */
    Long countBylikePictureAndIdPublication(Boolean likePicture, Long idPicture);

    /**
     * @param idInformationPicture information picture id
     * @return class informationPublication
     */
    InformationPublication findByIdInformationPubication(Long idInformationPicture);

    /**
     * @param idPublication publication id
     * @param idMembre person id
     * @param likePicture if like is true or false
     * @return class informationPublication
     */
    InformationPublication findByIdPublicationAndIdMembreAndLikePicture(Long idPublication, Long idMembre, Boolean likePicture);


    /**
     * @param likePicture if like picture is equal true or false
     * @return a list of information publication
     */
    List<InformationPublication> findByLikePicture(Boolean likePicture);

    /**
     * @param signalement if signal is true or false
     * @param idMembre member id
     * @param idPublicaton publication id
     * @return
     */
    InformationPublication findBySignalPictureAndIdMembreAndIdPublication(Boolean signalement, Long idMembre, Long idPublicaton);
}
