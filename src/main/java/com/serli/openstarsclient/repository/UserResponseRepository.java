package com.serli.openstarsclient.repository;


import com.serli.openstarsclient.data.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {

    /**
     * @param secretResponse secretResponse of user
     * @param email email of user
     * @return a list of userResponse
     */
    List<UserResponse> findBySecretResponseAndEmail(String secretResponse, String email);
}
