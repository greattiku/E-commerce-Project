package Ecommerce.Web.Ecommerce.Controller;

import Ecommerce.Web.Ecommerce.Dto.AppResponse;

import Ecommerce.Web.Ecommerce.Dto.CartRequest;
import Ecommerce.Web.Ecommerce.Entity.Cart;



import Ecommerce.Web.Ecommerce.Service.CartServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseExtractor;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Cart")
public class CartController {
    private final CartServices cartServices;

    @PreAuthorize("hasRole('User')")
    @PostMapping("/addToCart")
    public AppResponse<Cart> addToCart( @RequestBody CartRequest cartRequest) {
        return  cartServices.addToCart(cartRequest);

    }


}


