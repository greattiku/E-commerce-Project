package Ecommerce.Web.Ecommerce.Service;

import Ecommerce.Web.Ecommerce.Configuration.JwtAuthenticationFilter;
import Ecommerce.Web.Ecommerce.Dto.AppResponse;
import Ecommerce.Web.Ecommerce.Dto.CartRequest;
import Ecommerce.Web.Ecommerce.Dto.OrderRequest;
import Ecommerce.Web.Ecommerce.Entity.*;
import Ecommerce.Web.Ecommerce.Exception.ApiException;
import Ecommerce.Web.Ecommerce.Repository.OrderDetailRepository;
import Ecommerce.Web.Ecommerce.Repository.ProductRepository;
import Ecommerce.Web.Ecommerce.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private static final String ORDER_PLACED = "Placed";

    public AppResponse<String> placeOrder(OrderRequest orderRequest){
       List<CartRequest.ItemRequest> itemRequests = orderRequest.getItemRequest();

       for (CartRequest.ItemRequest o : itemRequests) {
           Product product = productRepository.findById(o.getProductId()).orElseThrow(()->new ApiException("Product not found or doesn't exist"));
           String userEmail = JwtAuthenticationFilter.CURRENT_USER;
         User user = userRepository.findByEmail(userEmail).orElseThrow(()->new ApiException("User not found or doesn't exist"));
           OrderDetail order = new OrderDetail();
           order.setFirstName(orderRequest.getFirstName());
           order.setLastName(orderRequest.getLastName());
           order.setPhoneNumber(orderRequest.getPhoneNumber());
           order.setCity(orderRequest.getCity());
           order.setState(orderRequest.getState());
           order.setPostalCode(orderRequest.getPostalCode());
           order.setCountry(orderRequest.getCountry());
           order.setDeliveryType(deliveryMethod(orderRequest));
           order.setOrderStatus(ORDER_PLACED);
           order.setProduct(product);
           order.setTotal(product.getPrice() * o.getQuantity());
           order.setUser(user);
           order.setCreationDate(LocalDateTime.now());

           orderDetailRepository.save(order);
       }
        return new AppResponse<>(0, " Your Order has been placed successfully");

    }
    public String deliveryMethod(OrderRequest orderRequest) {
        String dType = orderRequest.getDeliveryType();

        switch (dType) {
            case "doorDelivery":
                return "Door Delivery"; // Example return value

            case "pick-Up-In-Store":
                return "Pick-Up-In-Store"; // Example return value
            default:
                throw new InputMismatchException("Unexpected value: input should be between Door Delivery and pick-up-in-store");
        }
    }
}
