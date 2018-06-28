package com.serli.openstarsclient.repository;

import com.serli.openstarsclient.data.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * @param email person email
	 * @return class user
	 */
	User findByEmail(String email);

	/**
	 * @param pseudo pseudo person
	 * @return class user
	 */
	User findByPseudo(String pseudo);

	/**
	 * @param password pseudo person
	 * @param email email person
	 * @return class user
	 */
	User findByPasswordAndEmail(String password, String email);

	/**
	 * @param name person name
	 * @param familyName person family name
	 * @return class user
	 */
	User findByNameAndFamilyName(String name, String familyName);

	/**
	 * @param idMembre person id
	 * @return class user
	 */
	User findByIdMembre(Long idMembre);


	/**
	 * @param account if account is verified or not
	 * @param idmembre member id
	 * @return a user
	 */
	User findByVerifyAccountAndIdMembre(Boolean account, Long idmembre);

}