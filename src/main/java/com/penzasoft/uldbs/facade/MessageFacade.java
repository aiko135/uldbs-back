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
    
    public List<Message> getMessagesForChat(UUID chat){
        return entityManager
                .createQuery("SELECT mes FROM Message mes WHERE mes.chatUuid.uuid = :mess_p", Message.class)
                .setParameter("mess_p", chat)
                .getResultList();
    }
    
    public Message postMessage(UUID userid, UUID chatid, String text){
        Message m = new Message();
        UUID uuid = UUID.randomUUID();
        m.setUuid(uuid);
        m.setText(text);
        m.setTimestamp(new Date(0));
        
        User u = new User();
        u.setUuid(userid);
        m.setUserUuid(u);
        
        Chat c = new Chat();
        c.setUuid(chatid);
        m.setChatUuid(c);
        
        entityManager.persist(m);
        entityManager.flush();
        return entityManager
                .createQuery("SELECT mm FROM Message mm WHERE mm.uuid = :mid", Message.class)
                .setParameter("mid", uuid)
                .getSingleResult();
        
    }
}
