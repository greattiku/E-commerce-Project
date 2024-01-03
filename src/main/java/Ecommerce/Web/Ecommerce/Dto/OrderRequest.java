package Ecommerce.Web.Ecommerce.Dto;

import Ecommerce.Web.Ecommerce.Entity.Cart;
import Ecommerce.Web.Ecommerce.Entity.CartItem;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderRequest {
    @NotEmpty(message = "first_Name can't be blank")
    private String firstName;
    @NotEmpty(message = "last_Name can't be blank")
    private String lastName;
    @NotEmpty(message = "phone_Number can't be blank")
    private String phoneNumber;
    @NotEmpty(message = "city can't be blank")
    private String city;
    @NotEmpty(message = "order_Status can't be blank")
    private String orderStatus;
    @NotEmpty(message = "total can't be blank")
    private Double total;
    @NotEmpty(message = "State can't be blank")
    private String state;
    @NotEmpty(message = "postal_code can't be blank")
    private String postalCode;
    @NotEmpty(message = "country can't be blank")
    private String country;
    @NotEmpty(message = "delivery_type can't be blank")
    private String deliveryType;

    private List<CartRequest.ItemRequest> itemRequest;

}
