package com.SpringBoot.springbootRef.Student;
import org.springframework.stereotype.Service;
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl extends ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }
}
