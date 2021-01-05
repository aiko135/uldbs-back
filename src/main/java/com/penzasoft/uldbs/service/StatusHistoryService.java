/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.service;

import com.penzasoft.uldbs.facade.RequestFacade;
import com.penzasoft.uldbs.facade.StatusHistoryFacade;
import com.penzasoft.uldbs.model.Good;
import com.penzasoft.uldbs.model.StatusHistory;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Boolean createSH(StatusHistory sh){
        return shFacade.createStatusHistory(sh);
    }
     
}
