package com.serli.openstarsclient.repository;

import com.serli.openstarsclient.data.Coordonnees;
import com.serli.openstarsclient.data.Etat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordonneeRepository extends CrudRepository<Coordonnees,Integer> {

    /**
     * @param etat
     * @return Coordonnees where etat = 0
     */
    List<Coordonnees> findByEtat(Etat etat);

    /**
     * @param etat
     * @return nember of coordonnees
     */
    Integer countByEtat(Etat etat);

    /**
     * @param nom
     * @return name of planete
     */
    Coordonnees findByNomPlanete(String nom);
}
