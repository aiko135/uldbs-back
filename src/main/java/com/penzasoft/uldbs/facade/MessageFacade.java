/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.facade;

import com.penzasoft.uldbs.model.Chat;
import com.penzasoft.uldbs.model.Message;
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
public class MessageFacade {
       
    @PersistenceContext(unitName = "testPU")
    private EntityManager entityManager;
    
     private static final Logger logger = Logger.getLogger(MessageFacade.class.getName());
    
    public List<Message> getMessagesForChat(UUID chat){
        return entityManager
                .createQuery("SELECT mes FROM Message mes WHERE mes.chat.uuid = :mess_p", Message.class)
                .setParameter("mess_p", chat)
                .getResultList();
    }
    
    public Boolean postMessage(UUID userid, UUID chatid, String text){
        try{
            Message m = new Message();
            UUID uuid = UUID.randomUUID();
            m.setUuid(uuid);
            m.setText(text);
            m.setTimestamp(new Date(0));
        
            User u = new User();
            u.setUuid(userid);
            m.setUser(u);
        
            Chat c = new Chat();
            c.setUuid(chatid);
            m.setChat(c);
        
            entityManager.persist(m);
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
