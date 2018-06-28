package com.serli.openstarsclient.repository;

import com.serli.openstarsclient.data.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image,Integer> {

    /**
     * @param nomPlanete
     * @return first image for planete x
     */
    Image findFirstByNomPlanete(String nomPlanete);
}
