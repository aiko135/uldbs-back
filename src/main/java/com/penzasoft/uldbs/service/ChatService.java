/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.service;

import com.penzasoft.uldbs.facade.ChatFacade;
import com.penzasoft.uldbs.model.Chat;
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
@Path("chat")
public class ChatService {
    
    @Inject
    private ChatFacade chatFacade;
    
    @GET
    @Path("getChatsByClient")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Chat> getChatsByClientId(@HeaderParam("client") UUID clientId){
        return chatFacade.getChatsByClientId(clientId);
    }
    
    @GET
    @Path("getChatsByManager")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Chat> getChatsByManagerId(@HeaderParam("manager") UUID managerId){
        return chatFacade.getChatsByManagerId(managerId);
    }
}
