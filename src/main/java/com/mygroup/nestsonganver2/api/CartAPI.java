/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.api;

import com.mygroup.nestsonganver2.dto.BillDetailsDTO;
import com.mygroup.nestsonganver2.service.CartService;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author dd220
 */
@Path("cart")
public class CartAPI {
    private CartService cartService = CartService.gettCartSerivce();
    
    // get cart lines items
    @GET
    @Path("/customer/{customerId}/cart-line-items")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCartLineItems(@PathParam("customerId")int customerId) {
        List<BillDetailsDTO> cartLineItems = cartService.getCartLineItems(customerId);
        return Response.ok(cartLineItems, MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("/add/customer/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addToCart(BillDetailsDTO bd, @PathParam("customerId")int customerId) {
        List<BillDetailsDTO> items = cartService.addToCart(bd, customerId);
        if (items == null) return Response.status(Response.Status.NOT_MODIFIED).build();
        else return Response.ok(items, MediaType.APPLICATION_JSON).build();
    }
    
    //Buy 
    @GET
    @Path("/buy/customer/{customerId}") 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buy(@PathParam("customerId")int customerId) {
        if (cartService.buy(customerId)) {
            return Response.ok().build();
        }
        return Response.notModified().build();
    }
    
    //update quantity of item
    @PUT
    @Path("/update/cart-line-items/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCartLineItemsQuantiy(BillDetailsDTO bd, @PathParam("userId") int userId) {
        int billId = bd.getBillId();
        int billDetailsId = bd.getId();
        int quantity = bd.getQuantity();
        int check = cartService.updateQuantity(billDetailsId, quantity, billId, userId);
        if (check == -1)
            return Response.notModified().build();
        if (check == 0) 
            return Response.noContent().build();
        List<BillDetailsDTO> listDto = cartService.getCartLineItems(userId);
        return Response.ok(listDto, MediaType.APPLICATION_JSON).build();
    }
    
    
    //delete item in cart
    @DELETE
    @Path("/delete/cart-line-items/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCartlineItems(BillDetailsDTO bd, @PathParam("userId")int userId) {
        int check = cartService.deleteBillDetail(bd.getId(), userId);
        if(check == -1) 
            return Response.notModified().build();
        if (check == 0)
            return Response.noContent().build();
        List<BillDetailsDTO> listDto = cartService.getCartLineItems(userId);
        return Response.ok(listDto, MediaType.APPLICATION_JSON).build();
    }
}
