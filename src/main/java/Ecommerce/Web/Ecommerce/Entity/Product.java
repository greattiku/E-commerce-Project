package Ecommerce.Web.Ecommerce.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "Price", nullable = false)
    private double price;//don't forget to do a calculation for discount

    @Column(name = "Colour", nullable = false)
    private String colour;

    @Column(name = "Brand", nullable = false)
    private String brand;

    @Column(name = "Size", nullable = false)
    private String size;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "category", nullable = false)
//    @ManyToMany(cascade = CascadeType.PERSIST)
    private String category;

    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

}


//    @Getter
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "product_images",
//            joinColumns =  {
//                    @JoinColumn(name = "product_id")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "image_id")
//            }
//    )
//    private Set<ImageModel> productImages;
//
//    public void setProductImages(Set<ImageModel> productImages) {
//        this.productImages = productImages;
//    }
//
//}