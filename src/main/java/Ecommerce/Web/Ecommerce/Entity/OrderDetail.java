package Ecommerce.Web.Ecommerce.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OrderDetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_Name", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "contactNumber", nullable = false)
    private String phoneNumber;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "orderStatus", nullable = false)
    private String orderStatus;
    @Column(name = "total", nullable = false)
    private Double total;
    @Column(name = "State", nullable = false)
    private String state;
    @Column(name = "postalCode", nullable = false)
    private String postalCode;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "deliveryType", nullable = false)
    private String deliveryType;
    @OneToOne
    private  Product product;
    @ManyToOne
    private User user;
    private  LocalDateTime creationDate;

//    public double calculateOrderAmount(){
//        double orderAmount ;
//
//        if (cartItems !=null){
//            for (CartItem cartItem : cartItems){
//                orderAmount = orderAmount.add(calculateOrderAmount());
//            }
//        }
//        return orderAmount;
//    }

}
