package com.serli.openstarsclient.repository;

import com.serli.openstarsclient.data.Rasp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaspRepository extends CrudRepository<Rasp, String> {

    /**
     * @param id
     * @return
     */
    Rasp findByRaspId(Long id);

}
