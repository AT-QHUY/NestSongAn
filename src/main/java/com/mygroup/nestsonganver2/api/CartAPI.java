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

    //1 is cart; 2 is proccesing ;3 is complete; 4 is cancle
    
    //Create cartLine 
//    @GET
//    @Path("/add")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createCartLine(@QueryParam("userId")int userId, @QueryParam("productId")int productId, @QueryParam("quantity")int quantity ) {
//        BillDTO bill = billService.getLastBill(userId);
//        if (bill == null || bill.getDate() == null) {
//            
//        }
//        
//    }
    
    // get all bill
//    @GET
//    @Path("/customer/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getCartLine(@PathParam("id")int customerId) {
//        if (this.cartLine.isEmpty())
//            this.cartLine = billService.getBillByCUstomerIdAndStatus(customerId, 1);
//
//        if (this.cartLine.isEmpty()) 
//            return Response.status(Response.Status.NO_CONTENT).build();
//        return Response.ok(cartLine, MediaType.APPLICATION_JSON).build();
//    }
    
    // get all BillDetails of all bills belong to current customer
    @GET
    @Path("/customer/{customerId}/cart-line-items")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCartLineItems(@PathParam("customerId")int customerId) {
        List<BillDetailsDTO> cartLineItems = cartService.getCartLineItems(customerId);
        return Response.ok(cartLineItems, MediaType.APPLICATION_JSON).build();
    }
    
    @PUT
    @Path("/update/user/{userId}/cart-line-items")
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
        return Response.ok("Update successfull", MediaType.TEXT_PLAIN).build();
    }
    
    @DELETE
    @Path("/delete/user/{userId}/cart-line-items")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCartlineItems(BillDetailsDTO bd, @PathParam("userId")int userId) {
        int check = cartService.deleteBillDetail(bd.getId(), userId);
        if(check == -1) 
            return Response.notModified().build();
        if (check == 0)
            return Response.noContent().build();
        return Response.ok(bd.getId() + " has been deteled", MediaType.TEXT_PLAIN).build();
    }
}
