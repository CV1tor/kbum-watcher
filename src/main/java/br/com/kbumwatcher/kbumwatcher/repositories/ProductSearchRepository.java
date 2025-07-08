package br.com.kbumwatcher.kbumwatcher.repositories;

import br.com.kbumwatcher.kbumwatcher.domains.ProductSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductSearchRepository extends JpaRepository<ProductSearch, Long> {
    Optional<List<ProductSearch>> findByProductsName(String name);

    Optional<List<ProductSearch>> findByKeyword(String keyword);
}
