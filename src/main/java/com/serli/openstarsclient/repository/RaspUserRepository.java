package com.serli.openstarsclient.repository;

import com.serli.openstarsclient.data.RaspUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaspUserRepository extends CrudRepository<RaspUser, Integer> {

    /**
     * @param id
     * @return Rasp
     */
    RaspUser findByRaspIdMembre(Long id);
}
