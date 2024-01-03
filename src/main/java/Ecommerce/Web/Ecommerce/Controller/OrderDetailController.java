package Ecommerce.Web.Ecommerce.Controller;

import Ecommerce.Web.Ecommerce.Dto.AppResponse;
import Ecommerce.Web.Ecommerce.Dto.OrderRequest;
import Ecommerce.Web.Ecommerce.Dto.ProductRequest;
import Ecommerce.Web.Ecommerce.Entity.Product;
import Ecommerce.Web.Ecommerce.Service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Order")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @PreAuthorize("hasRole('User')")
    @PostMapping({"/placeOrder"})
    public AppResponse<String> placeOrder(@RequestBody OrderRequest orderRequest){
        return orderDetailService.placeOrder(orderRequest);
    }


}
