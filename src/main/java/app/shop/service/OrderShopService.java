package app.shop.service;

import app.shop.entity.OrderShop;
import app.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderShopService {
    private final OrderRepository repository;


    public List<OrderShop> getAll() {
        return repository.findAll();
    }
    @Transactional
    public OrderShop add(OrderShop orderShop) {
        repository.save(orderShop);
        return orderShop;
    }
}
