package com.example.onlineshopping.service;

import com.example.onlineshopping.domain.Product;
import com.example.onlineshopping.domain.ShoppingCart;
import com.example.onlineshopping.dto.ProductDto;
import com.example.onlineshopping.dto.ShoppingCartDto;
import com.example.onlineshopping.globalExecption.ResourceNotFoundException;
import com.example.onlineshopping.repository.ShoppingCartRepository;
import com.example.onlineshopping.util.ListMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class CartService {
    private final ShoppingCartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final ListMapper<ProductDto, Product> productDtoProductListMapper;

    private ShoppingCartDto findById(long id){
        ShoppingCartDto shoppingCartDto = modelMapper.map(cartRepository.findById(id).orElse(null), ShoppingCartDto.class);
        if(shoppingCartDto == null)
            throw new ResourceNotFoundException("Shopping cart id: "+id+" not found");
        return shoppingCartDto;
    }

    public ShoppingCartDto addProductToCart(long id, ProductDto productDto){
        /**
         * Input:
         *      id: long - Shopping cart id
         *      productDto: ProductDto
         * Returns:
         *      ShopingCartDto
         *
         **/
        ShoppingCartDto cartDto = findById(id);
        List<ProductDto> productDtos = cartDto.getProductDtos();
        if(productDtos == null)
            productDtos = new ArrayList<>();
        productDtos.add(productDto);
        ShoppingCart cart = modelMapper.map(cartDto, ShoppingCart.class);
        cart.setProducts((List<Product>) productDtoProductListMapper.mapList(productDtos, new Product()));
        return modelMapper.map(cartRepository.save(cart), ShoppingCartDto.class);
    }
}
