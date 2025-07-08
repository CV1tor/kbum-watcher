package br.com.kbumwatcher.kbumwatcher.services;

import br.com.kbumwatcher.kbumwatcher.domains.Product;
import br.com.kbumwatcher.kbumwatcher.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void save(Product product) {
        Product productExists = productRepository.findByName(product.getName()).orElse(null);
        if(productExists != null) {
            productRepository.save(productExists);
            return;
        }

        productRepository.save(product);
    }
}
