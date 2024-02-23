package app.shop.controller;

import app.shop.entity.Product;
import app.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product/")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService service;
    @GetMapping
    public ResponseEntity<List<Product>> get() {
        return ResponseEntity.ok().body(service.get());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<Product> getId (@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.getId(id));
    }
    @PostMapping(value = "add")
    public ResponseEntity<Product> add (@RequestPart Product product, @RequestPart List<MultipartFile> photos) {
    for (MultipartFile file : photos) {
        log.info(file.getOriginalFilename());
    }
    return ResponseEntity.ok().body(service.add(product, photos));
    }
    @DeleteMapping(value = "delete/{id}/")
    public ResponseEntity<Product> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "update")
    public ResponseEntity<Product> update(@RequestPart Product product, @RequestPart List<MultipartFile> photos) {
    return ResponseEntity.ok().body(service.update(product, photos));
    }
}
