package Ecommerce.Web.Ecommerce.Service;

import Ecommerce.Web.Ecommerce.Configuration.JwtAuthenticationFilter;
import Ecommerce.Web.Ecommerce.Dto.AppResponse;


import Ecommerce.Web.Ecommerce.Dto.CartRequest;
import Ecommerce.Web.Ecommerce.Entity.Cart;

import Ecommerce.Web.Ecommerce.Entity.CartItem;
import Ecommerce.Web.Ecommerce.Entity.Product;
import Ecommerce.Web.Ecommerce.Entity.User;
import Ecommerce.Web.Ecommerce.Exception.ApiException;

import Ecommerce.Web.Ecommerce.Repository.CartRepository;
import Ecommerce.Web.Ecommerce.Repository.ProductRepository;
import Ecommerce.Web.Ecommerce.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class CartServices {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;




    public AppResponse<Cart> addToCart(CartRequest cartRequest) {

        String userEmail = JwtAuthenticationFilter.CURRENT_USER;

        Optional<User> user = userRepository.findByEmail(userEmail);

        if (user.isEmpty()) {
            throw new ApiException("user not found");
        }


        Cart cart = new Cart();
        List<CartItem> cartItems = new ArrayList<>();
        for (CartRequest.ItemRequest itemRequest : cartRequest.getItemRequest()) {
            CartItem cartItem = new CartItem();
            Optional<Product> productOptional = productRepository.findById(itemRequest.getProductId());
            if (productOptional.isEmpty()) {
                throw new ApiException("Product not found");
            }
            Product product = productOptional.get();
            cartItem.setProduct(product);
            cartItem.setQuantity(itemRequest.getQuantity());

            cartItems.add(cartItem);
        }
        cart.setCartItems(cartItems);
        cart.setUser(user.get());

        cart.setCreationDate(LocalDateTime.now()); // Set creation date for new carts
        cartRepository.save(cart);

        return new AppResponse<>(200, "Added to cart successfully");
    }
}
