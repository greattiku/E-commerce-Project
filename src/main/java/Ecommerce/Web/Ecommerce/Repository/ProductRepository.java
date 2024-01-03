package Ecommerce.Web.Ecommerce.Repository;

//import Ecommerce.Web.Ecommerce.Entity.Category;
import Ecommerce.Web.Ecommerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);

    List<Product> findByCategory(String category);

    Optional<Product> findById(long productId);



}
