/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.facade;

import com.penzasoft.uldbs.model.Feedback;
import com.penzasoft.uldbs.model.Good;
import com.penzasoft.uldbs.model.User;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ktepin
 */
@Stateless
public class FeedbackFacade {
    @PersistenceContext(unitName = "testPU")
    private EntityManager entityManager;
    
     private static final Logger logger = Logger.getLogger(FeedbackFacade.class.getName());
    
    public Boolean addFeedback(Feedback feedback, UUID user, UUID good){
        feedback.setUuid(UUID.randomUUID());
        feedback.setUserUuid(new User(user));
        feedback.setGoodUuid(new Good(good));
         try{
            entityManager.persist(feedback);
            entityManager.flush();
            return true;
        }
        catch(Exception e){
            //result.setMessage(result.getMessage()+": "+e.getMessage());
            logger.log(Level.SEVERE, e.getMessage(),e);
            return false;
        }
    }
    
     public List<Feedback> getAllFeedbacks(int limit, int offset){
        return entityManager.createQuery(
               "SELECT ff FROM Feedback ff", Feedback.class)
               .setMaxResults(limit - offset)
               .setFirstResult(offset)
               .getResultList();
    }
}
