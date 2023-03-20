package com.penzasoft.uldbs;

import javax.ws.rs.*;

@Path("/")
public class Main{
  @GET
  @Path("echo")
  public String echo() {
    return "Reqeust works!";
  }
  
  @GET
  @Path("test")
  public String test() {
    testCryptoLib();
    return "Crypto lib tested. See server logs for details";
  }
  
  private void testCryptoLib(){
      System.out.println("Starting cryptolib check");
  }
}