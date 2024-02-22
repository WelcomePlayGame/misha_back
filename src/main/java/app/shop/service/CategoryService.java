package app.shop.service;
import app.shop.dto.CategoryDTO;
import app.shop.entity.Category;
import app.shop.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    final  private ModelMapper modelMapper;
    public List<CategoryDTO> getAll() {
    return repository.findAll().stream().map((element) -> modelMapper.map(element, CategoryDTO.class)).collect(Collectors.toList());
    }
    @Transactional
    public CategoryDTO add(Category category) {
        return modelMapper.map(repository.save(category), CategoryDTO.class);
    }

    @Transactional
    public void delete(long id) {
    repository.deleteById(id);
    }
    @Transactional
    public CategoryDTO update(Category category) {
        Category categoryUdpate = repository.findById(category.getId()).orElseThrow(()-> new EntityNotFoundException("No id category"));
        categoryUdpate.setTitle(category.getTitle());
        repository.save(categoryUdpate);
        return modelMapper.map(categoryUdpate, CategoryDTO.class);
    }
}
