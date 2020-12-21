package com.penzasoft.uldbs;

import javax.ws.rs.*;

@Path("/")
public class Main{
  @GET
  @Path("echo")
  public String echo() {
    return "Reqeust works!";
  }
}