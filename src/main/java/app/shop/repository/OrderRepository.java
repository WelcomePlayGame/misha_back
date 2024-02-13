package app.shop.repository;

import app.shop.entity.OrderShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderShop, Long> {
}
