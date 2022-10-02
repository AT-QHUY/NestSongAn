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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author huy
 */
@Path("user")
public class UserAPI {

    private static final UserService userService = UserService.getInstance();

    @Context
    UriInfo ui;
    
    // Get all user in database
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context HttpHeaders httpHeaders){
        String token = httpHeaders.getHeaderString("token");
        List<UserDTO> list = userService.findAllUsers(token);
        if (list == null || list.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } else {
            return Response.ok(list, MediaType.APPLICATION_JSON).build();
        }
    }

    // -------------------------------------------------------------------------
     //get user by id 
    
    @GET
    @Path("/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOneById(@PathParam("isbn") int isbn, @Context HttpHeaders httpHeaders) {
        String token = httpHeaders.getHeaderString("token");
        UserDTO user = userService.getUserById(isbn, token);
        if (user == null) return Response.status(Response.Status.UNAUTHORIZED).build();
        else if(user.getId() == 0) return Response.status(Response.Status.NO_CONTENT).build();
        else return Response.ok(user, MediaType.APPLICATION_JSON).build();
    }

   
    

    //--------------------------------------------------------------------------
    // insert new user to database
    @POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(UserDTO user) throws URISyntaxException, NoSuchAlgorithmException {

        String token = userService.insertUser(user);
        if (token == null) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        } else {          
            return Response.ok(token, MediaType.APPLICATION_JSON).build();
        }

    }

    //--------------------------------------------------------------------------
    // get user by username and password
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) throws NoSuchAlgorithmException {

        String token = userService.checkLogin(user);
        if (token != null) {
            return Response.ok(token, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }

    }

    //--------------------------------------------------------------------------
    // Update an user in database
    @PUT
    @Path("/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOne(@PathParam("isbn") int isbn, UserDTO user) {

        String token = userService.updateUser(isbn, user);
        if (token == null)  return Response.status(Response.Status.UNAUTHORIZED).build();
        else if(token.isEmpty()) return Response.status(Response.Status.NOT_MODIFIED).build();
        else return Response.ok().build();

    }
    
    // Update user password
    
    @PUT
    @Path("/update-password/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePassword(@PathParam("isbn") int isbn, UserDTO user) throws NoSuchAlgorithmException {

        int result;       
        result = userService.updateUserPassword(isbn, user.getPassword(), user.getNewPassword(), user.getToken());
        switch (result) {
            case 0:
                return Response.notModified().build();
            case 2:
                return Response.status(Response.Status.UNAUTHORIZED).build();
            default:
                return Response.ok().build();
        }

    }

    //--------------------------------------------------------------------------
    // Delete an user by changing status
    @DELETE
    @Path("/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOne(@PathParam("isbn") int isbn, UserDTO user) {
        int result ;
        result = userService.updateUserStatus(isbn, 0, user.getToken());
        switch (result) {
            case 0:
                return Response.notModified().build();
            case 2:
                return Response.status(Response.Status.UNAUTHORIZED).build();
            default:
                return Response.ok().build();
        }
        

    }
    //--------------------------------------------------------------------------
}
