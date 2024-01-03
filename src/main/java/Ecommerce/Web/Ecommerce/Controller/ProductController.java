package Ecommerce.Web.Ecommerce.Controller;

import Ecommerce.Web.Ecommerce.Dto.*;
import Ecommerce.Web.Ecommerce.Entity.Product;
import Ecommerce.Web.Ecommerce.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Product")
public class ProductController {
    private final ProductService productService;

  @PreAuthorize("hasRole('admin')")
    @PostMapping("/add-Product")
    public AppResponse<String> addProduct(@RequestBody @Valid ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

        @GetMapping("/getProducts")
        public AppResponse<Map<String, Object>> getAllProduct(
                @RequestParam(value = "Page", required = false, defaultValue = "0") int page,
                @RequestParam(value = "page", required = false, defaultValue = "15") int size
        ) {
            return productService.getAllProduct(PageRequest.of(page, size));

        }

    @GetMapping("/{name}")
    public AppResponse<List<ProductResponseFromDb>> getByName(@PathVariable String name) {
        return productService.getByName(name);
    }

    @GetMapping("/category/{category}")
    public  AppResponse<List<ProductResponseFromDb>>  getByCategoryName(@PathVariable String category) {
        return productService.getByCategoryName(category);

    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/productCheckOut/{isSingleProductCheckOut}/{productId}"})
    public List<Product> productCheckOut(@PathVariable(name ="isSingleProductCheckOut")boolean isSingleProductCheckOut,
                                         @PathVariable(name = "productId")  Long productId){
        return productService.productCheckOut(isSingleProductCheckOut,productId);
    }

    @PreAuthorize("hasRole('Admin')")
    @PatchMapping("/update/{id}")
    public AppResponse<String> updateProduct (@PathVariable Long id, @RequestBody @Valid ProductRequest productRequest) {
        return productService.updateProduct(id,productRequest);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/delete/{id}")
    public AppResponse<String> deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
    }
