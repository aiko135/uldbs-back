/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.service;

import com.penzasoft.uldbs.dto.FullGoodInfoDto;
import com.penzasoft.uldbs.facade.GoodFacade;
import com.penzasoft.uldbs.model.Good;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
@Path("good")
public class GoodService {
    @Inject
    private GoodFacade goodFacade;
    
    @GET
    @Path("getGoods")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Good> getMessagesForChat(){
        return goodFacade.getAllGoods(100, 0);
    }
    
    @GET
    @Path("getGoodsByPopularity")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Good> getGoodsByPopularity(){
        return goodFacade.getGoodsByPopularity(100, 0);
    }
    
    @GET
    @Path("getGoodsByRequests")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Good> getGoodsByRequests(){
        return goodFacade.getGoodsByRequests(100, 0);
    }
    
    @GET
    @Path("getFullInfoForGood/{good}")
    @Produces({MediaType.APPLICATION_JSON})
     public FullGoodInfoDto getFullGoodInfo(@PathParam("good") UUID good){
        return goodFacade.getFullInfoForGood(good, 50, 0);
    }
     
    @POST
    @Path("createGood")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Boolean createGood(@HeaderParam("catalog") UUID catalogId, Good good){
        return goodFacade.createGood(good, catalogId);
    }
}
