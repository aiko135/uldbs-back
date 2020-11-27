/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.service;

import com.penzasoft.uldbs.dto.LoginResult;
import com.penzasoft.uldbs.facade.UserFacade;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
@Path("user")
public class UserService {
    
    @Inject
    private UserFacade userFacade;
    
    @POST
    @Path("doLogin")
    @Produces({MediaType.APPLICATION_JSON})
    public LoginResult doLogin(
            @HeaderParam("login") String questid,
            @HeaderParam("pass") String password ){
        
        return userFacade.doLogin(questid, password);
    }
    
}
