package com.example.onlineshopping.controller;

import com.example.onlineshopping.domain.*;
import com.example.onlineshopping.dto.OrderAddressRequest;
import com.example.onlineshopping.service.BuyerService;
import com.example.onlineshopping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class CustomerController {

    @Autowired
    BuyerService buyerService;

    @Autowired
    SellerService sellerService;

    @GetMapping("/buyers/{id}")
    public EntityModel<Buyer> getCustomerById(@PathVariable long id){
        /**
         * Get customer by id
         * Input:
         *      id: long customer [buyer] id
         * Returns:
         *      Buyer Object
         **/
        Buyer customer = buyerService.findBuyerById(id);
        EntityModel<Buyer> customerEntity = EntityModel.of(customer);
        WebMvcLinkBuilder customerLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getCustomerReviews(id));
        customerEntity.add(customerLink.withRel("Customer Reviews"));
        return customerEntity;
    }
    @GetMapping("/buyers/{id}/reviews")
    public List<Review> getCustomerReviews(@PathVariable long id){
        /**
         * Get Customer Review
         * Input:
         *      customer id: long
         * Returns:
         *      List of Reviews
         ***/

        return buyerService.findReviewsByBuyerId(id);
    }

    @GetMapping("/buyers/{id}/addresses") // get buyer addresses
    public List<Address> getAddressesOfCustomer(@PathVariable long id){
        /**
         * Input: path variable customer id
         * Returns: List of Address **/
        return buyerService.getAddressesOfBuyer(id);
    }

    @PostMapping("/buyers/{id}/addresses")
    public EntityModel<Address> addAddressToCustomer(@RequestBody Address address, @PathVariable long id){
        /**
        * adds addaress to a customer [buyer in our case]**/
        Address add = buyerService.addAddressToBuyer(address, id);
        EntityModel<Address> addEntity = EntityModel.of(add);
        WebMvcLinkBuilder addLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getCustomerById(id));
        addEntity.add(addLink.withRel("Customer"));
        return addEntity;
    }

    @PostMapping("/buyers/{id}/products/{productId}/reviews") // add buyer review
    public EntityModel<Review> addReviewToProductByCustomer(@RequestBody Review review, @PathVariable long id, @PathVariable long productId){
        Review rev = buyerService.addReviewByBuyerId(review, id, productId);
        EntityModel<Review> revEntity = EntityModel.of(rev);
        WebMvcLinkBuilder revLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getCustomerReviews(id));
        revEntity.add(revLink.withRel("Customer Reviews"));
        return revEntity;
    }


    @GetMapping("/buyers/{id}/orders") // get buyer orders
    public List<Order> getOrdersForCustomer(@PathVariable long id){
        return buyerService.findBuyerOrdersById(id);
     }

    @GetMapping("/buyers/{id}/shoppingcart")
    public List<Product> getShoppingCart(@PathVariable long id){
        return buyerService.findOrCreateShoppingCart(id);
    }

    @PatchMapping("/buyers/{id}/shoppingcart")
    public List<Product> addProductsToShoppingCart(@RequestBody List<Product> products, @PathVariable long id){
        return buyerService.addProductsToCart(products, id);
    }

    @PatchMapping("/buyers/{id}/shoppingcart/clear")
    public List<Product> clearShoppingCart(@PathVariable long id){
        return buyerService.clearShoppingCart(id);
    }

    @PostMapping("/buyers/{id}/shoppingcart/process")
    public Invoice processShoppingCart(@RequestBody OrderAddressRequest orderAddresses, @PathVariable long id){
        /**
         * Input : OrderAddressRequest
         * Returns: Invoice Object
         * **/
        return buyerService.processShoppingCart(orderAddresses, id);
    }

    @GetMapping("/buyers/{id}/sellers/{sellerId}/follow")
    public List<Seller> followSeller(@PathVariable long id, @PathVariable long sellerId){
        /**
         * Input: id:
         *      Customer [Buyer] id
         *      sellerId: long Seller or Retailer id
         * Returns:
         *      List<Sellers>
         * **/
        Seller seller = sellerService.getSellerById(sellerId);
        return buyerService.followSeller(id, seller);
    }

    @GetMapping("/buyers/{id}/sellers/{sellerId}/unfollow")
    public List<Seller> unFollowSeller(@PathVariable long id, @PathVariable long sellerId){
        /**
         * Input: id:
         *      Customer [Buyer] id
         *      sellerId: long Seller or Retailer id
         * Returns:
         *      List<Sellers>
         * **/
        return buyerService.unFollowSeller(id, sellerId);
    }

    @GetMapping("/buyers/{id}/sellers/{sellerId}")
    public boolean isFollowing(@PathVariable long id, @PathVariable long sellerId){
        /**
         * Input: id:
         *      Customer [Buyer] id
         *      sellerId: long Seller or Retailer id
         * Returns:
         *      List<Sellers>
         * **/
        return buyerService.isFollowing(id, sellerId);
    }







    // @PostMapping("/buyers/{id}/sellers/{sellerId}/unfollow")
    // @GetMapping("/buyers/{id}/sellers/{sellerId}") // following boolean

}
