/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.facade;

import com.penzasoft.uldbs.model.Chat;
import com.penzasoft.uldbs.model.User;
import static com.penzasoft.uldbs.util.Settings.FINAL_STATUS_UUID;
import static com.penzasoft.uldbs.util.Settings.MANAGER_ROLE_CODE;
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
public class ChatFacade {
    
     private static final Logger logger = Logger.getLogger(ChatFacade.class.getName());
    
    @PersistenceContext(unitName = "testPU")
    private EntityManager entityManager;
    
    public List<Chat> getChatsByClientId(UUID clientId){
        return entityManager
                .createQuery("SELECT ch FROM Chat ch WHERE ch.client.uuid = :cl_id", Chat.class)
                .setParameter("cl_id", clientId)
                .getResultList();
    }
    
    public List<Chat> getChatsByManagerId(UUID managerId){
         return entityManager
                .createQuery("SELECT ch FROM Chat ch WHERE ch.manager.uuid = :mn_id", Chat.class)
                .setParameter("mn_id", managerId)
                .getResultList();
    }
    
    public Boolean createChat(UUID userId, UUID managerId){
        try{
            User client = entityManager.find(User.class, userId);
            User manager = entityManager.find(User.class, managerId);
            if(client == null || manager == null)
               return false; 
            
            Chat newChat = new Chat();
            newChat.setUuid(UUID.randomUUID());
            newChat.setClient(client);
            newChat.setManager(manager);
            entityManager.persist(newChat);
            entityManager.flush();
            return true;
        }
            catch(Exception e){
            //result.setMessage(result.getMessage()+": "+e.getMessage());
            logger.log(Level.SEVERE, e.getMessage(),e);
            return false;
        }
    }
    
    public Boolean createChatWithRandomManager(UUID userId){
        try{
            List<Chat> chats = getChatsByClientId(userId);
            if(chats.size() > 0) 
                return false; //У пользователя уже есть чаты
            
            User client = entityManager.find(User.class, userId);
            Query q = entityManager.createNativeQuery(
            "select * from getfreemanager(?::integer,?::uuid);"
            );
            q.setParameter(1, MANAGER_ROLE_CODE);
            q.setParameter(2, FINAL_STATUS_UUID);
            String freeManagerUuid = q.getSingleResult().toString();
            User manager = entityManager.find(User.class, UUID.fromString(freeManagerUuid));
            if(client == null || manager == null)
               return false; 
            
            Chat newChat = new Chat();
            newChat.setUuid(UUID.randomUUID());
            newChat.setClient(client);
            newChat.setManager(manager);
            entityManager.persist(newChat);
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
