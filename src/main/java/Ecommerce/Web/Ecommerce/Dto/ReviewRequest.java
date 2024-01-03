package Ecommerce.Web.Ecommerce.Dto;

import Ecommerce.Web.Ecommerce.Entity.User;
import lombok.Data;

@Data
public class ReviewRequest {


    private String comment;

    private int rating;
}
