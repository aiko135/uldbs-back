/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.facade;

import com.penzasoft.uldbs.dto.FullGoodInfo;
import com.penzasoft.uldbs.model.Chat;
import com.penzasoft.uldbs.model.Feedback;
import com.penzasoft.uldbs.model.Good;
import com.penzasoft.uldbs.model.Message;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
    import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author ktepin
 */
@Stateless
public class GoodFacade {
    
     private static final Logger logger = Logger.getLogger(GoodFacade.class.getName());
    
    
    @PersistenceContext(unitName = "testPU")
    private EntityManager entityManager;
        
    public List<Good> getAllGoods(int limit, int offset){
        limit = limit - 1;
        CriteriaQuery<Good> cq = entityManager.getCriteriaBuilder().createQuery(Good.class);
        cq.select(cq.from(Good.class));
        TypedQuery<Good> q = entityManager.createQuery(cq);
        q.setMaxResults(limit - offset + 1);
        q.setFirstResult(offset);
        return q.getResultList();
    }
    
    public List<Good> getGoodsByPopularity(int limit, int offset){
       return entityManager.createQuery(
               "SELECT gg FROM Good gg GROUP BY gg.uuid ORDER BY COUNT(gg.feedbackList) DESC NULLS LAST")
               .setMaxResults(limit - offset)
               .setFirstResult(offset)
               .getResultList();
    }
    
    public List<Good> getGoodsByRequests(int limit, int offset){
          return entityManager.createQuery(
               "SELECT gg FROM Good gg GROUP BY gg.uuid ORDER BY COUNT(gg.goodRequestList) DESC")
               .setMaxResults(limit - offset)
               .setFirstResult(offset)
               .getResultList();
    }
    
    public FullGoodInfo getFullInfoForGood(UUID goodUuid, int feedback_limit, int feedback_offset){
        FullGoodInfo result = new FullGoodInfo();
        Good g = entityManager
                .createQuery("SELECT gg FROM Good gg WHERE gg.uuid = :idd", Good.class)
                .setParameter("idd", goodUuid )
                .getSingleResult();
        result.setGood(g);
        List<Feedback> feedbacks = entityManager
                .createQuery("SELECT ff FROM Feedback ff WHERE ff.goodUuid.uuid = :idd", Feedback.class)
                .setMaxResults(feedback_limit - feedback_offset)
                .setFirstResult(feedback_offset)
                .setParameter("idd", goodUuid )
                .getResultList();
        result.setFeedbacks(feedbacks);
        return result;
    }
    
    public Boolean createGood(Good good){
        try{
            good.setUuid(UUID.randomUUID());
            entityManager.persist(good);
            entityManager.flush();
            return true;
        }
        catch(Exception e){
            //result.setMessage(result.getMessage()+": "+e.getMessage());
            logger.log(Level.SEVERE, e.getMessage(),e);
            return false;
        }
    }
}   
