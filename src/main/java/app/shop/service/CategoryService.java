package app.shop.service;
import app.shop.entity.Category;
import app.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    public List<Category> getAll() {
    return repository.findAll();
    }
    @Transactional
    public Category add(Category category) {
        return repository.save(category);
    }

    @Transactional
    public void delete(long id) {
    repository.deleteById(id);
    }
}
