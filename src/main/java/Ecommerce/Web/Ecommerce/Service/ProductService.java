package Ecommerce.Web.Ecommerce.Service;

import Ecommerce.Web.Ecommerce.Configuration.JwtAuthenticationFilter;
import Ecommerce.Web.Ecommerce.Dto.*;
//import Ecommerce.Web.Ecommerce.Entity.Category;
import Ecommerce.Web.Ecommerce.Entity.*;
import Ecommerce.Web.Ecommerce.Exception.ApiException;
//import Ecommerce.Web.Ecommerce.Repository.CategoryRepository;
import Ecommerce.Web.Ecommerce.Repository.CartRepository;
import Ecommerce.Web.Ecommerce.Repository.ProductRepository;
import Ecommerce.Web.Ecommerce.Repository.UserRepository;
import io.swagger.v3.core.model.ApiDescription;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final CartRepository cartRepository;


    public AppResponse<String> addProduct(ProductRequest productRequest) {

        Product addingProduct = new Product();
        addingProduct.setName(productRequest.getName());
        addingProduct.setDescription(productRequest.getDescription());
        addingProduct.setColour(productRequest.getColour());
        addingProduct.setBrand(productRequest.getBrand());
        addingProduct.setPrice(productRequest.getPrice());
        addingProduct.setSize(productRequest.getSize());
        addingProduct.setQuantity(productRequest.getQuantity());
        addingProduct.setCategory(productRequest.getCategory());
        addingProduct.setImageUrl(productRequest.getImageUrl());

        productRepository.save(addingProduct);

        return new AppResponse<>(0, "Product has been created successfully");

    }


//           don't forget to do a calculation for price discount

    public AppResponse<Map<String, Object>> getAllProduct(Pageable pageable) {
        Page<ProductResponseFromDb> productResponseFromDbPage = productRepository.findAll(pageable).map((product) -> new ProductResponseFromDb(product));//we can sti use this---.map(ProductResponseFromDb::new); we're referencing back to the product response class

        Map<String, Object> page = Map.of(
                "page", productResponseFromDbPage.getNumber(),
                "totalPages", productResponseFromDbPage.getTotalPages(),
                "totalElements", productResponseFromDbPage.getTotalElements(),
                "size", productResponseFromDbPage.getSize(),
                "content", productResponseFromDbPage.getContent()
        );


        return new AppResponse<>("success", page);
    }

    public AppResponse<List<ProductResponseFromDb>> getByName(String name) {
        List<Product> product = productRepository.findByName(name);
        if (product == null || product.isEmpty()) {
            throw new ApiException("Product not found");
        }
        List<ProductResponseFromDb> pro = product.stream()
                .map(ProductResponseFromDb::new)
                .collect(Collectors.toList());

        return new AppResponse<>(0, "Successful", pro);

    }

    public AppResponse<List<ProductResponseFromDb>> getByCategoryName(String category) {
        List<Product> categories = productRepository.findByCategory(category);
        if (categories == null || categories.isEmpty()) {
            throw new ApiException("Category not found");
        }
        List<ProductResponseFromDb> cat = categories.stream()
                .map(ProductResponseFromDb::new)
                .collect(Collectors.toList());
        return new AppResponse<>(0, "Successful", cat);
    }





    public List<Product> productCheckOut(boolean isSingleProductCheckOut, long productId) {
        List<Product> products = null; // Declare products outside the if-else blocks

        if (isSingleProductCheckOut && productId != 0) {
            // Single product checkout
            products = new ArrayList<>();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ApiException("Product not found or doesn't exist"));
            products.add(product);
        } else {
            // All products checkout
            String userEmail = JwtAuthenticationFilter.CURRENT_USER;
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new ApiException("User not found or doesn't exist"));

            List<Cart> carts = cartRepository.findByUser(user);
            products = carts.stream()
                    .flatMap(cart -> cart.getCartItems().stream())
                    .map(CartItem::getProduct)
                    .collect(Collectors.toList());
        }

        return products;
    }


    public AppResponse<String> updateProduct(long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(()->new ApiException("Product not found or doesn't exist"));
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setColour(productRequest.getColour());
        product.setPrice(productRequest.getPrice());
        product.setBrand(productRequest.getBrand());
        product.setCategory(productRequest.getCategory());
        product.setQuantity(productRequest.getQuantity());
        product.setSize(productRequest.getSize());
        product.setImageUrl(productRequest.getImageUrl());

        productRepository.save(product);
        return new AppResponse<>(200, "Product updated Successfully");

    }

    public AppResponse<String> deleteProduct(long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            productRepository.delete(product);
            return new AppResponse<>(200, "Product deleted successfully");
        } else {
            throw new ApiException("Product not found or doesn't exists");
        }
    }
}

