package com.serli.openstarsclient.repository;

import com.serli.openstarsclient.data.InformationPlanete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationPlaneteRepository extends JpaRepository<InformationPlanete, Long> {

    /**
     * @param idInformationPlanete
     * @return class InformationPlanete
     */
    InformationPlanete findByIdInformationPlanete(Long idInformationPlanete);

    /**
     * @param nomPlanete
     * @return class InformationPlanete
     */
    InformationPlanete findByNomPlanete(String nomPlanete);

    InformationPlanete findAllByNomPlanete(String nomPlanete);
}