/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.facade;

import com.penzasoft.uldbs.dto.MyRequestDto;
import com.penzasoft.uldbs.dto.UsersRequestDto;
import com.penzasoft.uldbs.model.GoodRequest;
import com.penzasoft.uldbs.model.Request;
import com.penzasoft.uldbs.model.Status;
import com.penzasoft.uldbs.model.StatusHistory;
import com.penzasoft.uldbs.model.User;
import static com.penzasoft.uldbs.util.Settings.FINAL_STATUS_UUID;
import static com.penzasoft.uldbs.util.Settings.MANAGER_ROLE_CODE;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
public class RequestFacade {
    @PersistenceContext(unitName = "testPU")
    private EntityManager entityManager;
    
    private static final Logger logger = Logger.getLogger(RequestFacade.class.getName());
    
    private UUID getUuidOfFinalStatus(){
        return UUID.fromString(FINAL_STATUS_UUID);
         // TODO запрос в бд и сохранение в Синглтон
    }
    
    public Boolean postRequest(UsersRequestDto request){
        try{
            if(request.getGoods().size() < 1)
                return false;
            //Request creation
            Request new_req = new Request();
            new_req.setUuid(UUID.randomUUID());
            
            User u = entityManager.find(User.class, request.getUserUuid());
            if(u == null)
                return false;
            new_req.setClient(u);
            
            new_req.setPaymentData(request.getPaymentData());
            
            //Поиск наименее загруженного менеджера
            //DATABASE FUNCTION
            Query q = entityManager.createNativeQuery(
            "select * from getfreemanager(?::integer,?::uuid);"
            );
            q.setParameter(1, MANAGER_ROLE_CODE);
            q.setParameter(2, FINAL_STATUS_UUID);
            String freeManagerUuid = q.getSingleResult().toString();
            User m = entityManager.find(User.class, UUID.fromString(freeManagerUuid));
            new_req.setManager(m);
            
            entityManager.persist(new_req);
            entityManager.flush();
            //---------------
                    
            //Request history create
            StatusHistory history = new StatusHistory();
            history.setUuid(UUID.randomUUID());
            history.setRequest(new_req);
            
            Status initStatus = entityManager.createQuery("SELECT st FROM Status st WHERE st.isInitial = 1",Status.class).getSingleResult();
            history.setStatus(initStatus);
            
            history.setSetupTimestamp(new Date());
            entityManager.persist(history);
            entityManager.flush();
            
            //GOOD REQUEST
            for(int i = 0; i<request.getGoods().size(); i++){
                GoodRequest gr = new GoodRequest();
                gr.setUuid(UUID.randomUUID());
                gr.setRequest(new_req);
                gr.setGood(request.getGoods().get(i));
                entityManager.persist(gr);
                entityManager.flush();
            }
     
            return true;
        }
            catch(Exception e){
            //result.setMessage(result.getMessage()+": "+e.getMessage());
            logger.log(Level.SEVERE, e.getMessage(),e);
            return false;
        }
    }
    
    public List<MyRequestDto> getClientRequests(UUID client){
        List<Request> myReqs = entityManager
               .createQuery("SELECT rr FROM Request rr WHERE rr.client.uuid = :clientUuid", Request.class)
               .setParameter("clientUuid", client)
               .getResultList();
        
        ArrayList<MyRequestDto> dtoList = new ArrayList<MyRequestDto>();
        
        for(int i=0; i<myReqs.size(); i++){
            MyRequestDto dto = new MyRequestDto();
            Request old = myReqs.get(i);
                    
            dto.setRequestUuid( old.getUuid() );
            dto.setStatusHistoryList( old.getStatusHistoryList() );
            dto.setContactorUuid(old.getManager().getUuid());
            dto.setContactorPhone(old.getManager().getPhone());
            dto.setContactorEmail(old.getManager().getEmail());
            dto.setGoodRequestList(old.getGoodRequestList());
            dto.setContactorName(old.getManager().getName());
            
            dtoList.add(dto);
        }
        return dtoList;
    }
    
     public List<MyRequestDto> getManagerRequests(UUID manager){
        List<Request> myReqs = entityManager
               .createQuery("SELECT rr FROM Request rr WHERE rr.manager.uuid = :mUuid", Request.class)
               .setParameter("mUuid", manager)
               .getResultList();
        
        ArrayList<MyRequestDto> dtoList = new ArrayList<MyRequestDto>();
        
        for(int i=0; i<myReqs.size(); i++){
            MyRequestDto dto = new MyRequestDto();
            Request old = myReqs.get(i);
                    
            dto.setRequestUuid( old.getUuid() );
            dto.setStatusHistoryList( old.getStatusHistoryList() );
            dto.setContactorUuid(old.getClient().getUuid());
            dto.setContactorPhone(old.getClient().getPhone());
            dto.setContactorEmail(old.getClient().getEmail());
            dto.setGoodRequestList(old.getGoodRequestList());
            dto.setContactorName(old.getClient().getName());
            
            dtoList.add(dto);
        }
        return dtoList;
    }
    
    public List<Request> getAllUnfinishedRequests(){
          return entityManager
                .createQuery("SELECT rr FROM Request rr WHERE ( "
                        + "(SELECT count(sh) FROM StatusHistory sh WHERE  sh.request.uuid = rr.uuid AND sh.status.uuid = :fs_uuid)"
                        + "= 0)", Request.class)
                .setParameter("fs_uuid", UUID.fromString(FINAL_STATUS_UUID))
                .getResultList();
    }
    
    public List<Request> getAllUnfinishedRequestsForManager(UUID managerUuid){
          return entityManager
                .createQuery("SELECT rr FROM Request rr WHERE rr.manager.uuid = :mn_uuid AND ( "
                        + "(SELECT count(sh) FROM StatusHistory sh WHERE  sh.request.uuid = rr.uuid AND sh.status.uuid = :fs_uuid)"
                        + "= 0)", Request.class)
                .setParameter("mn_uuid", managerUuid)
                .setParameter("fs_uuid", UUID.fromString(FINAL_STATUS_UUID))
                .getResultList();
    }
}
