package com.serli.openstarsclient.repository;

import com.serli.openstarsclient.data.InformationNews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InformationNewsRepository extends JpaRepository<InformationNews, Long> {

    /**
     * @param like if like picture is true
     * @param filActu news id
     * @return number of like for status
     */
    Long countByLikeStatusAndIdFilActualite(Boolean like, Long filActu);

    /**
     * @param idFilActu news id
     * @param idMember member id
     * @param likeStatus if like is true
     * @return class information news
     */
    InformationNews findByIdFilActualiteAndIdMemberAndLikeStatus(Long idFilActu, Long idMember, Boolean likeStatus);

    /**
     * @param idFilActu news id
     * @param idMember member id
     * @return class information news
     */
    InformationNews findByIdFilActualiteAndIdMember(Long idFilActu, Long idMember);

    /**
     * @param news id news
     * @return a list of informationNews
     */
    List<InformationNews> findByIdFilActualite(Long news);

    /**
     * @param idFilActualite news id
     * @return class information news
     */
    InformationNews findByIdInformationFilActualite(Long idFilActualite);
}
