package ir.comprehensive.service;

import ir.comprehensive.domain.Product;
import ir.comprehensive.service.extra.Swappable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService implements Swappable<Product> {
}
