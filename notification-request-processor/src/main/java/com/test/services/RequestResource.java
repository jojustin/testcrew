package com.test.services;

import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.UriInfo;

import com.test.server.core.AbstractProcessor;
import com.test.server.core.CbBroker;
import com.test.server.core.EmailProcessor;
import com.test.server.core.Processor;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.logging.Logger;;

@Path("/notifications")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class RequestResource {
    Logger logger = Logger.getLogger(RequestResource.class.getName());
    Processor processor = null;

    public RequestResource(final Processor processor) {
        this.processor = processor;
    }

    @POST
    @Path("/notify")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response postNotification(final InputStream is,
    @PathParam("notifcationid") final String notificationid,
    @Context final UriInfo uriInfo, @Context final HttpServletRequest request) {
        if (processor.isReady()) {
            logger.info("--------calling the processor " + processor);
            processor.send();
        }
        return Response.ok().build();
    }
}
