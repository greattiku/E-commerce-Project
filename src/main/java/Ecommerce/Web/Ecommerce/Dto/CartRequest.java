package Ecommerce.Web.Ecommerce.Dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CartRequest {
    private  List<ItemRequest> itemRequest;


    @Data
    public static class ItemRequest{
        private Long productId;
        private int quantity;
    }


}
