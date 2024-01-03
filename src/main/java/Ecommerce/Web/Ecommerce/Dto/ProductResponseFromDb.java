package Ecommerce.Web.Ecommerce.Dto;

//import Ecommerce.Web.Ecommerce.Entity.Category;
import Ecommerce.Web.Ecommerce.Entity.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductResponseFromDb {

    private Long id;

    private String name;

    private String description;

    private String colour;

    private double price;

    private String size;

    private String brand;

    private int quantity;

    private String category;

    private String imageUrl;


    public ProductResponseFromDb(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        colour = product.getColour();
        price = product.getPrice();
        brand = product.getBrand();
        size = product.getSize();
        quantity = product.getQuantity();
        category = product.getCategory();
        imageUrl = product.getImageUrl();
    }
    }


//    public List<CategoryResponseFromDb> convertFrmDbToResponse(Product product) {
//        List<Category> categories = product.getCategory();
//        List<CategoryResponseFromDb> categoryResponse = new ArrayList<>();
//        for (Category c : category){
//            CategoryResponseFromDb categoryResponseItem = new CategoryResponseFromDb(c);
//            categoryResponse.add(categoryResponseItem);
//        }
//        return  categoryResponse;
//
//    }
//}