package ir.comperhensive.service;

import ir.comperhensive.domain.Product;
import ir.comperhensive.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> fetchAll() {
        return repository.findAll();
    }
}
