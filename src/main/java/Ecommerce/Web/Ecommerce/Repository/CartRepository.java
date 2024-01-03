package Ecommerce.Web.Ecommerce.Repository;

import Ecommerce.Web.Ecommerce.Entity.Cart;

import Ecommerce.Web.Ecommerce.Entity.CartItem;
import Ecommerce.Web.Ecommerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);



}