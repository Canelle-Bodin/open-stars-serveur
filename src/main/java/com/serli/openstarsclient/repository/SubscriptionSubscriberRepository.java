package com.serli.openstarsclient.repository;

import com.serli.openstarsclient.data.SubscriptionSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionSubscriberRepository extends JpaRepository<SubscriptionSubscriber, Long> {

    /**
     * @param idMembre person id
     * @return number of subscriber
     */
    Long countByAbonne(Long idMembre);

    /**
     * @param idMembre person id
     * @return number of subscription
     */
    Long countByMembre(Long idMembre);

    /**
     * @param membre person id
     * @param abonne person id
     * @return class subscritionSubscriber
     */
    SubscriptionSubscriber findByMembreAndAbonne(Long membre, Long abonne);

    /**
     * @param membre person id
     * @return a list of subscritionSubscriber
     */
    List<SubscriptionSubscriber> findByAbonne(Long membre);

    /**
     * @param membre person id
     * @return a list of subscritionSubscriber
     */
    List<SubscriptionSubscriber> findByMembre(Long membre);
}
