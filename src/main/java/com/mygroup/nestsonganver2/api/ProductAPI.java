/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.api;

import com.mygroup.nestsonganver2.dto.ProductDTO;
import com.mygroup.nestsonganver2.service.ProductService;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author ADMIN
 */
@Path("Product")
public class ProductAPI {

    private static final ProductService productService = ProductService.getInstance();

    @Context
    UriInfo ui;

    // Show all products
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAll() {

        List<ProductDTO> list = productService.showAllProducts();
        if (list.isEmpty()) 
            return Response.status(Response.Status.NOT_FOUND).build();

            return Response.ok(list, MediaType.APPLICATION_JSON).build();
        

    }

    //search products by name
    @GET
    @Path("searchByName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsByName(@PathParam("name") String keyword) {

        List<ProductDTO> list = productService.searchByName(keyword);
        if (list == null || list.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(list, MediaType.APPLICATION_JSON).build();

    }

    //add new product
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewProduct(ProductDTO product) throws URISyntaxException, NoSuchAlgorithmException {
        //authentication
        int id = productService.addNewProduct(product);
        if (id == 0) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        } else {
            URI uri = new URI(ui.getBaseUri() + "Product/" + id);
            return Response.created(uri).build();
        }

    }

    //search product by cate ID(show the products in the Category
    @GET
    @Path("searchByCateId/{cateId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByCateId(@PathParam("cateId") int cateId) {

        List<ProductDTO> list = productService.getProductByCateId(cateId);
        if (list == null || list.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }

    //get product by cate ID(show the products in the Category
    @GET
    @Path("{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("Id") int Id) {
        List<ProductDTO> list = productService.getProductById(Id);
        if (list == null || list.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }
    
    @PUT
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("isbn") int isbn, ProductDTO product) {
        product.setId(isbn);
        int result = productService.updateProduct(product);
        if (result == 0) 
            return Response.notModified().build();       
        return Response.ok().build();
        //return ve trang product
    }
//
//    //filter 
//    @GET
//    @Path("filter")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response filterProducts(@PathParam("filter")) {
//
//        List<ProductDTO> list = productService.searchByName(name);
//        if (list == null || list.isEmpty()) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//
//        return Response.ok(list, MediaType.APPLICATION_JSON).build();
//
//    }
}
