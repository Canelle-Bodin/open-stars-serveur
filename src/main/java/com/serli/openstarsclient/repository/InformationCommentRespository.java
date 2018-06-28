package com.serli.openstarsclient.repository;

import com.serli.openstarsclient.data.InformationComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InformationCommentRespository extends JpaRepository<InformationComment, Long> {

    /**
     * @param idMembre person id
     * @param likeComment if person like the comment
     * @param informationPublication publication id
     * @return class informationComment
     */
    InformationComment findByIdMembreAndLikeCommentAndIdInformationPublication(Long idMembre, Boolean likeComment, Long informationPublication);

    /**
     * @param idMembre person id
     * @param dislikeComment if personne dislike the comment
     * @param informationPublication publication id
     * @return class informationComment
     */
    InformationComment findByIdMembreAndDislikeCommentAndIdInformationPublication(Long idMembre, Boolean dislikeComment, Long informationPublication);

    /**
     * @param likeComment if like is true or false
     * @param informationPublication publication id
     * @return number of like for picture
     */
    Long countByLikeCommentAndIdInformationPublication(Boolean likeComment, Long informationPublication);

    /**
     * @param dislikeComment if dislike is true or false
     * @param informationPublication publication id
     * @return number of dislike
     */
    Long countByDislikeCommentAndIdInformationPublication(Boolean dislikeComment, Long informationPublication);

    /**
     * @param informationPublication publication id
     * @return a list of informationComment
     */
    List<InformationComment> findByIdInformationPublication(Long informationPublication);
}
