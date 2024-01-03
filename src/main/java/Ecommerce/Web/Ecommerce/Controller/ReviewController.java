package Ecommerce.Web.Ecommerce.Controller;

import Ecommerce.Web.Ecommerce.Dto.AppResponse;
import Ecommerce.Web.Ecommerce.Dto.ProductRequest;
import Ecommerce.Web.Ecommerce.Dto.ReviewRequest;
import Ecommerce.Web.Ecommerce.Entity.Product;
import Ecommerce.Web.Ecommerce.Service.ProductService;
import Ecommerce.Web.Ecommerce.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {


        private final ReviewService reviewService;

        @PreAuthorize("hasRole('User')")
        @PostMapping("/add-review/{productId}")
        public AppResponse<String> review(@RequestBody @Valid ReviewRequest reviewRequest,
                                          @PathVariable Long productId) {
                return reviewService.review(reviewRequest, productId);
        }

        @GetMapping("/getAllReviews")
        public AppResponse<Map<String, Object>> getAllReviews(
                @RequestParam(value = "Page", required = false, defaultValue = "0") int page,
                @RequestParam(value = "page", required = false, defaultValue = "15") int size
        ) {
            return reviewService.getAllReviews(PageRequest.of(page, size));

        }
}
