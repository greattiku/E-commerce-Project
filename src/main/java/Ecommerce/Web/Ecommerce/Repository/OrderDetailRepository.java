package Ecommerce.Web.Ecommerce.Repository;

import Ecommerce.Web.Ecommerce.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
