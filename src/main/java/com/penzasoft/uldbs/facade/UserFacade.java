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
            /* Нативным запросом 
             List<User> found = entityManager
                     .createNativeQuery("SELECT * FROM public.user u WHERE u.email = ?", User.class)
                      .setParameter(1, login)
                       .getResultList();
            */
             List<User> found2 = entityManager
                 .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
             
            if(found.isEmpty()){
                 return new LoginResult(false,"NotFound");
            }
            else{
                return new LoginResult(true, found.get(0).getEmail());
            }
           
    }
}
