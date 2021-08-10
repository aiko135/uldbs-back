/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.service;

import com.penzasoft.uldbs.facade.RequestFacade;
import com.penzasoft.uldbs.facade.StatusHistoryFacade;
import com.penzasoft.uldbs.model.Good;
import com.penzasoft.uldbs.model.Status;
import com.penzasoft.uldbs.model.StatusHistory;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ktepin
 */
@Stateless
@Path("statushistory")
public class StatusHistoryService {
    @Inject
    private StatusHistoryFacade shFacade;
    
    @POST
    @Path("createStatusHistory")
    @Produces({MediaType.APPLICATION_JSON})
    public Boolean createSH(  
            @HeaderParam("request") UUID request,
            @HeaderParam("status") UUID status,
            @HeaderParam("message") String message){
        return shFacade.createStatusHistory(request, status, message);
    }
    
    @GET
    @Path("getAllStatus")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Status> getAllStatus(){
        return shFacade.getAllStatus();
    }
}
