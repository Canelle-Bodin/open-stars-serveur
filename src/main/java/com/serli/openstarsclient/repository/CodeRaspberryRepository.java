package com.serli.openstarsclient.repository;

import com.serli.openstarsclient.data.CodeRaspberry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRaspberryRepository  extends JpaRepository<CodeRaspberry, Long> {

    /**
     * @param name name of telescope owner
     * @param codeRasp code Raspberry provided
     * @return a class codeRaspberry
     */
    CodeRaspberry findByOwnerNameAndCodeRaspberry(String name, Long codeRasp);
}
