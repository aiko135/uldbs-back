/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.facade;

import com.penzasoft.uldbs.model.Request;
import java.util.List;
import java.util.UUID;
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
    
    private static String FINAL_STATUS_UUID = "ad631ed1-a650-4f9c-bfa1-70b84c6f0d10";
    
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
