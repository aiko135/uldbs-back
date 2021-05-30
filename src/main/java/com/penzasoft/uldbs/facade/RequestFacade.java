/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.facade;

import com.penzasoft.uldbs.dto.UsersRequest;
import com.penzasoft.uldbs.model.Request;
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
public class RequestFacade {
    @PersistenceContext(unitName = "testPU")
    private EntityManager entityManager;
    
    private static final Logger logger = Logger.getLogger(MessageFacade.class.getName());
    
    private static String FINAL_STATUS_UUID = "ad631ed1-a650-4f9c-bfa1-70b84c6f0d10";
    private UUID getUuidOfFinalStatus(){
        return UUID.fromString(FINAL_STATUS_UUID);
         // TODO запрос в бд и сохранение в Синглтон
    }
    
    public Boolean postRequest(UsersRequest request){
        try{
            Request new_req = new Request();
            new_req.setUuid(UUID.randomUUID());
            
            User u = entityManager.find(User.class, request.getUserUuid());
            if(u == null)
                return false;
            new_req.setClientUuid(u);
            
            new_req.setPaymentData(request.getPaymentData());
            return true;
        }
            catch(Exception e){
            //result.setMessage(result.getMessage()+": "+e.getMessage());
            logger.log(Level.SEVERE, e.getMessage(),e);
            return false;
        }
    }
    
    public List<Request> getAllUnfinishedRequests(){
          return entityManager
                .createQuery("SELECT rr FROM Request rr WHERE ( "
                        + "(SELECT count(sh) FROM StatusHistory sh WHERE  sh.requestUuid.uuid = rr.uuid AND sh.statusUuid.uuid = :fs_uuid)"
                        + "= 0)", Request.class)
                .setParameter("fs_uuid", UUID.fromString(FINAL_STATUS_UUID))
                .getResultList();
    }
    
    public List<Request> getAllUnfinishedRequestsForManager(UUID managerUuid){
          return entityManager
                .createQuery("SELECT rr FROM Request rr WHERE rr.managerUuid.uuid = :mn_uuid AND ( "
                        + "(SELECT count(sh) FROM StatusHistory sh WHERE  sh.requestUuid.uuid = rr.uuid AND sh.statusUuid.uuid = :fs_uuid)"
                        + "= 0)", Request.class)
                .setParameter("mn_uuid", managerUuid)
                .setParameter("fs_uuid", UUID.fromString(FINAL_STATUS_UUID))
                .getResultList();
    }
}
