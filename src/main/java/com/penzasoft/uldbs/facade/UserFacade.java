/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.facade;

import com.penzasoft.uldbs.dto.LoginResult;
import com.penzasoft.uldbs.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ktepin
 */
@Stateless
public class UserFacade {
    
    @PersistenceContext(unitName = "testPU")
    private EntityManager entityManager;
    
    public LoginResult doLogin(String login, String password){
            List<User> found = entityManager
                 .createQuery("SELECT u FROM User u WHERE u.email=:p_email", User.class)
                 .setParameter("p_email", login)
                .getResultList();
            
            if(found.size() > 0 && found.get(0).getPass().equals( password)){
                return new LoginResult(true,"token", found.get(0));
            }
            else{
                 return new LoginResult(false,"NotFound",null);
            }
    }
}
