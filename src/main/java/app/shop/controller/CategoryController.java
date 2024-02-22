package app.shop.controller;

import app.shop.dto.CategoryDTO;
import app.shop.entity.Category;
import app.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/category/")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService service;
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }
    @PostMapping(value = "add/")
    public ResponseEntity<CategoryDTO> add (@RequestBody Category category){
        log.info(category.getTitle());
        return ResponseEntity.ok().body(service.add(category));
    }
    @DeleteMapping(value = "delete/{id}/")
    public ResponseEntity<CategoryDTO> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/update/")
    public ResponseEntity<CategoryDTO> update(@RequestBody Category category) {
        return ResponseEntity.ok().body(service.update(category));
    }
}
