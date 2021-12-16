package com.example.onlineshopping.service.implementation;


import com.example.onlineshopping.domain.*;
import com.example.onlineshopping.dto.OrderAddressRequest;
import com.example.onlineshopping.globalExecption.UserNotFoundException;
import com.example.onlineshopping.repository.*;
import com.example.onlineshopping.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@Transactional
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    public Buyer findBuyerById(long id) {
        return buyerRepository.findById(id).orElseThrow();
    }


    @Override
    public Buyer addBuyer(Buyer buyer) {
        Buyer savedBuyer = buyerRepository.save(buyer);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setBuyer(savedBuyer);
        shoppingCartRepository.save(shoppingCart);
        return savedBuyer;
    }

    @Override
    public List<Order> findBuyerOrdersById(long id) {
        Buyer buyer = findBuyerById(id);
        return orderRepository.findAllByBuyer(buyer);
    }

    @Override
    public Review addReviewByBuyerId(Review review, long id, long productId) {
        Buyer buyer = findBuyerById(id);
        Product product = productRepository.findById(productId).get();
        review.setBuyer(buyer);
        review.setProduct(product);
        review.setApproved(false);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findReviewsByBuyerId(long id) {
        Buyer buyer = findBuyerById(id);
        return reviewRepository.findAllByBuyer(buyer);
    }

    @Override
    public Address addAddressToBuyer(Address address, long id) {
        Buyer buyer = findBuyerById(id);
        address.setBuyer(buyer);
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAddressesOfBuyer(long id) {
        Buyer buyer = findBuyerById(id);
        return addressRepository.findAllByBuyer(buyer);
    }

    @Override
    public List<Product> findOrCreateShoppingCart(long id) {
        Buyer buyer = findBuyerById(id);
        ShoppingCart shoppingCart = shoppingCartRepository.findFirstByBuyer(buyer);
        return shoppingCart.getProducts();
    }

    @Override
    public List<Product> addProductsToCart(List<Product> products, long id) {
        Buyer buyer = findBuyerById(id);
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByBuyer(buyer);
        shoppingCart.setProducts(products);
        return shoppingCartRepository.save(shoppingCart).getProducts();
    }

    @Override
    public List<Product> clearShoppingCart(long id) {
        Buyer buyer = findBuyerById(id);
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByBuyer(buyer);
        shoppingCart.setProducts(new ArrayList<>());
        return shoppingCartRepository.save(shoppingCart).getProducts();
    }

    @Override
    public List<Seller> followSeller(long id, Seller seller){
        Buyer customer = buyerRepository.findById(id).get();
        if(customer == null)
            throw new UserNotFoundException("Buyer with id of :"+id+" Not found");
        List<Seller> sellers = (List<Seller>) customer.getFollowing();
        if(sellers == null)
            sellers = new ArrayList<>();
        sellers.add(seller);
        customer.setFollowing((Set<Seller>) sellers);
        buyerRepository.save(customer);
        return  sellers;
    }

    @Override
    public List<Seller> unFollowSeller(long id, long seller_id) {
        Buyer customer = buyerRepository.findById(id).get();
        if(customer == null)
            throw new UserNotFoundException("Buyer with id of :"+id+" Not found");
        List<Seller> sellers = (List<Seller>) customer.getFollowing();
        if(sellers == null)
            sellers = new ArrayList<>();
        sellers = sellers.stream().filter(s -> s.getId() != seller_id).collect(Collectors.toList());
        customer.setFollowing((Set<Seller>) sellers);
        buyerRepository.save(customer);
        return sellers;
    }

    @Override
    public Invoice processShoppingCart(OrderAddressRequest orderAddresses, long id) {
        List<Product> products = findOrCreateShoppingCart(id);
        if(products.size() > 0){
            Buyer buyer = findBuyerById(id);
            float totalPrice = products.stream().map(product -> product.getPrice()).reduce((price, total)-> price + total).get();
            System.out.println("Price Total: "+ totalPrice);
            List<Order> orders = products.stream().map(product -> {
                Order order = new Order();
                order.setStatus(OrderStatus.ORDERED);
                order.setBuyer(buyer);
                order.setProduct(product);
                order.setShippingAddress(orderAddresses.getShippingAddress());
                order.setBillingAddress(orderAddresses.getBillingAddress());
                return order;
            }).collect(Collectors.toList());
            List<Order> savedOrders = StreamSupport.stream(orderRepository.saveAll(orders).spliterator(), false).collect(Collectors.toList());
            buyer.setBalance(buyer.getBalance() - totalPrice);
            buyerRepository.save(buyer);
            Invoice invoice = new Invoice();
            invoice.setOrders(savedOrders);
            clearShoppingCart(id);
            return invoiceRepository.save(invoice);
        }
        return null;
    }
}
