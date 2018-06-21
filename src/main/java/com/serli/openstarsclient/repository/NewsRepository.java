package com.serli.openstarsclient.repository;

import com.serli.openstarsclient.data.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    /**
     * @param idMembre person id
     * @param archive if achive is true or false
     * @return a list of news
     */
    List<News> findByIdMembreAndArchive(Long idMembre, Boolean archive);

    /**
     * @param idFilActualite new id
     * @return class news
     */
    News findByIdFilActualite(Long idFilActualite);
}
