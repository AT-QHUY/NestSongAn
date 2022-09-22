/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.api;

import com.mygroup.nestsonganver2.dao.impl.UserDAO;
import com.mygroup.nestsonganver2.dto.UserDTO;
import com.mygroup.nestsonganver2.entity.UserEntity;
import com.mygroup.nestsonganver2.service.UserService;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author huy
 */
@Path("User")
public class UserAPI {

    private static final UserService userService = UserService.getInstance();

    @Context
    UriInfo ui;

    // Get all user in database
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        List<UserDTO> list = userService.findAllUsers();
        if (list.isEmpty() || list == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(list, MediaType.APPLICATION_JSON).build();
        }

    }
    // -------------------------------------------------------------------------

    //get user by id 
    @GET
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOneById(@PathParam("isbn") int isbn) {

        UserDTO user = userService.getUserById(isbn);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(user, MediaType.APPLICATION_JSON).build();
        }

    }

    //--------------------------------------------------------------------------
    // insert new user to database
    @POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(UserDTO user) throws URISyntaxException, NoSuchAlgorithmException {

        int id = userService.insertUser(user);
        if (id == 0) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        } else {
            URI uri = new URI(ui.getBaseUri() + "User/" + id);
            return Response.created(uri).build();
        }

    }

    //--------------------------------------------------------------------------
    // get user by username and password
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) throws NoSuchAlgorithmException {

        user = userService.checkLogin(user);
        if (user != null) {
            return Response.ok(user, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }

    //--------------------------------------------------------------------------
    // Update an user in database
    @PUT
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOne(@PathParam("isbn") int isbn, UserDTO user) {

        int result;
        user.setId(isbn);
        result = userService.updateUser(user);
        if (result == 0) {
            return Response.notModified().build();
        } else {
            return Response.ok().build();
        }

    }
    
    // Update user password
    
    @PUT
    @Path("{isbn}/update-password")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePassword(@PathParam("isbn") int isbn, UserDTO user) throws NoSuchAlgorithmException {

        int result;       
        result = userService.updateUserPassword(isbn, user.getPassword());
        if (result == 0) {
            return Response.notModified().build();
        } else {
            return Response.ok().build();
        }

    }

    //--------------------------------------------------------------------------
    // Delete an user by changing status
    @DELETE
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOne(@PathParam("isbn") int isbn) {

        int result ;
        result = userService.updateUserStatus(isbn, 0);
        if (result == 0) {
            return Response.notModified().build();
        } else {
            return Response.ok().build();
        }

    }
    //--------------------------------------------------------------------------
}
