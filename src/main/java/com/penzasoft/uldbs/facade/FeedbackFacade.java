/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.facade;

import com.penzasoft.uldbs.model.Feedback;
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
    
    public Feedback addFeedback(Feedback feedback){
        entityManager.persist(feedback);
        return feedback;
    }
}
