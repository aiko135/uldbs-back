/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.service;

import com.penzasoft.uldbs.facade.ChatFacade;
import com.penzasoft.uldbs.model.Chat;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 *
 * @author ktepin
 */
//http://localhost:8081/uldbs-back/file/1.jpeg
@Stateless
@Path("file")
public class FileService {
    
    private final String filesHome = "/home/ktepin/uldbs-server/apache-tomee-plume-8.0.5/files";
    
    @GET
    @Path("/{path}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile(@PathParam("path") String path){
        File fileDownload = new File(filesHome + File.separator + path);
	ResponseBuilder response = Response.ok((Object) fileDownload);
        String name = fileDownload.getName();
	response.header("Content-Disposition", "attachment;filename=" + name);
	return response.build();
    }
}
