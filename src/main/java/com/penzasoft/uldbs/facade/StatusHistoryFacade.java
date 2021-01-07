/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.facade;

import com.penzasoft.uldbs.model.Good;
import com.penzasoft.uldbs.model.Request;
import com.penzasoft.uldbs.model.Status;
import com.penzasoft.uldbs.model.StatusHistory;
import java.util.Date;
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
public class StatusHistoryFacade {
    @PersistenceContext(unitName = "testPU")
    private EntityManager entityManager;
    
    private static final Logger logger = Logger.getLogger(StatusHistoryFacade.class.getName());
    
    public Boolean createStatusHistory(UUID request, UUID status, String message){
        try{
            StatusHistory sh = new StatusHistory();
            sh.setUuid(UUID.randomUUID());
            sh.setSetupTimestamp(new Date());
            sh.setRequestUuid(new Request(request));
            sh.setStatusUuid(new Status(status));
            if(!(message.equals("null")))
                sh.setComment(message);
            entityManager.persist(sh);
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
