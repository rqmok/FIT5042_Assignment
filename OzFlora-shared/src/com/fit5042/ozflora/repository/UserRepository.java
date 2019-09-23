/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.repository;

import com.fit5042.ozflora.auth.entities.User;
import javax.ejb.Remote;

/**
 *
 * @author Zeeshan
 */
@Remote
public interface UserRepository {

    /**
     * Create a given {@link User} in the database.
     *
     * @param user The {@link User} to create.
     * @return The created {@link User}.
     * @throws java.lang.Exception
     */
    User createUser(User user) throws Exception;

    /**
     * Find a {@link User} given their ID.
     *
     * @param id The ID of the {@link User} to find.
     * @return The found {@link User}, and null if not found.
     * @throws java.lang.Exception
     */
    User findUserById(String id) throws Exception;
}
