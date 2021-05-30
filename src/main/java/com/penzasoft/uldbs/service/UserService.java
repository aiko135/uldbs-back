/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.service;

import com.penzasoft.uldbs.dto.LoginResultDto;
import com.penzasoft.uldbs.dto.RegisterResultDto;
import com.penzasoft.uldbs.facade.UserFacade;
import com.penzasoft.uldbs.model.User;
import java.util.List;
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
@Path("user")
public class UserService {
    
    @Inject
    private UserFacade userFacade;
    
    @POST
    @Path("doLogin")
    @Produces({MediaType.APPLICATION_JSON})
    public LoginResultDto doLogin(
            @HeaderParam("login") String login,
            @HeaderParam("pass") String password ){
        
        return userFacade.doLogin(login, password);
    }
    
    @POST
    @Path("register")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public RegisterResultDto register(User regdata){
        return userFacade.register(regdata);
    }
    
    @GET
    @Path("getAllUsers")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getAllUsers(){
        return userFacade.getAllUsers(100, 0);
    }
    
    @GET
    @Path("getAllManagers")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getAllManagers(){
        return userFacade.getAllManagers(100, 0);
    }
}
