package Ecommerce.Web.Ecommerce.Dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


@Data
public class ProductRequest {
    @NotEmpty(message = "Name can't be blank")
    private String name;

    @NotEmpty(message = "Description can't be blank")
    private String description;

//    @NotNull(message = "Price can't be blank")
    @PositiveOrZero(message = "Price can't be blank")
    private double price;//don't forget to do a calculation for discount

    @NotEmpty(message = "Colour can't be blank")
    private String colour;

    private String brand;

    @NotEmpty(message = "Size can't be blank")
    private String size;

    @NotNull(message = "Quantity can't be blank")
    private int quantity;

    @NotEmpty(message = "Category can't be blank")
    private String category;

    @NotEmpty(message = "imageUrl can't be blank")
    private String imageUrl;
}

//    private Set<ImageModel> productImages;