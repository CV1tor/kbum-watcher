package br.com.kbumwatcher.kbumwatcher.services;

import br.com.kbumwatcher.kbumwatcher.domains.Product;
import br.com.kbumwatcher.kbumwatcher.domains.ProductSearch;
import br.com.kbumwatcher.kbumwatcher.exceptions.ProductSearchException;
import br.com.kbumwatcher.kbumwatcher.repositories.ProductSearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductSearchService {
    private final ProductSearchRepository productSearchRepository;

    public void save(List<Product> products, String keyword) {
        ProductSearch productSearch = new ProductSearch();
        productSearch.setProducts(products);
        productSearch.setKeyword(keyword);

        productSearchRepository.save(productSearch);
    }

    public ProductSearch findById(Long id) {
        return productSearchRepository.findById(id).orElseThrow(() -> new ProductSearchException("ProductSearch not found"));
    }

    public List<ProductSearch> findByKeyword(String keyword) {
        return productSearchRepository.findByKeyword(keyword).orElseThrow(() -> new ProductSearchException("ProductSearch not found"));
    }

    public List<ProductSearch> findByProductsName(String name) {
        return productSearchRepository.findByProductsName(name).orElseThrow(() -> new ProductSearchException("ProductSearch not found"));
    }

}
