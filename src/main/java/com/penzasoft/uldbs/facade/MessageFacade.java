/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.facade;

import com.penzasoft.uldbs.model.Message;
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
}
