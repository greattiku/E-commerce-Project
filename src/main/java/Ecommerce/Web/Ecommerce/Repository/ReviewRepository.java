package Ecommerce.Web.Ecommerce.Repository;

import Ecommerce.Web.Ecommerce.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
