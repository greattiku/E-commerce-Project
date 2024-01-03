package Ecommerce.Web.Ecommerce.Dto;

import Ecommerce.Web.Ecommerce.Entity.Product;
import Ecommerce.Web.Ecommerce.Entity.Review;
import Ecommerce.Web.Ecommerce.Entity.User;
import lombok.Data;

@Data
public class ReviewResponseFromDb {

    private Long id;

    private User user;

    private Product product;

    private String comment;

    private int rating;


    public ReviewResponseFromDb(Review review) {
        id = review.getId();
        user = review.getUser();
        product = review.getProduct();
        comment = review.getComment();
        rating = review.getRating();

    }
}
