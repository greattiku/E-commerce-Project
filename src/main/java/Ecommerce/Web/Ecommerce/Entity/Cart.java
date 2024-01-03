package Ecommerce.Web.Ecommerce.Entity;

import Ecommerce.Web.Ecommerce.Dto.CartRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Shopping_Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "email")
    private User user;

    @OneToMany(cascade = CascadeType.PERSIST)
    private  List<CartItem> cartItems;


    private LocalDateTime creationDate;

    private LocalDateTime modificationDate;

    }




