/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.service;


import com.penzasoft.uldbs.facade.FeedbackFacade;
import com.penzasoft.uldbs.model.Feedback;
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
@Path("feedback")
public class FeedbackService {
    @Inject
    private FeedbackFacade feedbackFacade;
    
    @POST
    @Path("addFeedback")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Boolean addFeedback(
         @HeaderParam("user") UUID user,
         @HeaderParam("good") UUID good,
             Feedback feedback){
        return feedbackFacade.addFeedback(feedback, user, good);
    }
    
    @GET
    @Path("getAllFeedbacks")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Feedback> getAllFeedbacks(){
        return feedbackFacade.getAllFeedbacks(100, 0);
    }
}
