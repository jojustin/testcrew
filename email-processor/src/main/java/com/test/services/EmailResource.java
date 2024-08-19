package com.test.services;

import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.logging.Logger;

@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class EmailResource {
    Logger logger = Logger.getLogger(EmailResource.class.getName());

    @POST
    @Path("/notify")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response postEmailNotification(final InputStream is,
    @PathParam("notifcationid") final String notificationid,
    @Context final UriInfo uriInfo, @Context final HttpServletRequest request) {
        logger.info("--------sending back the email response");
        return Response.ok().status(200).entity("emailprocessed").build();
    }
}
