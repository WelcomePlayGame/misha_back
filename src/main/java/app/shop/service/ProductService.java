package app.shop.service;

import app.shop.entity.Category;
import app.shop.entity.Product;
import app.shop.repository.CategoryRepository;
import app.shop.repository.ProductRepository;
import app.shop.unit.WorkWithFile;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final CategoryRepository repository_category;

    @Value("${url_base}")
    private String URL_BASE;

    @Value("${url_product}")
    private String URL_PRODUCT;

    @Value("${upload_product}")
    private String UPLOAD_PRODUCT;

    public List<Product> get() {
        return  repository.findAll();
    }

    @Transactional
    public Product add(Product product, List<MultipartFile> photos) {
        Category category = repository_category.findById(product.getCategory().getId()).orElseThrow(() -> new EntityNotFoundException("No category id"));
        List<String> listUrl = new ArrayList<>();
        for (MultipartFile file : photos) {
            String name = WorkWithFile.generateNameFile(file);
            WorkWithFile.createDirectory(UPLOAD_PRODUCT, name, file);
            listUrl.add(URL_BASE+ URL_PRODUCT+"/"+name);
        }
        product.setPhoto(listUrl);
        product.setCategory(category);
        return  repository.save(product);
    }

    public Product getId(Long id) {
        Product product = repository.findById(id).orElseThrow(()-> new EntityNotFoundException("No id Product"));
        return product;
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Product update(Product product, List<MultipartFile> photos) {
        Product productUpdate = repository.findById(product.getId()).orElseThrow(()-> new EntityNotFoundException("No id product"));
        Category categoryUpdate = repository_category.findById(product.getCategory().getId()).orElseThrow(()-> new EntityNotFoundException("No id category"));
        List<String> listUrl = new ArrayList<>();
        for (MultipartFile file : photos) {
            String name = WorkWithFile.generateNameFile(file);
            WorkWithFile.createDirectory(UPLOAD_PRODUCT, name, file);
            listUrl.add(URL_BASE+ URL_PRODUCT+"/"+name);
        }
        if (product.getTitle()!=null) {
            productUpdate.setTitle(product.getTitle());
        }
        if (product.getDesribe()!=null) {
            productUpdate.setDesribe(productUpdate.getDesribe());
        }
        if (product.getCost() !=null) {
            productUpdate.setCost(product.getCost());
        }
        if (product.getNew_cost() != null) {
            productUpdate.setNew_cost(product.getNew_cost());
        }
       if (product.isPromotion()) {
           productUpdate.setPromotion(product.isPromotion());
       }
        productUpdate.setPhoto(listUrl);
        productUpdate.setCategory(categoryUpdate);
        return productUpdate;
    }
}
