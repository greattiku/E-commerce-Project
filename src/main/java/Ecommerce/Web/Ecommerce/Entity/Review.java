package Ecommerce.Web.Ecommerce.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.InputMismatchException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "email")
    private User user;
    @ManyToOne
   // @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "comment", nullable = false)
    private String comment;
    @Column(name = "rating", nullable = false)
    private int rating;
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;


}
