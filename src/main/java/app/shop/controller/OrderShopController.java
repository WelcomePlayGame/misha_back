package app.shop.controller;

import app.shop.entity.OrderShop;
import app.shop.service.OrderShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
@RequiredArgsConstructor
public class OrderShopController {

    private final OrderShopService service;
    @GetMapping
    ResponseEntity<List<OrderShop>> getAll() {
        return  ResponseEntity.ok().body(service.getAll());
    }
    @PostMapping(value = "/add/")
    ResponseEntity<OrderShop> add(@RequestBody OrderShop orderShop) {
        return ResponseEntity.ok().body(service.add(orderShop));
    }
}
