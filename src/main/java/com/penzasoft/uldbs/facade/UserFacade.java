/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.facade;

import com.penzasoft.uldbs.dto.LoginResult;
import com.penzasoft.uldbs.dto.RegisterResult;
import com.penzasoft.uldbs.model.User;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ktepin
 */
@Stateless
public class UserFacade {
    
     private static final Logger logger = Logger.getLogger(UserFacade.class.getName());
    
    @PersistenceContext(unitName = "testPU")
    private EntityManager entityManager;
    
    public LoginResult doLogin(String login, String password){
            List<User> found = entityManager
                 .createQuery("SELECT u FROM User u WHERE u.email = :p_email", User.class)
                 .setParameter("p_email", login)
                .getResultList();
            
            if(found.size() > 0 && found.get(0).getPass().equals( password)){
                return new LoginResult(true,"OK","token", found.get(0));
            }
            else{
                 return new LoginResult(false,"NotFound", "",null);
            }
    }
    
    public RegisterResult register(User regdata){
        RegisterResult result = new RegisterResult(false,"Server error",null);
        try{
            Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM public.user u2 WHERE u2.email = ?");
            query.setParameter(1, regdata.getEmail());
            int count = ((Number) query.getSingleResult()).intValue();
            if(count > 0){
                result.setResult(false);
                result.setMessage("User with same email already exists");
            }
            else{
                UUID newUsrUuid = UUID.randomUUID();
                regdata.setUuid(newUsrUuid);
                regdata.setRole((short)1);
                
                entityManager.persist(regdata);
                entityManager.flush();
                
                result.setResult(true);
                result.setMessage("OK");
                result.setCreatedAccount(regdata);
            }
            return null;
        }
        catch(Exception e){
            //result.setMessage(result.getMessage()+": "+e.getMessage());
            logger.log(Level.SEVERE, e.getMessage(),e);
        }
        finally{
            return result;
        }
    }
    
    public List<User> getAllUsers(int limit, int offset){
        return entityManager.createQuery(
               "SELECT uu FROM User uu WHERE uu.role = 1", User.class)
               .setMaxResults(limit - offset)
               .setFirstResult(offset)
               .getResultList();
    }
    
    public List<User> getAllManagers(int limit, int offset){
          return entityManager.createQuery(
               "SELECT uu FROM User uu WHERE uu.role > 1", User.class)
               .setMaxResults(limit - offset)
               .setFirstResult(offset)
               .getResultList();
    }
}
