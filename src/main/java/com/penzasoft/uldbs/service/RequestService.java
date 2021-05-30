/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.service;

import com.penzasoft.uldbs.dto.FullGoodInfoDto;
import com.penzasoft.uldbs.dto.MyRequestDto;
import com.penzasoft.uldbs.dto.UsersRequestDto;
import com.penzasoft.uldbs.facade.MessageFacade;
import com.penzasoft.uldbs.facade.RequestFacade;
import com.penzasoft.uldbs.model.Request;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ktepin
 */
@Stateless
@Path("request")
public class RequestService {
    @Inject
    private RequestFacade requestFacade;
    
    @POST
    @Path("postRequest")
    @Produces({MediaType.APPLICATION_JSON})
    public Boolean postRequest(UsersRequestDto request){
            return requestFacade.postRequest(request);
    }
    
    @GET
    @Path("myRequests/{user}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<MyRequestDto> getMyRequests(@PathParam("user") UUID user){
        return requestFacade.getMyRequests(user);
    }
    
    @GET
    @Path("getAllUnfinished")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Request> getAllUnfinished(){
        return requestFacade.getAllUnfinishedRequests();
    }
    
    @GET
    @Path("getAllUnfinishedForManager")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Request> getAllUnfinishedForManager(@HeaderParam("manager") UUID managerId){
        return requestFacade.getAllUnfinishedRequestsForManager(managerId);
    }
}
