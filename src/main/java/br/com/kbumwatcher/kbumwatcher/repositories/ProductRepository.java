package br.com.kbumwatcher.kbumwatcher.repositories;

import br.com.kbumwatcher.kbumwatcher.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

}
