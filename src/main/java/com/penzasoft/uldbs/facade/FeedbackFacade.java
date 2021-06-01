/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.facade;

import com.penzasoft.uldbs.model.Feedback;
import com.penzasoft.uldbs.model.Good;
import com.penzasoft.uldbs.model.User;
import java.util.Date;
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
        try{
            feedback.setUuid(UUID.randomUUID());
            User u = entityManager.find(User.class, user);
            Good g = entityManager.find(Good.class, good);
            if(u == null || g == null)
                return false;
            //TODO проверка на то что пользователь заказывал товар -----------------------------
            // Поиск дубликатов.  Пользователь не может оценить товар больше 1 раза
            List<Feedback> chats = entityManager
                    .createQuery("SELECT fb FROM Feedback fb WHERE fb.user.uuid = :usr_id AND fb.good.uuid = :good_id",Feedback.class)
                    .setParameter("usr_id", u.getUuid())
                    .setParameter("good_id", g.getUuid())
                    .getResultList();
            if(!chats.isEmpty())
                return false;
            
            feedback.setUser(u);
            feedback.setGood(g);
            feedback.setTimestamp(new Date());
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
