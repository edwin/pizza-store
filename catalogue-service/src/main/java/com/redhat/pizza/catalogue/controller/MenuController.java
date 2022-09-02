package com.redhat.pizza.catalogue.controller;

import com.redhat.pizza.catalogue.entity.Menu;
import io.quarkus.panache.common.Sort;
import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * <pre>
 *     com.redhat.pizza.catalogue.controller.MenuController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 02 Sep 2022 22:29
 */
@Path("/api/v1")
public class MenuController {

    private static final Logger LOG = Logger.getLogger(MenuController.class);

    @GET
    @Path("/menu")
    @Produces(MediaType.APPLICATION_JSON)
    public Response displayMenu(@QueryParam("page") Integer page) {
        LOG.debug(String.format("Accessing Menu page %s ", page));
        if(page==null || page <0)
            return Response.status(400).entity(new ArrayList<Menu>()).build();

        return Response.ok(Menu.findAll(Sort.by("id", Sort.Direction.Descending))
                    .page(page, 5)
                    .list())
                .build();
    }
}
