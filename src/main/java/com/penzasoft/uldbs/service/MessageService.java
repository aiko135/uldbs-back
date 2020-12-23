/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.service;

import com.penzasoft.uldbs.facade.MessageFacade;
import com.penzasoft.uldbs.model.Message;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ktepin
 */
@Stateless
@Path("message")
public class MessageService {
    @Inject
    private MessageFacade messageFacade;
    
    @GET
    @Path("getMessagesForChat")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Message> getMessagesForChat(@HeaderParam("chat") UUID chat){
        return messageFacade.getMessagesForChat(chat);
    }
}
