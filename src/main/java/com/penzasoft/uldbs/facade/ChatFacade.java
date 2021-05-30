/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.facade;

import com.penzasoft.uldbs.model.Chat;
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
public class ChatFacade {
   
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
}
