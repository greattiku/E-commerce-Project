package Ecommerce.Web.Ecommerce.Service;

import Ecommerce.Web.Ecommerce.Configuration.JwtAuthenticationFilter;
import Ecommerce.Web.Ecommerce.Dto.*;
import Ecommerce.Web.Ecommerce.Entity.OrderDetail;
import Ecommerce.Web.Ecommerce.Entity.Product;
import Ecommerce.Web.Ecommerce.Entity.Review;
import Ecommerce.Web.Ecommerce.Entity.User;
import Ecommerce.Web.Ecommerce.Exception.ApiException;
import Ecommerce.Web.Ecommerce.Repository.ProductRepository;
import Ecommerce.Web.Ecommerce.Repository.ReviewRepository;
import Ecommerce.Web.Ecommerce.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;


@RequiredArgsConstructor
@Service
@Slf4j
public class ReviewService {
    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;


    public AppResponse<String> review(ReviewRequest reviewRequest, long productId) {

            String userEmail = JwtAuthenticationFilter.CURRENT_USER;
            User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new ApiException("User not found. Please, login to write a review"));

            Product product = productRepository.findById(productId).orElseThrow(() -> new ApiException("Product not found or doesn't exist"));


            Review addReview = new Review();
        addReview.setUser(user);
        addReview.setProduct(product);
        addReview.setComment(reviewRequest.getComment());
        addReview.setRating(reviewRequest.getRating());
        addReview.setCreationDate(LocalDateTime.now());

        reviewRepository.save(addReview);

        return new AppResponse<>(0, "Successful");

    }

    public AppResponse<Map<String, Object>> getAllReviews(Pageable pageable) {
        Page<ReviewResponseFromDb> reviewResponseFromDbPage = reviewRepository.findAll(pageable).map(ReviewResponseFromDb::new);//we can sti use this---.map(ProductResponseFromDb::new); we're referencing back to the product response class

        Map<String, Object> page = Map.of(
                "page", reviewResponseFromDbPage.getNumber(),
                "totalPages", reviewResponseFromDbPage.getTotalPages(),
                "totalElements", reviewResponseFromDbPage.getTotalElements(),
                "size", reviewResponseFromDbPage.getSize(),
                "content", reviewResponseFromDbPage.getContent()
        );


        return new AppResponse<>("success", page);
    }
}
